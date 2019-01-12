package com.ucab.fcpserver4j.logica.mensajes.servidores.salida;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;

public class EnviarPeticionArchivo extends MensajeSalida
{
    public EnviarPeticionArchivo( Archivo archivo )
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.ENVIAR_PETICION_ARCHIVO );
        agregarElemento( PropiedadesMensajes.SERVIDOR, true );
        agregarElemento( PropiedadesMensajes.NOMBREARCHIVO, archivo.getNombre() );
        agregarElemento( PropiedadesMensajes.VERSIONARCHIVO, archivo.getVersion() );
    }
}
