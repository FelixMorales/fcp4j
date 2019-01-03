package com.ucab.fcpclient4j.logica.mensajes.core.interfaces;

import com.ucab.fcpclient4j.comun.utilidades.Conexion;
import com.ucab.fcpclient4j.logica.mensajes.core.PaqueteEntrada;

public interface IMensajeEntrada
{
    void ejecutar( PaqueteEntrada mensaje, Conexion servidor );
}
