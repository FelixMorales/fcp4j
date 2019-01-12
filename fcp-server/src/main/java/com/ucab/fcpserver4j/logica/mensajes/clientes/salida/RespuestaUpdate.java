package com.ucab.fcpserver4j.logica.mensajes.clientes.salida;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;

public class RespuestaUpdate extends MensajeSalida
{
    public RespuestaUpdate( Archivo archivo)
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.RESPUESTA_UPDATE );
        agregarElemento( PropiedadesMensajes.NOMBREARCHIVO, archivo.getNombre() );
        agregarElemento( PropiedadesMensajes.CONTENIDOARCHIVO, archivo.getContenido());

    }
}
