package com.ucab.fcpserver4j;

import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.servicio.Servicio;
import java.io.IOException;

public class App
{
    public static void main( String[] args )
    {
        try
        {
            System.setProperty( "javax.net.ssl.keyStore", LeerPropiedad.KEYSTOREPATH);
            System.setProperty("javax.net.ssl.keyStorePassword", LeerPropiedad.KEYSTOREPASSWORD);

            Thread servicio = new Thread( new Servicio() );
            servicio.start();
        }
        catch(IOException e)
        {
            System.out.println( e.getMessage() );
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}