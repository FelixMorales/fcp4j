package com.ucab.fcpserver4j.logica.comandos.clientes.gestionarupdate;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.excepciones.ArchivoEnOtroServidorException;
import com.ucab.fcpserver4j.comun.excepciones.ArchivoNoEncontradoException;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.persistencia.DatabaseManager;

import java.io.IOException;

public class ComandoRecibirUpdate extends Comando<Archivo>
{

    Archivo archivo;
    public ComandoRecibirUpdate( Archivo archivo )
    {
        this.archivo = archivo;
    }

    @Override
    public Archivo ejecutar() throws IOException, ArchivoNoEncontradoException, ArchivoEnOtroServidorException
    {

        Archivo retorno;
        archivo = DatabaseManager.obtenerSingleton().ObtenerPersistenciaArchivo( archivo );

        if(archivo.getLocalizacion() == null || archivo.getLocalizacion().size() < 1)
        {
            throw new ArchivoNoEncontradoException( "Archivo no encontrado" );
        }
        else
        {
            ComandoBuscarArchivo comandoBuscarArchivo = new ComandoBuscarArchivo( archivo );
            retorno = comandoBuscarArchivo.ejecutar();
        }
        return retorno;
    }


}
