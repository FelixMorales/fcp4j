package com.ucab.fcpclient4j.logica.comandos;

import java.io.IOException;

public abstract class Comando<T>
{
  public abstract T ejecutar() throws IOException;
}
