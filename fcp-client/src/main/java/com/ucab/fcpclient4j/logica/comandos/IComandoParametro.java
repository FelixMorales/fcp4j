package com.ucab.fcpclient4j.logica.comandos;

import java.io.IOException;

public interface IComandoParametro
{
    void ejecutarArgumento(String[] parametros) throws IOException;
}
