package com.ucab.fcpserver4j.logica.comandos.gestionararchivos;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.logica.comandos.Comando;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ComandoBytesToFile extends Comando<Boolean>
{
    File archivo;
    byte[] contenidoArchivo;
    public ComandoBytesToFile( Archivo archivoCommit )
    {
        archivo = new File("./persistencia/archivos/"+archivoCommit.getNombre()+"&&v="+archivoCommit.getVersion());
        this.contenidoArchivo = archivoCommit.getContenido();
    }

    public ComandoBytesToFile(byte[] contenidoDB)
    {
        archivo = new File("./persistencia/db/archivosServidor.db");
        this.contenidoArchivo = contenidoDB;
    }

    @Override
    public Boolean ejecutar() throws IOException, FileNotFoundException
    {
        Boolean retorno = false;
        try
        {

            OutputStream os = new FileOutputStream( archivo );
            os.write(contenidoArchivo);
            os.close();
            retorno = true;
        }
        catch ( FileNotFoundException exc) {
            System.out.println("Error al transformar el archivo " + exc.toString());
            throw exc;
        }
        catch (  IOException e )
        {
            System.out.println("Error al escribir sobre el archivo " + e.toString());
            throw e;
        }
        return retorno;
    }
}
