package com.ucab.fcpserver4j.logica.comandos.gestionararchivos;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.logica.comandos.Comando;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ComandoBytesToFile extends Comando<Boolean>
{
    File archivo;
    private String separador = File.separator;
    byte[] contenidoArchivo;
    public ComandoBytesToFile( Archivo archivoCommit )
    {
        archivo = new File( "." + separador + "persistencia" + separador + "archivos" + separador
                            + archivoCommit.getNombre() + LeerPropiedad.FORMATO_VERSION_ARCHIVO
                            + archivoCommit.getVersion());

        this.contenidoArchivo = archivoCommit.getContenido();
    }

    public ComandoBytesToFile(byte[] contenidoDB)
    {
        archivo = new File("."+separador+"persistencia"+separador+"db"+separador+"archivosServidor.db");
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
