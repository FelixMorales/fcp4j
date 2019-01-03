package com.ucab.fcpclient4j.logica.mensajes.salida;


import com.ucab.fcpclient4j.logica.mensajes.core.MensajeSalida;
import com.ucab.fcpclient4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpclient4j.logica.mensajes.core.constantes.PropiedadesMensajes;

public class PruebaSalida extends MensajeSalida
{
    public PruebaSalida()
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.PRUEBA_CLIENTE );
        agregarElemento( PropiedadesMensajes.SERVIDOR, false );
        agregarElemento( PropiedadesMensajes.NOMBREARCHIVO, "prueba.txt" );
    }
}
