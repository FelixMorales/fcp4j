package com.ucab.fcpserver4j.logica.mensajes.clientes.entrada;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.excepciones.ArchivoEnOtroServidorException;
import com.ucab.fcpserver4j.comun.excepciones.ArchivoNoEncontradoException;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.Conexion;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.clientes.gestionarupdate.ComandoRecibirUpdate;
import com.ucab.fcpserver4j.logica.comandos.servidores.gestionarupdate.ComandoObtenerArchivoRemoto;
import com.ucab.fcpserver4j.logica.mensajes.clientes.salida.EnviarError;
import com.ucab.fcpserver4j.logica.mensajes.clientes.salida.RespuestaCommit;
import com.ucab.fcpserver4j.logica.mensajes.clientes.salida.RespuestaUpdate;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntradaCliente;

import java.io.IOException;

public class Update implements IMensajeEntradaCliente
{

    @Override
    public void ejecutar( PaqueteEntrada mensaje, Conexion cliente ) throws IOException
    {
        try
        {
            Archivo archivo = new Archivo();

            archivo.setNombre( mensaje.obtenerString( PropiedadesMensajes.NOMBREARCHIVO ) );

            ComandoRecibirUpdate comando = new ComandoRecibirUpdate( archivo );

            Archivo encontrado;
            encontrado = comando.ejecutar();

            cliente.enviarCaracteres( new RespuestaUpdate(encontrado) );

        }
        catch ( ArchivoEnOtroServidorException remotoExc )
        {
            ServerManager.obtenerSingleton().setClienteActivo( cliente );
            ComandoObtenerArchivoRemoto comandoRemoto = new ComandoObtenerArchivoRemoto( remotoExc.getArchivo() );
            comandoRemoto.ejecutar();
        }
        catch ( ArchivoNoEncontradoException exc )
        {
            System.out.println( "El archivo no existe." );
            cliente.enviarCaracteres( new EnviarError( LeerPropiedad.ERROR_ARCHIVO_NOEXISTE ) );
        }
        catch ( IOException  e)
        {
            e.printStackTrace();
            cliente.enviarCaracteres( new EnviarError( LeerPropiedad.ERROR_GENERAL ) );
        }
    }
}
