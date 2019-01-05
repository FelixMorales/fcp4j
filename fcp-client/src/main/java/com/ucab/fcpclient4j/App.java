package com.ucab.fcpclient4j;

import com.ucab.fcpclient4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpclient4j.comun.utilidades.ServerManager;
import com.ucab.fcpclient4j.logica.comandos.commit.ComandoRealizarCommit;
import com.ucab.fcpclient4j.logica.comandos.comunicacionInicial.ComandoSeleccionarServidor;
import com.ucab.fcpclient4j.logica.mensajes.core.MensajeManager;
import com.ucab.fcpclient4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpclient4j.logica.mensajes.salida.Commit;
import com.ucab.fcpclient4j.logica.mensajes.salida.HandShake;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class App 
{
    public static void main( String[] args ) throws NoSuchAlgorithmException, IOException, InterruptedException
    {
        System.setProperty("javax.net.ssl.trustStore", LeerPropiedad.TRUSTSTOREPATH );
        System.setProperty("javax.net.ssl.trustStorePassword ", LeerPropiedad.TRUSTSTOREPASSWORD );


        //TODO: Realizar logica local antes de iniciar conexion y selecccion de servidores

        ComandoSeleccionarServidor comando = new ComandoSeleccionarServidor();
        if(comando.ejecutar())
        {
            //TODO: Crear comando para enviar HandShake.
            ServerManager.obtenerGlobal().getServidorPrincipal().enviarCaracteres( new HandShake() );
            String mensajeUTF = ServerManager.obtenerGlobal().getServidorPrincipal().recibirCaracteres();
            ServerManager.obtenerGlobal().getServidorPrincipal().getConexion().close();

            PaqueteEntrada mensajeEntrada = new PaqueteEntrada( mensajeUTF );
            MensajeManager.obtenerMensajeManager().ProcesarMensaje( mensajeEntrada, null );
            System.out.println( "mensaje recibido"+ mensajeUTF );

            ComandoRealizarCommit comandoCommit = new ComandoRealizarCommit( "probando.txt"  );
            comandoCommit.ejecutar();
        }
    }
}
