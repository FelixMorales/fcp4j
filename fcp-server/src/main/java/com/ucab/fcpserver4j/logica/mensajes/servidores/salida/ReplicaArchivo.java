package com.ucab.fcpserver4j.logica.mensajes.servidores.salida;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;

public class ReplicaArchivo extends MensajeSalida
{
    public ReplicaArchivo( Archivo archivo, boolean almacenarArchivo )
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.REPLICAR_ARCHIVO );
        agregarElemento( PropiedadesMensajes.SERVIDOR, true );
        agregarElemento( PropiedadesMensajes.ALMACENAR_ARCHIVO, almacenarArchivo);
        agregarElemento( PropiedadesMensajes.NOMBREARCHIVO, archivo.getNombre());
        agregarElemento( PropiedadesMensajes.AUTORARCHIVO, archivo.getAutor());
        agregarElemento( PropiedadesMensajes.CONTENIDOARCHIVO, archivo.getContenido());
        agregarElemento( PropiedadesMensajes.VERSIONARCHIVO, archivo.getVersion());
        agregarElemento( PropiedadesMensajes.LOCALIZACIONES_ARCHIVO, concatenarLocalizaciones( archivo ));

    }

    private String concatenarLocalizaciones(Archivo archivo)
    {
        String retorno = "";

        for(String localizacion : archivo.getLocalizacion())
        {
            retorno = retorno + localizacion + ";";
        }

        return  retorno;
    }
}
