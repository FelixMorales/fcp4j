package com.ucab.fcpserver4j.persistencia;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.entidades.Servidor;
import javafx.scene.shape.Arc;
import sun.security.krb5.internal.crypto.Des;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DatabaseManager
{
    private Connection conexion;


    private static final Lock lock = new ReentrantLock();



    private static DatabaseManager singleton;

    private DatabaseManager()
    { }

    public static synchronized DatabaseManager obtenerSingleton()
    {
        if(singleton == null)
            singleton = new DatabaseManager();

        return singleton;
    }

    private void Conectar() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        String separator = File.separator;
        conexion = DriverManager.getConnection( "jdbc:sqlite:persistencia"+separator+"db"+separator+"archivosServidor.db" );
    }

    private void Desconectar()
    {
        try
        {
            if(!conexion.isClosed())
            {
                conexion.close();
            }
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }

    private int ObtenerVersionArchivo( String nombreArchivo )
    {
        int retorno = 1;
        String sql = "SELECT version FROM archivo WHERE nombre = ? ORDER BY id DESC LIMIT 1";
        PreparedStatement pstmt = null;

        try
        {
            Conectar();

            pstmt = conexion.prepareStatement( sql );
            pstmt.setString( 1, nombreArchivo.toLowerCase() );
            ResultSet rs  = pstmt.executeQuery();

            if(rs.next())
            {
                retorno = rs.getInt( "version" ) + 1;
            }

            CerrarResultSet( rs );
            CerrarPreparedStatement( pstmt );
        }
        catch ( SQLException  | ClassNotFoundException e )
        {
            CerrarPreparedStatement( pstmt );
        }
        finally
        {
            Desconectar();
        }
        return retorno;
    }

    private void ActualizarHistorico() throws SQLException
    {
        String sql = "UPDATE historico SET contador = contador + 1 WHERE id = 1";
        PreparedStatement pstmt = null;

        int filasAfectadas;

        try
        {
            pstmt = conexion.prepareStatement( sql );
            filasAfectadas = pstmt.executeUpdate();

            if(filasAfectadas != 1)
            {
                conexion.rollback();
            }

            CerrarPreparedStatement( pstmt );
        }
        catch ( SQLException e )
        {
            CerrarPreparedStatement( pstmt );
            throw e;
        }

    }

    private void RegistrarLocalizacionArchivo(Archivo archivo) throws SQLException
    {
        String sql = "INSERT INTO archivo_ubicacion(idArchivo, servidor) VALUES(?,?)";
        PreparedStatement pstmt = null;

        int filasAfectadas;

        try
        {
            for(String localizacion : archivo.getLocalizacion())
            {
                pstmt = conexion.prepareStatement( sql );
                pstmt.setLong( 1, archivo.getId() );
                pstmt.setString( 2, localizacion );
                filasAfectadas = pstmt.executeUpdate();

                if(filasAfectadas != 1)
                {
                    conexion.rollback();
                    break;
                }

                CerrarPreparedStatement( pstmt );
            }
        }
        catch ( SQLException e )
        {
            CerrarPreparedStatement( pstmt );
            throw e;
        }

    }

    private int ObtenerIdArchivo(Archivo archivo) throws SQLException
    {
        int retorno = -1;
        String sql = "SELECT id FROM archivo WHERE nombre = ? AND version = ? LIMIT 1";
        PreparedStatement pstmt = null;

        try
        {
            pstmt = conexion.prepareStatement( sql );
            pstmt.setString( 1, archivo.getNombre() );
            pstmt.setInt( 2, archivo.getVersion() );
            ResultSet rs  = pstmt.executeQuery();

            if(rs.next())
            {
                retorno = rs.getInt( "id" );
            }

            CerrarResultSet( rs );
            CerrarPreparedStatement( pstmt );
        }
        catch ( SQLException e )
        {
            CerrarPreparedStatement( pstmt );
            throw e;
        }

        return retorno;

    }

    public void AgregarArchivo( Archivo archivo, boolean fromCommit )
    {
        String insertArchivo = "INSERT INTO archivo(nombre,version, autor) VALUES(?,?,?)";
        PreparedStatement pstmt = null;

        int filasAfectadas;

        lock.lock();

        if(fromCommit)
        {
            int versionArchivo = ObtenerVersionArchivo( archivo.getNombre() );
            archivo.setVersion( versionArchivo );
        }

        try
        {
            Conectar();

            conexion.setAutoCommit( false );


            pstmt = conexion.prepareStatement( insertArchivo );
            pstmt.setString( 1, archivo.getNombre().toLowerCase() );
            pstmt.setInt( 2, archivo.getVersion() );
            pstmt.setString( 3, archivo.getAutor() );
            filasAfectadas = pstmt.executeUpdate();

            if(filasAfectadas != 1){
                conexion.rollback();
            }


            archivo.setId( ObtenerIdArchivo( archivo ) );

            RegistrarLocalizacionArchivo( archivo );

            ActualizarHistorico();

            conexion.commit();
        }
        catch ( ClassNotFoundException | SQLException e)
        {
            RollBack();
            e.printStackTrace();
        }
        finally
        {
            CerrarPreparedStatement( pstmt );
            Desconectar();
            lock.unlock();
        }
    }

    public int ObtenerHistorico()
    {
        int retorno = 0;
        String sql = "SELECT contador FROM historico LIMIT 1";
        PreparedStatement pstmt = null;

        try
        {
            Conectar();

            pstmt = conexion.prepareStatement( sql );
            ResultSet rs  = pstmt.executeQuery();

            if(rs.next())
            {
                retorno = rs.getInt( "contador" );
            }

            CerrarResultSet( rs );
            CerrarPreparedStatement( pstmt );
        }
        catch ( SQLException  | ClassNotFoundException e )
        {
            CerrarPreparedStatement( pstmt );
        }
        finally
        {
            Desconectar();
        }
        return retorno;
    }


    public HashMap<String, Integer> ObtenerCantidadArchivosPorServidor()
    {

        lock.lock();

        HashMap<String, Integer> retorno = new HashMap<>(  );
        String sql = "SELECT servidor, count(idArchivo) FROM archivo_ubicacion GROUP BY servidor ORDER BY count(idArchivo)";
        PreparedStatement pstmt = null;

        try
        {
            Conectar();

            pstmt = conexion.prepareStatement( sql );
            ResultSet rs  = pstmt.executeQuery();

            while(rs.next())
            {
                retorno.put( rs.getString( 1 ), rs.getInt( 2 ) );
            }

            CerrarResultSet( rs );
            CerrarPreparedStatement( pstmt );
        }
        catch ( SQLException  | ClassNotFoundException e )
        {
            CerrarPreparedStatement( pstmt );
        }
        finally
        {
            Desconectar();
            lock.unlock();
        }
        return retorno;
    }

    private  void CerrarPreparedStatement(PreparedStatement pm)
    {
        try
        {
            if(pm != null)
                pm.close();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }

    private  void CerrarResultSet(ResultSet rs)
    {
        try
        {
            if(rs != null)
                rs.close();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }

    private void RollBack()
    {
        try
        {
            conexion.rollback();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }

    }
}
