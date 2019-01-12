package com.ucab.fcpserver4j.logica.comandos.servidores.gestionarupdate;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.mensajes.clientes.salida.RespuestaCommit;
import com.ucab.fcpserver4j.logica.mensajes.servidores.salida.EnviarPeticionArchivo;

import java.io.IOException;

public class ComandoObtenerArchivoRemoto extends Comando<Boolean>
{
    private Archivo archivo;

    public ComandoObtenerArchivoRemoto( Archivo archivo )
    {
        this.archivo = archivo;
    }

    @Override
    public Boolean ejecutar() throws IOException
    {

        Servidor servidorActivo = obtenerServidor();

        try
        {
            servidorActivo.getConexion().enviarCaracteres( new EnviarPeticionArchivo( archivo ) );
        }
        catch ( IOException e )
        {
            // Enviar mensaje servidor remoto desconectado.
            ServerManager.obtenerSingleton().getClienteActivo().enviarCaracteres( new RespuestaCommit() );
            e.printStackTrace();
        }
        catch ( NullPointerException exc )
        {
            // Enviar mensaje servidor remoto desconectado.
            ServerManager.obtenerSingleton().getClienteActivo().enviarCaracteres( new RespuestaCommit() );
            exc.printStackTrace();
        }


        return true;
    }

    private Servidor obtenerServidor()
    {
        Servidor retorno = null;

        for(Servidor servidor : ServerManager.obtenerSingleton().getServidoresActivos())
        {
            if(!servidor.isLocal())
            {
                if(servidorLocalizacion( servidor ))
                {
                    retorno = servidor;
                    break;
                }
            }
        }

        return retorno;
    }

    private boolean servidorLocalizacion(Servidor servidor)
    {
        boolean retorno = false;

        for(String localizacion : archivo.getLocalizacion())
        {
            if(localizacion.toLowerCase().equals( servidor.getNombre().toLowerCase() ))
            {
                retorno = true;
                break;
            }
        }

        return retorno;
    }
}
