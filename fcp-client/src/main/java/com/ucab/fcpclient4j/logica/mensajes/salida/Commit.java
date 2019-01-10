package com.ucab.fcpclient4j.logica.mensajes.salida;


import com.ucab.fcpclient4j.comun.entidades.Archivo;
import com.ucab.fcpclient4j.logica.mensajes.core.MensajeSalida;
import com.ucab.fcpclient4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpclient4j.logica.mensajes.core.constantes.PropiedadesMensajes;

public class Commit extends MensajeSalida
{

    public Commit( Archivo archivo )
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.COMMIT );
        agregarElemento( PropiedadesMensajes.SERVIDOR, false );
        agregarElemento( PropiedadesMensajes.NOMBREARCHIVO, archivo.getNombre() );
        agregarElemento( PropiedadesMensajes.CONTENIDOARCHIVO, archivo.getContenido() );
        agregarElemento( PropiedadesMensajes.AUTORARCHIVO, archivo.getAutor() );

    }
}
