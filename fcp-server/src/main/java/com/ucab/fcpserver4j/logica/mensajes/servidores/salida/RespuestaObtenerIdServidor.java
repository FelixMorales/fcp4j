package com.ucab.fcpserver4j.logica.mensajes.servidores.salida;

import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeSalida;

public class RespuestaObtenerIdServidor extends MensajeSalida
{
    public RespuestaObtenerIdServidor( )
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.ASIGNAR_ID_SERVIDORES );
        agregarElemento( PropiedadesMensajes.SERVIDOR, true );
        agregarElemento( PropiedadesMensajes.IDSERVIDOR, ServerManager.obtenerSingleton().getServidorLocal().getId() );
        agregarElemento( PropiedadesMensajes.SOYPRINCIPAL, ServerManager.obtenerSingleton().getServidorLocal().isPrincipal() );
        agregarElemento( PropiedadesMensajes.HISTORICO_SERVIDOR, ServerManager.obtenerSingleton().getServidorLocal().getHistorico() );
    }
}
