package com.ucab.fcpclient4j.logica.comandos.gestionararchivos;

import com.ucab.fcpclient4j.logica.comandos.Comando;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

public class ComandoFileToByte extends Comando<byte[]>
{
    File archivo;
    public ComandoFileToByte(String nombreArcivo)
    {
        archivo = new File("./"+nombreArcivo);
    }
    @Override
    public byte[] ejecutar()
    {

        byte[] retorno = new byte[0];

        try
        {
            retorno = Files.readAllBytes(archivo.toPath());
        }
        catch (Exception e)
        {
            System.out.println( "Error al leer el archivo." + e.toString() );
        }

        return retorno;
    }
}
