package com.ucab.fcpserver4j.logica.salida.servidor;

import com.ucab.fcpserver4j.comun.utilidades.CodigosSalida;
import com.ucab.fcpserver4j.comun.utilidades.Global;
import com.ucab.fcpserver4j.comun.utilidades.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.salida.MensajeSalida;

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
