package com.ucab.fcpserver4j.servicio;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.Conexion;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeManager;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;

import java.io.IOException;

/**
 * Name:                  ConnectionManager
 * Description:           Procesa cada conexión nueva, diferencia si es un servidor o cliente.
 *
 * @since 30/12/18
 *
 */
public class ConnectionManager implements Runnable
{
    private Conexion conexion;

    public ConnectionManager( Conexion conexion )
    {
        this.conexion = conexion;
    }

    @Override
    public void run()
    {
        boolean esServidor = false;
        try
        {
            String mensaje = conexion.recibirCaracteres();

            PaqueteEntrada mensajeEntrada = new PaqueteEntrada( mensaje );

            esServidor = mensajeEntrada.obtenerBoolean( PropiedadesMensajes.SERVIDOR ) ;


            if(esServidor)
            {
                monitorearServidor( conexion, mensajeEntrada );
            }
            else
            {
                MensajeManager.obtenerMensajeManager().
                        ProcesarMensajeCliente( mensajeEntrada, conexion );
            }
        }
        catch( IOException e )
        {
            System.out.println( e.toString() );
        }
        catch ( Exception excGeneral )
        {
            excGeneral.printStackTrace();
        }
    }

    private void monitorearServidor(Conexion conexion, PaqueteEntrada entrada)
    {
        // Instancia el objeto servidor.
        Servidor servidor = crearServidor( conexion, entrada.obtenerString( PropiedadesMensajes.NOMBRESERVIDOR ));

        // Agrega el servidor a la lista de servidores activos.
        ServerManager.obtenerSingleton().getServidoresActivos().add( servidor );

        // Procesa el mensaje de entrada inicial.
        MensajeManager.obtenerMensajeManager().ProcesarMensajeServidor( entrada, servidor );

        // Mantiene la conexión abierta con el servidor.
        MonitorearServidor monitor = new MonitorearServidor( servidor );
        monitor.escucharServidor();
    }

    private Servidor crearServidor(Conexion conexion, String nombreServidor)
    {
        Servidor respuesta = new Servidor();
        String[] listaServidores = LeerPropiedad.SERVIDORES.split( ";" );

        for ( String servidor: listaServidores )
        {
            String[] direccion = servidor.split( ":" );

            if(nombreServidor.equals( direccion[2] ))
            {
                respuesta.setIp( direccion[0] );
                respuesta.setPuerto( Integer.parseInt( direccion[1] ) );
                respuesta.setNombre( direccion[2] );
                respuesta.setConexion( conexion );
            }
        }

        return respuesta;
    }
}
