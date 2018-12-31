package com.ucab.fcpserver4j.logica.salida.servidor;

import com.ucab.fcpserver4j.comun.utilidades.CodigosSalida;
import com.ucab.fcpserver4j.comun.utilidades.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.salida.MensajeSalida;

public class ObtenerIdServidor extends MensajeSalida
{
    public ObtenerIdServidor( String nombreServidor )
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.OBTENER_ID );
        agregarElemento( PropiedadesMensajes.SERVIDOR, true );
        agregarElemento( PropiedadesMensajes.NOMBRESERVIDOR, nombreServidor );
    }
}
