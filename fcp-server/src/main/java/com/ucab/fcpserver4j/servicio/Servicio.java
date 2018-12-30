package com.ucab.fcpserver4j.servicio;

import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.Conexion;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.NoSuchAlgorithmException;

public class Servicio implements Runnable
{
    private ServerSocket listener;

    public Servicio() throws IOException, NoSuchAlgorithmException
    {
        ServerSocketFactory fabrica = SSLContext.getDefault().getServerSocketFactory();
        listener = fabrica.createServerSocket( Integer.parseInt( LeerPropiedad.PUERTO ) );
    }

    @Override
    public void run()
    {
        try
        {
            Conexion conexion;

            while ( true )
            {
                System.out.println( "Esperando conexion de clientes..." );
                conexion = new Conexion( listener.accept() );
                System.out.println( "Recibiendo informacion del cliente" );
                System.out.println( conexion.getConexion().getRemoteSocketAddress().toString() );
                System.out.println( conexion.recibirCaracteres() );
            }
        }
        catch(IOException e)
        {
            System.out.println( e.toString() );
        }
    }
}
