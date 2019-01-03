package com.ucab.fcpserver4j.logica.mensajes.clientes.entrada;

import com.ucab.fcpserver4j.comun.utilidades.Conexion;
import com.ucab.fcpserver4j.logica.mensajes.clientes.salida.RespuestaHandShake;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntradaCliente;

import java.io.IOException;

public class HandShake implements IMensajeEntradaCliente
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Conexion cliente )
    {
        System.out.println( "mensaje recibido de"+cliente.getConexion().getRemoteSocketAddress() );
        try
        {
            cliente.enviarCaracteres( new RespuestaHandShake() );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }
}
