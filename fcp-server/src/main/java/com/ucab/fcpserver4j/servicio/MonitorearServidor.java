package com.ucab.fcpserver4j.servicio;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.Global;
import com.ucab.fcpserver4j.comun.utilidades.MensajeManager;
import com.ucab.fcpserver4j.logica.entrada.PaqueteEntrada;

import java.io.IOException;

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
                String mensaje = servidor.getConexion().recibirCaracteres();
                MensajeManager.obtenerMensajeManager().ejecutarMensaje( new PaqueteEntrada( mensaje ), servidor );
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
