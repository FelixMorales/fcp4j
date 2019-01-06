package com.ucab.fcpserver4j.persistencia;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import javafx.scene.shape.Arc;
import sun.security.krb5.internal.crypto.Des;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        conexion = DriverManager.getConnection( "jdbc:sqlite:persistencia\\db\\archivosServidor.db" );
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

    public void AgregarArchivo( Archivo archivo )
    {
        String insertArchivo = "INSERT INTO archivo(nombre,version, autor, localizacion, fechaCreacion) VALUES(?,?,?,?,?)";
        PreparedStatement pstmt = null;

        int filasAfectadas;

        lock.lock();

        int versionArchivo = ObtenerVersionArchivo( archivo.getNombre() );

        try
        {
            Conectar();

            conexion.setAutoCommit( false );


            pstmt = conexion.prepareStatement( insertArchivo );
            pstmt.setString( 1, archivo.getNombre().toLowerCase() );
            pstmt.setInt( 2, versionArchivo );
            pstmt.setString( 3, archivo.getAutor() );
            pstmt.setString( 4, archivo.getLocalizacion().getNombre() );
            pstmt.setString( 5, String.valueOf( archivo.getFechaCreacion().getTime()) );
            filasAfectadas = pstmt.executeUpdate();

            if(filasAfectadas != 1){
                conexion.rollback();
            }

            ActualizarHistorico();

            conexion.commit();
        }
        catch ( ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            CerrarPreparedStatement( pstmt );
            Desconectar();
            lock.unlock();
        }
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
}
