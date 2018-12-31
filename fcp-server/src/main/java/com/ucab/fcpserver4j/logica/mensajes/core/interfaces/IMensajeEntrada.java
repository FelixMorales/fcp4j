package com.ucab.fcpserver4j.logica.mensajes.core.interfaces;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;

public interface IMensajeEntrada
{
    void ejecutar( PaqueteEntrada mensaje, Servidor servidor);
}
