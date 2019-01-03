package com.ucab.fcpserver4j.logica.mensajes.clientes.entrada;

import com.ucab.fcpserver4j.comun.utilidades.Conexion;
import com.ucab.fcpserver4j.logica.mensajes.clientes.salida.RespuestaPrueba;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntradaCliente;

import java.io.IOException;

public class Prueba implements IMensajeEntradaCliente
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Conexion cliente )
    {
        String archivo = mensaje.obtenerString( PropiedadesMensajes.NOMBREARCHIVO );
        System.out.println( "mensaje recibido de"+cliente.getConexion().getRemoteSocketAddress() );
        System.out.println( "nombre del archivo:"+archivo );
        try
        {
            cliente.enviarCaracteres( new RespuestaPrueba() );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }
}
