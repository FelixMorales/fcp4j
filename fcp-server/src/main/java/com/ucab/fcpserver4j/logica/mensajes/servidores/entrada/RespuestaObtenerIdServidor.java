package com.ucab.fcpserver4j.logica.mensajes.servidores.entrada;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntrada;

public class RespuestaObtenerIdServidor implements IMensajeEntrada
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Servidor servidor )
    {
        servidor.setId( mensaje.obtenerInt( PropiedadesMensajes.IDSERVIDOR ) );
        servidor.setPrincipal( mensaje.obtenerBoolean( PropiedadesMensajes.SOYPRINCIPAL ) );
    }
}
