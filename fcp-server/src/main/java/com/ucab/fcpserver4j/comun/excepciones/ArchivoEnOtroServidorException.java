package com.ucab.fcpserver4j.comun.excepciones;

import com.ucab.fcpserver4j.comun.entidades.Archivo;

public class ArchivoEnOtroServidorException extends Exception
{
    private Archivo archivo;
    public ArchivoEnOtroServidorException(Archivo archivo)
    {
        super();

        this.archivo = archivo;

    }

    public Archivo getArchivo(){ return archivo; }
}
