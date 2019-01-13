package com.ucab.fcpclient4j;

import com.ucab.fcpclient4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpclient4j.comun.utilidades.ProgramManager;
import com.ucab.fcpclient4j.logica.comandos.comunicacionInicial.ComandoEnviarHandShake;
import com.ucab.fcpclient4j.logica.comandos.comunicacionInicial.ComandoSeleccionarServidor;

import java.io.IOException;
public class App 
{
    public static void main( String[] args )
    {
        System.setProperty("javax.net.ssl.trustStore", LeerPropiedad.TRUSTSTOREPATH );
        System.setProperty("javax.net.ssl.trustStorePassword ", LeerPropiedad.TRUSTSTOREPASSWORD );

        try
        {
            ComandoSeleccionarServidor comando = new ComandoSeleccionarServidor();
            if(comando.ejecutar())
            {
                ComandoEnviarHandShake comandoHandShake = new ComandoEnviarHandShake();
                comandoHandShake.ejecutar();

                ProgramManager.obtenerSingleton().ProcesarParametro( args );
            }
        }
        catch ( IOException e )
        {
            System.out.println( "ERROR AL INTENTAR CONTACTAR CON EL SERVIDOR.");
        }
        catch ( Exception e )
        {
            System.out.println( "ERROR GENERAL");
        }
    }
}
