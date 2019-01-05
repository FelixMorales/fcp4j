package com.ucab.fcpclient4j.logica.comandos.gestionararchivos;

import com.ucab.fcpclient4j.logica.comandos.Comando;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ComandoBytesToFile extends Comando<Boolean>
{
    File archivo;
    byte[] contenidoArchivo;
    public ComandoBytesToFile(byte[] contenidoArchivo, String nombreArchivo)
    {
        archivo = new File("./"+nombreArchivo);
        this.contenidoArchivo = contenidoArchivo;
    }
    @Override
    public Boolean ejecutar()
    {
        Boolean retorno = false;
        try
        {

            OutputStream os = new FileOutputStream( archivo );
            os.write(contenidoArchivo);
            os.close();
            retorno = true;
        }

        catch (Exception e) {
            System.out.println("Error al transformar el archivo " + e);
        }

        return retorno;
    }
}
