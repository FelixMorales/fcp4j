package com.ucab.fcpserver4j.logica.mensajes.servidores.entrada;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.logica.comandos.servidores.gestionareplica.ComandoRecibirReplica;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntrada;

import java.util.ArrayList;
import java.util.List;

public class ReplicarArchivo implements IMensajeEntrada
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Servidor servidor )
    {
        boolean almacenarArchivo = mensaje.obtenerBoolean( PropiedadesMensajes.ALMACENAR_ARCHIVO );

        Archivo archivo = new Archivo();

        archivo.setNombre( mensaje.obtenerString( PropiedadesMensajes.NOMBREARCHIVO ) );
        archivo.setAutor( mensaje.obtenerString( PropiedadesMensajes.AUTORARCHIVO ) );
        archivo.setContenido( mensaje.obtenerBytes( PropiedadesMensajes.CONTENIDOARCHIVO ) );
        archivo.setVersion( mensaje.obtenerInt( PropiedadesMensajes.VERSIONARCHIVO ) );

        String localizaciones = mensaje.obtenerString( PropiedadesMensajes.LOCALIZACIONES_ARCHIVO );

        archivo.setLocalizacion( localizacionesArchivo( localizaciones ) );

        ComandoRecibirReplica comando = new ComandoRecibirReplica( archivo, almacenarArchivo );
        comando.ejecutar();


    }

    private List<String> localizacionesArchivo(String localizaciones)
    {
        List<String> retorno = new ArrayList<>(  );

        String[] array = localizaciones.split( ";" );

        for(String elemento : array)
        {
            retorno.add( elemento );
        }

        return retorno;
    }
}
