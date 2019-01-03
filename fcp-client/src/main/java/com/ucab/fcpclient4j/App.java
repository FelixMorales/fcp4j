package com.ucab.fcpclient4j;

import com.ucab.fcpclient4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpclient4j.comun.utilidades.Conexion;
import com.ucab.fcpclient4j.comun.utilidades.ServerManager;
import com.ucab.fcpclient4j.logica.comandos.comunicacionInicial.ComandoSeleccionarServidor;
import com.ucab.fcpclient4j.logica.mensajes.salida.PruebaSalida;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class App 
{
    public static void main( String[] args ) throws NoSuchAlgorithmException, IOException
    {
        System.setProperty("javax.net.ssl.trustStore", LeerPropiedad.TRUSTSTOREPATH );
        System.setProperty("javax.net.ssl.trustStorePassword ", LeerPropiedad.TRUSTSTOREPASSWORD );

        ComandoSeleccionarServidor comando = new ComandoSeleccionarServidor();
        if(comando.ejecutar())
        {
            ServerManager.obtenerGlobal().getServidorPrincipal().enviarCaracteres( new PruebaSalida() );
            String mensaje = ServerManager.obtenerGlobal().getServidorPrincipal().recibirCaracteres();
            System.out.println( "mensaje recibido"+ mensaje );
            ServerManager.obtenerGlobal().getServidorPrincipal().getConexion().close();
        }
    }
}
