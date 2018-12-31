package com.ucab.fcpserver4j;

import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.logica.comandos.servidores.salida.generacionidservidores.ComandoCompuestoGenerarIds;
import com.ucab.fcpserver4j.servicio.Servicio;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class App
{
    public static void main( String[] args )
    {
        try
        {
            System.setProperty("javax.net.ssl.keyStore", LeerPropiedad.KEYSTOREPATH);
            System.setProperty("javax.net.ssl.keyStorePassword", LeerPropiedad.KEYSTOREPASSWORD);
            System.setProperty("javax.net.ssl.trustStore", LeerPropiedad.TRUSTSTOREPATH );
            System.setProperty("javax.net.ssl.trustStorePassword ", LeerPropiedad.TRUSTSTOREPASSWORD );

            // Inicia el servicio para escuchar nuevas peticiones de servidores y clientes.
            Thread servicio = new Thread( new Servicio() );
            servicio.start();

            // Inicia el proceso para obtener el id de los otros servidores y generar el id local.
            ComandoCompuestoGenerarIds comando = new ComandoCompuestoGenerarIds();
            comando.ejecutar();
        }
        catch(IOException e)
        {
            System.out.println( "IOException GLOBAL" );
            System.out.println( e.toString() );
        }
        catch( NoSuchAlgorithmException e)
        {
            System.out.println( "NoSuchAlgorithmException GLOBAL" );
            System.out.println( e.toString() );
        }
        catch( Exception e )
        {
            System.out.println( "Exception GLOBAL" );
            System.out.println( e.toString() );
        }
    }
}
