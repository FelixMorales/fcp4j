package com.ucab.fcpserver4j.logica.mensajes.servidores.salida;

import com.ucab.fcpserver4j.logica.mensajes.core.MensajeSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;

public class RespuestaPeticionPersistencia extends MensajeSalida
{
    public RespuestaPeticionPersistencia(byte[] contenidoPersistencia)
    {
        super();
        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.RESPUESTA_PETICION_PERSISTENCIA );
        agregarElemento( PropiedadesMensajes.SERVIDOR, true );
        agregarElemento( PropiedadesMensajes.CONTENIDO_PERSISTENCIA, contenidoPersistencia );
    }
}
