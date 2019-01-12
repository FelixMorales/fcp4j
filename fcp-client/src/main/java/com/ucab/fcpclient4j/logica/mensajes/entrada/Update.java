package com.ucab.fcpclient4j.logica.mensajes.entrada;

import com.ucab.fcpclient4j.comun.entidades.Archivo;
import com.ucab.fcpclient4j.comun.utilidades.Conexion;
import com.ucab.fcpclient4j.comun.utilidades.ServerManager;
import com.ucab.fcpclient4j.logica.comandos.gestionararchivos.ComandoBytesToFile;
import com.ucab.fcpclient4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpclient4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpclient4j.logica.mensajes.core.interfaces.IMensajeEntrada;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Update implements IMensajeEntrada
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Conexion servidor )
    {
        try
        {
            Archivo archivo = new Archivo();
            archivo.setNombre( mensaje.obtenerString( PropiedadesMensajes.NOMBREARCHIVO ) );
            archivo.setContenido( mensaje.obtenerBytes( PropiedadesMensajes.CONTENIDOARCHIVO ) );

            ComandoBytesToFile comando = new ComandoBytesToFile( archivo.getContenido(), archivo.getNombre() );
            comando.ejecutar();

            System.out.println( "Archivo creado con exito" );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }
}
