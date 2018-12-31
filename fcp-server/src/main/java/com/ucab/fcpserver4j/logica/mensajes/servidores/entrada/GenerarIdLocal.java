package com.ucab.fcpserver4j.logica.mensajes.servidores.entrada;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntrada;

public class GenerarIdLocal implements IMensajeEntrada
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Servidor servidor )
    {
        System.out.println( "Generando Id" );
        servidor.setId( mensaje.obtenerInt( PropiedadesMensajes.IDSERVIDOR ) );
        servidor.setPrincipal( mensaje.obtenerBoolean( PropiedadesMensajes.SOYPRINCIPAL ) );
        System.out.println( mensaje.GetMensaje() );
    }
}
