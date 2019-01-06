package com.ucab.fcpclient4j;

import com.ucab.fcpclient4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpclient4j.comun.utilidades.ProgramManager;
import com.ucab.fcpclient4j.comun.utilidades.ServerManager;
import com.ucab.fcpclient4j.logica.comandos.commit.ComandoRealizarCommit;
import com.ucab.fcpclient4j.logica.comandos.comunicacionInicial.ComandoEnviarHandShake;
import com.ucab.fcpclient4j.logica.comandos.comunicacionInicial.ComandoSeleccionarServidor;
import com.ucab.fcpclient4j.logica.mensajes.core.MensajeManager;
import com.ucab.fcpclient4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpclient4j.logica.mensajes.salida.Commit;
import com.ucab.fcpclient4j.logica.mensajes.salida.HandShake;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class App 
{
    public static void main( String[] args )
    {
        System.setProperty("javax.net.ssl.trustStore", LeerPropiedad.TRUSTSTOREPATH );
        System.setProperty("javax.net.ssl.trustStorePassword ", LeerPropiedad.TRUSTSTOREPASSWORD );


        //TODO: Realizar logica local antes de iniciar conexion y selecccion de servidores

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
            System.out.println( "EXCEPCION SOCKETS "+ e.toString() );
        }
        catch ( Exception e )
        {
            System.out.println( "EXCEPCION GENERAL "+ e.toString() );
        }
    }
}
