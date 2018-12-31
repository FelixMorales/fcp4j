package com.ucab.fcpserver4j.logica.entrada.servidor;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.entrada.PaqueteEntrada;

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
