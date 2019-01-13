package com.ucab.fcpserver4j.logica.mensajes.clientes.entrada;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.Conexion;
import com.ucab.fcpserver4j.logica.comandos.clientes.gestionarcommit.ComandoRecibirCommit;
import com.ucab.fcpserver4j.logica.mensajes.clientes.salida.EnviarError;
import com.ucab.fcpserver4j.logica.mensajes.clientes.salida.RespuestaCommit;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntradaCliente;

import java.io.IOException;

public class Commit implements IMensajeEntradaCliente
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Conexion cliente ) throws IOException
    {
        try
        {

            Archivo archivo = new Archivo();

            archivo.setAutor( mensaje.obtenerString( PropiedadesMensajes.AUTORARCHIVO ) );
            archivo.setNombre( mensaje.obtenerString( PropiedadesMensajes.NOMBREARCHIVO ) );
            archivo.setContenido( mensaje.obtenerBytes( PropiedadesMensajes.CONTENIDOARCHIVO ) );
            archivo.setIp(cliente.getConexion().getInetAddress().toString() );

            ComandoRecibirCommit comando = new ComandoRecibirCommit( archivo );
            comando.ejecutar();

            cliente.enviarCaracteres( new RespuestaCommit() );
        }
        catch ( IOException e )
        {
            cliente.enviarCaracteres( new EnviarError( LeerPropiedad.ERROR_GENERAL ) );
            e.printStackTrace();
        }
    }
}
