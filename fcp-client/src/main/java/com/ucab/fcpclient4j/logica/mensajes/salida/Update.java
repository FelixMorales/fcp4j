package com.ucab.fcpclient4j.logica.mensajes.salida;


import com.ucab.fcpclient4j.comun.entidades.Archivo;
import com.ucab.fcpclient4j.logica.mensajes.core.MensajeSalida;
import com.ucab.fcpclient4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpclient4j.logica.mensajes.core.constantes.PropiedadesMensajes;

public class Update extends MensajeSalida
{

    public Update( Archivo archivo )
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.UPDATE );
        agregarElemento( PropiedadesMensajes.SERVIDOR, false );
        agregarElemento( PropiedadesMensajes.NOMBREARCHIVO, archivo.getNombre() );

    }
}
