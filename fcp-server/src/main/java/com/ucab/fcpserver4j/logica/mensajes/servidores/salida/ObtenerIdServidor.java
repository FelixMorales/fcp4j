package com.ucab.fcpserver4j.logica.mensajes.servidores.salida;

import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeSalida;

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
