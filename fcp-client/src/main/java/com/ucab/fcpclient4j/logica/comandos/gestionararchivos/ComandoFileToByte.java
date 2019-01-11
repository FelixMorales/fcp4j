package com.ucab.fcpclient4j.logica.comandos.gestionararchivos;

import com.ucab.fcpclient4j.logica.comandos.Comando;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class ComandoFileToByte extends Comando<byte[]>
{
    File archivo;
    private String separador = File.separator;
    public ComandoFileToByte(String nombreArcivo)
    {
        archivo = new File("."+separador+"salida"+separador+nombreArcivo);
    }
    @Override
    public byte[] ejecutar() throws IOException
    {

        byte[] retorno = new byte[0];

        try
        {
            retorno = Files.readAllBytes(archivo.toPath());
        }
        catch ( IOException e)
        {
            System.out.println( "Error al leer el archivo." + e.toString() );
            throw e;
        }

        return retorno;
    }
}
