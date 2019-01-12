package com.ucab.fcpserver4j.logica.mensajes.servidores.salida;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;

public class RespuestaPeticionArchivo extends MensajeSalida
{
    public RespuestaPeticionArchivo( Archivo archivo )
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.RESPUESTA_PETICION_ARCHIVO );
        agregarElemento( PropiedadesMensajes.SERVIDOR, true );
        agregarElemento( PropiedadesMensajes.NOMBREARCHIVO, archivo.getNombre() );
        agregarElemento( PropiedadesMensajes.VERSIONARCHIVO, archivo.getVersion() );
        agregarElemento( PropiedadesMensajes.CONTENIDOARCHIVO, archivo.getContenido() );
    }
}
