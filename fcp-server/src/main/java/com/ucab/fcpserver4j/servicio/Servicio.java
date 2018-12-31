package com.ucab.fcpserver4j.servicio;

import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.Conexion;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.NoSuchAlgorithmException;

/**
 * Name:                  Servicio
 * Description:           Detecta cada nueva conexion y la envia al ConnectionManager
 *
 * @since 30/12/18
 *
 */
public class Servicio implements Runnable
{
    private ServerSocket listener;

    public Servicio() throws IOException, NoSuchAlgorithmException
    {
        ServerSocketFactory fabrica = SSLContext.getDefault().getServerSocketFactory();
        int puerto = Integer.parseInt( LeerPropiedad.LOCAL.split( ":" )[1] );
        listener = fabrica.createServerSocket( puerto );
    }

    @Override
    public void run()
    {
        while ( true )
        {
            try
            {
                Conexion conexionEntrante = new Conexion( listener.accept() );
                Thread conectionManager = new Thread( new ConnectionManager( conexionEntrante ) );
                conectionManager.start();
            }
            catch(IOException e)
            {
                System.out.println( e.toString() );
            }
        }
    }
}
