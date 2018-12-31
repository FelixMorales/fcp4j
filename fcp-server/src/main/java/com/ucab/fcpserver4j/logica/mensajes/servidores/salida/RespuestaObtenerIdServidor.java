package com.ucab.fcpserver4j.logica.mensajes.servidores.salida;

import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpserver4j.comun.utilidades.Global;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeSalida;

public class RespuestaObtenerIdServidor extends MensajeSalida
{
    public RespuestaObtenerIdServidor( )
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.GENERAR_ID_LOCAL );
        agregarElemento( PropiedadesMensajes.SERVIDOR, true );
        agregarElemento( PropiedadesMensajes.NOMBRESERVIDOR, Global.obtenerGlobal().getServidorLocal().getNombre() );
        agregarElemento( PropiedadesMensajes.IDSERVIDOR,Global.obtenerGlobal().getServidorLocal().getId() );
        agregarElemento( PropiedadesMensajes.SOYPRINCIPAL,Global.obtenerGlobal().getServidorLocal().isPrincipal() );
    }
}
