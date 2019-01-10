package com.ucab.fcpserver4j.logica.comandos.clientes.gestionarcommit;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.comandos.gestionararchivos.ComandoBytesToFile;
import com.ucab.fcpserver4j.logica.mensajes.servidores.salida.ReplicaArchivo;

import java.io.IOException;
import java.util.List;

public class ComandoReplicarArchivo extends Comando<Boolean>
{
    Archivo archivo;
    public ComandoReplicarArchivo( Archivo archivo )
    {
        this.archivo = archivo;
    }

    @Override
    public Boolean ejecutar() throws IOException
    {


        for(Servidor servidorActivo : ServerManager.obtenerGlobal().getServidoresActivos())
        {
            if(servidorActivo.isLocal())
            {
                almacenarArchivoLocal();
            }
            else
            {
                enviarReplicaArchivo( servidorActivo );
            }
        }

        return true;
    }

    private void almacenarArchivoLocal()
    {

        try
        {
            if(replicar( ServerManager.obtenerGlobal().getServidorLocal() ))
            {
                ComandoBytesToFile comando = new ComandoBytesToFile( archivo );
                comando.ejecutar();
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

    private void enviarReplicaArchivo(Servidor servidor)
    {
        try
        {
            servidor.getConexion().enviarCaracteres( new ReplicaArchivo( archivo, replicar( servidor ) ) );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

    private boolean replicar(Servidor servidor)
    {
        boolean retorno = false;

        for(String localizacion : archivo.getLocalizacion())
        {
            if(servidor.getNombre().equals( localizacion.toLowerCase() ))
            {
                retorno = true;
                break;
            }
        }

        return  retorno;
    }

}
