package com.ucab.fcpclient4j.logica.mensajes.salida;


import com.ucab.fcpclient4j.logica.mensajes.core.MensajeSalida;
import com.ucab.fcpclient4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpclient4j.logica.mensajes.core.constantes.PropiedadesMensajes;

public class HandShake extends MensajeSalida
{
    public HandShake()
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.HANDS_SHAKE );
        agregarElemento( PropiedadesMensajes.SERVIDOR, false );
    }
}
