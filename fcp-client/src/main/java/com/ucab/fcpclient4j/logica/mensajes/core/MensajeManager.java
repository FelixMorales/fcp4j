package com.ucab.fcpclient4j.logica.mensajes.core;


import com.ucab.fcpclient4j.comun.utilidades.Conexion;
import com.ucab.fcpclient4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpclient4j.logica.mensajes.core.interfaces.IMensajeEntrada;

import java.util.HashMap;

/**
 * Name:                  MensajeManager
 * Description:           Se encarga de identificar y procesar los mensajes entrantes de otros servidores
 *                        y clientes.
 *
 * @since 30/12/18
 *
 */
public class MensajeManager
{
    private static MensajeManager singleton;

    private static final HashMap<Integer, IMensajeEntrada> mensajesEntrada = new HashMap<>(  );

    private MensajeManager()
    {
        //mensajesEntrada.put( CodigosEntrada.OBTENER_ID, new ObtenerIdServidor() );
    }


    public void ProcesarMensaje(PaqueteEntrada mensaje, Conexion servidor )
    {
        IMensajeEntrada mensajeEntrada = verificarMensajeEntrada(
                mensaje.obtenerInt( PropiedadesMensajes.TIPOMENSAJE ) );

        if(mensajeEntrada != null)
            mensajeEntrada.ejecutar( mensaje, servidor );
        else
            mensajeNoEncontrado( mensaje );
    }

    public IMensajeEntrada verificarMensajeEntrada(int codigoEntrada)
    {
        return mensajesEntrada.get( codigoEntrada );
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
