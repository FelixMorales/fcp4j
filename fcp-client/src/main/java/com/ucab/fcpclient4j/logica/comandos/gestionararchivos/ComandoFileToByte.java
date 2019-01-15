package com.ucab.fcpclient4j.logica.comandos.gestionararchivos;

import com.ucab.fcpclient4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpclient4j.logica.comandos.Comando;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ComandoFileToByte extends Comando<byte[]>
{
    File archivo;
    public ComandoFileToByte(String nombreArcivo)
    {
        archivo = new File( "." + LeerPropiedad.SEPARADOR_ARCHIVO + "archivos" + LeerPropiedad.SEPARADOR_ARCHIVO + nombreArcivo);
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
