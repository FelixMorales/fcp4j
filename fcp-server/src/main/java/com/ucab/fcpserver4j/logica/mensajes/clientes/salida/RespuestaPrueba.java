package com.ucab.fcpserver4j.logica.mensajes.clientes.salida;

import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;

public class RespuestaPrueba extends MensajeSalida
{
    public RespuestaPrueba()
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, 1 );
        agregarElemento( PropiedadesMensajes.SERVIDOR, true );
        agregarElemento( PropiedadesMensajes.NOMBRESERVIDOR, ServerManager.obtenerGlobal().getServidorLocal().getNombre() );
        agregarElemento( PropiedadesMensajes.SOYPRINCIPAL, ServerManager.obtenerGlobal().getServidorLocal().isPrincipal() );
    }
}
