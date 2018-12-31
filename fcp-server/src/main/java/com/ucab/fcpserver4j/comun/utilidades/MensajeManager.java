package com.ucab.fcpserver4j.comun.utilidades;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.logica.entrada.servidor.AsignarIdServidor;
import com.ucab.fcpserver4j.logica.entrada.servidor.GenerarIdLocal;
import com.ucab.fcpserver4j.logica.entrada.servidor.IMensajeEntrada;
import com.ucab.fcpserver4j.logica.entrada.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.entrada.servidor.ObtenerIdServidor;

import java.util.HashMap;

public class MensajeManager
{
    private static MensajeManager singleton;

    public static final HashMap<Integer, IMensajeEntrada> mensajesEntrada = new HashMap<>(  );

    private MensajeManager()
    {
        mensajesEntrada.put( CodigosEntrada.OBTENER_ID, new ObtenerIdServidor() );
        mensajesEntrada.put( CodigosEntrada.GENERAR_ID_LOCAL, new GenerarIdLocal() );
        mensajesEntrada.put( CodigosEntrada.ASIGNAR_ID_SERVIDOR, new AsignarIdServidor() );
    }



    // Ejecuta mensajes que le envia otro servidor.
    public void ejecutarMensaje( PaqueteEntrada mensaje, Servidor servidor)
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
