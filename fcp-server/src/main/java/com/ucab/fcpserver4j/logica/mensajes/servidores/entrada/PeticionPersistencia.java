package com.ucab.fcpserver4j.logica.mensajes.servidores.entrada;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.logica.comandos.gestionararchivos.ComandoFileToByte;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntrada;
import com.ucab.fcpserver4j.logica.mensajes.servidores.salida.RespuestaPeticionPersistencia;

import java.io.IOException;

public class PeticionPersistencia implements IMensajeEntrada
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Servidor servidor )
    {
        ComandoFileToByte comandoPersistencia = new ComandoFileToByte(  );

        try
        {
            byte[] contenidoPersistencia = comandoPersistencia.ejecutar();
            servidor.getConexion().enviarCaracteres( new RespuestaPeticionPersistencia( contenidoPersistencia ) );

        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }

    }
}
