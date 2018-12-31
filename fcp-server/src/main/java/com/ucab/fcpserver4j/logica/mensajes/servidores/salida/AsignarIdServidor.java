package com.ucab.fcpserver4j.logica.mensajes.servidores.salida;

import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpserver4j.comun.utilidades.Global;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeSalida;

public class AsignarIdServidor extends MensajeSalida
{
    public AsignarIdServidor()
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.ASIGNAR_ID_SERVIDOR );
        agregarElemento( PropiedadesMensajes.SERVIDOR, true );
        agregarElemento( PropiedadesMensajes.NOMBRESERVIDOR, Global.obtenerGlobal().getServidorLocal().getNombre() );
        agregarElemento( PropiedadesMensajes.IDSERVIDOR, Global.obtenerGlobal().getServidorLocal().getId() );
    }
}
