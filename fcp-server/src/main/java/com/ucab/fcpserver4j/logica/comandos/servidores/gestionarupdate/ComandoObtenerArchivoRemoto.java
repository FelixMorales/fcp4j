package com.ucab.fcpserver4j.logica.comandos.servidores.gestionarupdate;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.mensajes.clientes.salida.EnviarError;
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
            ServerManager.obtenerSingleton().getClienteActivo().enviarCaracteres( new EnviarError( LeerPropiedad.ERROR_GENERAL ) );
            e.printStackTrace();
        }
        catch ( NullPointerException exc )
        {
            ServerManager.obtenerSingleton().getClienteActivo().enviarCaracteres( new EnviarError( LeerPropiedad.ERROR_GENERAL ) );
            System.out.println( "El servidor remoto donde se localiza el archivo se encuentra desconectado." );
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
