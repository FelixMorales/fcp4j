package com.ucab.fcpserver4j.logica.comandos.clientes.gestionarupdate;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.excepciones.ArchivoEnOtroServidorException;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.comandos.gestionararchivos.ComandoFileToByte;

import java.io.IOException;


public class ComandoBuscarArchivo extends Comando<Archivo>
{
    private Archivo archivo;
    public ComandoBuscarArchivo( Archivo archivo )
    {
        this.archivo = archivo;
    }

    @Override
    public Archivo ejecutar() throws IOException, ArchivoEnOtroServidorException
    {
        Archivo retorno;

        if(verificarLocal())
        {
            ComandoFileToByte comando = new ComandoFileToByte( archivo );
            archivo.setContenido( comando.ejecutar() );
            retorno = archivo;
        }
        else
        {
            throw new ArchivoEnOtroServidorException( archivo );
        }

        return retorno;
    }

    private boolean verificarLocal()
    {
        boolean retorno = false;
        for(String localizacion : archivo.getLocalizacion())
        {
            if(localizacion.toLowerCase()
                    .equals( ServerManager.obtenerSingleton().getServidorLocal().getNombre().toLowerCase() ) )
            {
                retorno = true;
                break;
            }
        }

        return retorno;
    }
}
