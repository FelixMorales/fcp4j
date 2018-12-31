package com.ucab.fcpserver4j.servicio;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.Global;
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
            Global.obtenerGlobal().getServidoresActivos().remove( servidor );
            //verificar si es principal
            System.out.println( "muere servidor  "+e.toString() );
            System.out.println( e.toString() );
        }
    }
}
