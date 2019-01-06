package com.ucab.fcpserver4j.logica.comandos;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Comando<T>
{
  public abstract T ejecutar() throws IOException;
}
