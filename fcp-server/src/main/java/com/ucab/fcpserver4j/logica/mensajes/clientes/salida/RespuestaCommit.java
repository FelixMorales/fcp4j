package com.ucab.fcpserver4j.logica.mensajes.clientes.salida;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;

public class RespuestaCommit extends MensajeSalida
{
    public RespuestaCommit()
    {
        super();

        Servidor localhost = ServerManager.obtenerGlobal().getServidorLocal();
        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.RESPONDER_COMMIT );
        agregarElemento( PropiedadesMensajes.NOMBRESERVIDOR, localhost.getNombre() );
        agregarElemento( PropiedadesMensajes.SOYPRINCIPAL, localhost.isPrincipal() );

    }
}
