package com.ucab.fcpserver4j.logica.mensajes.core;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.Conexion;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.clientes.enviarservidorprincipal.ComandoEnviarServidorPrincipal;
import com.ucab.fcpserver4j.logica.mensajes.clientes.entrada.Commit;
import com.ucab.fcpserver4j.logica.mensajes.clientes.entrada.HandShake;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntradaCliente;
import com.ucab.fcpserver4j.logica.mensajes.servidores.entrada.AsignarIdServidor;
import com.ucab.fcpserver4j.logica.mensajes.servidores.entrada.RespuestaObtenerIdServidor;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntrada;
import com.ucab.fcpserver4j.logica.mensajes.servidores.entrada.ObtenerIdServidor;

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

    private static final HashMap<Integer, IMensajeEntrada> mensajesEntradaServidores = new HashMap<>(  );
    private static final HashMap<Integer, IMensajeEntradaCliente> mensajesEntradaClientes = new HashMap<>(  );

    private MensajeManager()
    {
        //Servidores
        mensajesEntradaServidores.put( CodigosEntrada.OBTENER_ID, new ObtenerIdServidor() );
        mensajesEntradaServidores.put( CodigosEntrada.ASIGNAR_ID_SERIVDORES, new RespuestaObtenerIdServidor() );
        mensajesEntradaServidores.put( CodigosEntrada.ASIGNAR_ID_SERVIDOR, new AsignarIdServidor() );


        //Clientes
        mensajesEntradaClientes.put( CodigosEntrada.HANDSHAKE_CLIENTE, new HandShake() );
        mensajesEntradaClientes.put( CodigosEntrada.COMMIT, new Commit() );
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
        {
            if( ServerManager.obtenerGlobal().getServidorLocal().isPrincipal() )
                mensajeEntrada.ejecutar( mensaje, cliente );
            else
                {
                    ComandoEnviarServidorPrincipal comando = new ComandoEnviarServidorPrincipal(cliente);
                    comando.ejecutar();
                }
        }
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
