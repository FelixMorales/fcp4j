package com.ucab.fcpserver4j.logica.comandos.clientes.gestionarupdate;

import com.ucab.fcpserver4j.comun.entidades.Archivo;import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.mensajes.clientes.salida.RespuestaUpdate;

import java.io.IOException;

public class ComandoEnviarArchivoRemoto extends Comando<Boolean>
{
    Archivo archivo;
    public ComandoEnviarArchivoRemoto(Archivo archivo)
    {
        this.archivo = archivo;
    }
    @Override
    public Boolean ejecutar()
    {
        try
        {
            ServerManager.obtenerSingleton().getClienteActivo().enviarCaracteres( new RespuestaUpdate( archivo ) );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }

        return null;
    }
}
