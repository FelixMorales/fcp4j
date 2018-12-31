package com.ucab.fcpserver4j.logica.mensajes.core;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.Conexion;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntradaCliente;
import com.ucab.fcpserver4j.logica.mensajes.servidores.entrada.AsignarIdServidor;
import com.ucab.fcpserver4j.logica.mensajes.servidores.entrada.GenerarIdLocal;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntrada;
import com.ucab.fcpserver4j.logica.mensajes.servidores.entrada.ObtenerIdServidor;

import java.util.HashMap;

public class MensajeManager
{
    private static MensajeManager singleton;

    private static final HashMap<Integer, IMensajeEntrada> mensajesEntradaServidores = new HashMap<>(  );
    private static final HashMap<Integer, IMensajeEntradaCliente> mensajesEntradaClientes = new HashMap<>(  );

    private MensajeManager()
    {
        //Servidores
        mensajesEntradaServidores.put( CodigosEntrada.OBTENER_ID, new ObtenerIdServidor() );
        mensajesEntradaServidores.put( CodigosEntrada.GENERAR_ID_LOCAL, new GenerarIdLocal() );
        mensajesEntradaServidores.put( CodigosEntrada.ASIGNAR_ID_SERVIDOR, new AsignarIdServidor() );


        //Clientes
    }



    // Ejecuta mensajes que le envia otro servidor.
    public void ProcesarMensajeServidor( PaqueteEntrada mensaje, Servidor servidor)
    {
        IMensajeEntrada mensajeEntrada = verificarMensajeEntrada(
                mensaje.obtenerInt( PropiedadesMensajes.TIPOMENSAJE ) );

        if(mensajeEntrada != null)
            mensajeEntrada.ejecutar( mensaje, servidor );
        else
            mensajeNoEncontrado( mensaje );
    }

    public void ProcesarMensajeCliente(PaqueteEntrada mensaje, Conexion cliente )
    {
        IMensajeEntradaCliente mensajeEntrada = verificarMensajeEntradaCliente(
                mensaje.obtenerInt( PropiedadesMensajes.TIPOMENSAJE ) );

        if(mensajeEntrada != null)
            mensajeEntrada.ejecutar( mensaje, cliente );
        else
            mensajeNoEncontrado( mensaje );
    }

    public IMensajeEntrada verificarMensajeEntrada(int codigoEntrada)
    {
        return mensajesEntradaServidores.get( codigoEntrada );
    }

    public IMensajeEntradaCliente verificarMensajeEntradaCliente (int codigoEntrada)
    {
        return mensajesEntradaClientes.get( codigoEntrada );
    }


    public static MensajeManager obtenerMensajeManager()
    {
        if(singleton == null)
            singleton = new MensajeManager();

        return singleton;
    }

    private void mensajeNoEncontrado(PaqueteEntrada mensaje)
    {
        System.out.println( String.format( "mensaje no encontrado %s" ,
                                           mensaje.obtenerInt( PropiedadesMensajes.TIPOMENSAJE )) );
    }
}
