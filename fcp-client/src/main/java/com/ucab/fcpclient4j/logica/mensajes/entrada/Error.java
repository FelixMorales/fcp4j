package com.ucab.fcpclient4j.logica.mensajes.entrada;

import com.ucab.fcpclient4j.comun.utilidades.Conexion;
import com.ucab.fcpclient4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpclient4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpclient4j.logica.mensajes.core.interfaces.IMensajeEntrada;

public class Error implements IMensajeEntrada
{

    @Override
    public void ejecutar( PaqueteEntrada mensaje, Conexion servidor )
    {
        System.out.print( "HA OCURRIDO UN ERROR:" );
        System.out.println( mensaje.obtenerString( PropiedadesMensajes.MENSAJE ) );
    }
}
