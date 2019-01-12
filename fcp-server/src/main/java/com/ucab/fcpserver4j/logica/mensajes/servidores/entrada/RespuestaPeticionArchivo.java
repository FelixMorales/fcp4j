package com.ucab.fcpserver4j.logica.mensajes.servidores.entrada;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.logica.comandos.clientes.gestionarupdate.ComandoEnviarArchivoRemoto;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntrada;

public class RespuestaPeticionArchivo implements IMensajeEntrada
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Servidor servidor )
    {
        Archivo archivo = new Archivo();

        archivo.setNombre( mensaje.obtenerString( PropiedadesMensajes.NOMBREARCHIVO ) );
        archivo.setVersion( mensaje.obtenerInt( PropiedadesMensajes.VERSIONARCHIVO ) );
        archivo.setContenido( mensaje.obtenerBytes( PropiedadesMensajes.CONTENIDOARCHIVO ) );

        ComandoEnviarArchivoRemoto comando = new ComandoEnviarArchivoRemoto( archivo );
        comando.ejecutar();


    }
}
