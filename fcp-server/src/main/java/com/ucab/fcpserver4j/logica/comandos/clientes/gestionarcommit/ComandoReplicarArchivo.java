package com.ucab.fcpserver4j.logica.comandos.clientes.gestionarcommit;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.comandos.gestionararchivos.ComandoBytesToFile;
import com.ucab.fcpserver4j.logica.mensajes.servidores.salida.ReplicaArchivo;

import java.io.IOException;

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


        for(Servidor servidorActivo : ServerManager.obtenerSingleton().getServidoresActivos())
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
            if(replicar( ServerManager.obtenerSingleton().getServidorLocal() ))
            {
                ComandoBytesToFile comando = new ComandoBytesToFile( archivo );
                comando.ejecutar();

                System.out.println( "Archivo guardado en el directorio local." );
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
            System.out.println( String.format( LeerPropiedad.INFO_ARCHIVO_REPLICA,
                                               servidor.getIp(), servidor.getPuerto(), servidor.getNombre()));
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
