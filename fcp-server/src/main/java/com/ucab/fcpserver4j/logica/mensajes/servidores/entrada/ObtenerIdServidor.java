package com.ucab.fcpserver4j.logica.mensajes.servidores.entrada;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntrada;
import com.ucab.fcpserver4j.logica.mensajes.servidores.salida.RespuestaObtenerIdServidor;

import java.io.IOException;

public class ObtenerIdServidor implements IMensajeEntrada
{

    @Override
    public void ejecutar( PaqueteEntrada mensaje, Servidor servidor )
    {

        try
        {
            servidor.getConexion().enviarCaracteres( new RespuestaObtenerIdServidor() );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }


    }
}
