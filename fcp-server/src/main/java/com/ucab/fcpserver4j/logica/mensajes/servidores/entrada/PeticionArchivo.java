package com.ucab.fcpserver4j.logica.mensajes.servidores.entrada;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.logica.comandos.gestionararchivos.ComandoFileToByte;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntrada;
import com.ucab.fcpserver4j.logica.mensajes.servidores.salida.RespuestaPeticionArchivo;

import java.io.IOException;

public class PeticionArchivo implements IMensajeEntrada
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Servidor servidor )
    {
        try
        {
            Archivo archivo = new Archivo();

            archivo.setNombre( mensaje.obtenerString( PropiedadesMensajes.NOMBREARCHIVO ) );
            archivo.setVersion( mensaje.obtenerInt( PropiedadesMensajes.VERSIONARCHIVO ) );

            ComandoFileToByte comandoFileToByte = new ComandoFileToByte( archivo );
            archivo.setContenido(comandoFileToByte.ejecutar());

            System.out.println( "Me llego la peticion de archivo remoto - "+archivo.getNombre() );
            servidor.getConexion().enviarCaracteres( new RespuestaPeticionArchivo( archivo ) );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }
}
