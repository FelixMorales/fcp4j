package com.ucab.fcpserver4j.logica.comandos.gestionararchivos;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.logica.comandos.Comando;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ComandoFileToByte extends Comando<byte[]>
{
    File archivo;
    private String separator = File.separator;
    public ComandoFileToByte( Archivo archivo)
    {
        this.archivo = new File("."+separator+"persistencia"+separator+"archivos"
                                +separator+generarNombre( archivo ));
    }

    public ComandoFileToByte()
    {
        this.archivo = new File("./persistencia/db/archivosServidor.db");
    }

    @Override
    public byte[] ejecutar() throws IOException
    {

        byte[] retorno = new byte[0];

        try
        {
            retorno = Files.readAllBytes( archivo.toPath());
        }
        catch ( IOException e)
        {
            System.out.println( "Error al leer el archivo." + e.toString() );
            throw e;
        }

        return retorno;
    }

    private String generarNombre(Archivo archivo)
    {
        StringBuilder sb = new StringBuilder(  );
        sb.append( archivo.getNombre() );
        sb.append( LeerPropiedad.FORMATO_VERSION_ARCHIVO );
        sb.append( archivo.getVersion() );

        return sb.toString();
    }
}
