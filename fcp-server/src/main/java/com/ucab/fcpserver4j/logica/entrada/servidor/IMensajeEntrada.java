package com.ucab.fcpserver4j.logica.entrada.servidor;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.logica.entrada.PaqueteEntrada;

public interface IMensajeEntrada
{
    void ejecutar( PaqueteEntrada mensaje, Servidor servidor);
}
