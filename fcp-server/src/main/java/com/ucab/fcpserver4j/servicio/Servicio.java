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
        while ( true )
        {
            try
            {
                Thread hilo = new Thread( new ConnectionManager( new Conexion( listener.accept() ) ) );
                hilo.start();
            }
            catch(IOException e)
            {
                System.out.println( e.toString() );
            }
        }
    }
}
