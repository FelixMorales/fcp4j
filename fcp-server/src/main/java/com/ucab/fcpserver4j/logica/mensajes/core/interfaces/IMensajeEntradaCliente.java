package com.ucab.fcpserver4j.logica.mensajes.core.interfaces;

import com.ucab.fcpserver4j.comun.utilidades.Conexion;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;

public interface IMensajeEntradaCliente
{
    void ejecutar( PaqueteEntrada mensaje, Conexion cliente );
}
