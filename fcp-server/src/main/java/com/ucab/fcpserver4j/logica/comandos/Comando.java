package com.ucab.fcpserver4j.logica.comandos;

import com.ucab.fcpserver4j.comun.excepciones.ArchivoEnOtroServidorException;
import com.ucab.fcpserver4j.comun.excepciones.ArchivoNoEncontradoException;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Comando<T>
{
  public abstract T ejecutar() throws IOException, ArchivoNoEncontradoException, ArchivoEnOtroServidorException;
}
