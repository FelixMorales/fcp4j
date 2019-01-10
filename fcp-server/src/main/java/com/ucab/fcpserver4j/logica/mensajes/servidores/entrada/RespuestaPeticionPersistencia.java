package com.ucab.fcpserver4j.logica.mensajes.servidores.entrada;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.gestionararchivos.ComandoBytesToFile;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntrada;
import com.ucab.fcpserver4j.persistencia.DatabaseManager;

import java.io.IOException;

public class RespuestaPeticionPersistencia implements IMensajeEntrada
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Servidor servidor )
    {
        byte[] contenidoPersistencia = mensaje.obtenerBytes( PropiedadesMensajes.CONTENIDO_PERSISTENCIA );
        ComandoBytesToFile comando = new ComandoBytesToFile( contenidoPersistencia );
        try
        {
            comando.ejecutar();
            ServerManager.obtenerGlobal().getServidorLocal()
                    .setHistorico( DatabaseManager.obtenerSingleton().ObtenerHistorico() );
            System.out.println( "Historico actualizado:"+ServerManager.obtenerGlobal()
                    .getServidorLocal().getHistorico() );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }

    }
}
