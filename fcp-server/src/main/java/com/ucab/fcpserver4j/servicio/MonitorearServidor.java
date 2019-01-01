package com.ucab.fcpserver4j.servicio;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.servidores.seleccionservidorprincipal.ComandoSeleccionarPrincipal;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeManager;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;

import java.io.IOException;

/**
 * Name:                  MonitorearServidor
 * Description:           Matiene la conexión abierta con los otros servidores remotos.
 *
 * @since 30/12/18
 *
 */
public class MonitorearServidor implements Runnable
{
    Servidor servidor;

    public MonitorearServidor(Servidor servidor)
    {
        this.servidor = servidor;
    }

    @Override
    public void run()
    {
        escucharServidor();
    }

    public void escucharServidor()
    {
        try
        {
            while ( true )
            {
                String mensajeUTF = servidor.getConexion().recibirCaracteres();

                PaqueteEntrada mensajeEntrada = new PaqueteEntrada( mensajeUTF );

                MensajeManager.obtenerMensajeManager().ProcesarMensajeServidor( mensajeEntrada, servidor );
            }
        }
        catch( IOException e )
        {
            ComandoSeleccionarPrincipal comando = new ComandoSeleccionarPrincipal( servidor );
            comando.ejecutar();
            System.out.println( "muere servidor  "+servidor.getNombre()+" - "+servidor.getIp()+servidor.getPuerto() );
        }
    }
}
