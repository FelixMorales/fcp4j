package com.ucab.fcpserver4j.logica.comandos.servidores.sincronizacioninicial;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeSalida;
import com.ucab.fcpserver4j.logica.mensajes.servidores.salida.ObtenerIdServidor;

import java.io.IOException;

/**
 * Name:                  ComandoEnviarPeticionObtenerId
 * Description:           Envio el mensaje de salida para obtener el id local de los servidores remotos activos.
 *
 * @since 30/12/18
 *
 */
public class ComandoEnviarPeticionObtenerId extends Comando<Boolean>
{
    public ComandoEnviarPeticionObtenerId()
    {

    }

    @Override
    public Boolean ejecutar()
    {
        for( Servidor servidor : ServerManager.obtenerSingleton().getServidoresActivos() )
        {
            try
            {
                if(! servidor.isLocal() )
                {
                    // Obtengo el nombre del servidor local (yo)
                    String nombreServidorLocal = ServerManager.obtenerSingleton().getServidorLocal().getNombre();

                    // Instancio el mensaje de salida para obtener el id de los otros servidores activos.
                    IMensajeSalida obtenerIdServidor = new ObtenerIdServidor( nombreServidorLocal );

                    // Envio el mennsaje de salida generado al servidor activo.
                    servidor.getConexion().enviarCaracteres( obtenerIdServidor );

                    // Espero a que el servidor activo me responda. (RespuestaObtenerIdServidor)
                    String mensajeUTF = servidor.getConexion().recibirCaracteres();

                    // Transformo el mensaje UTF a PaqueteEntrada para poder procesarlo
                    PaqueteEntrada mensajeEntrada = new PaqueteEntrada( mensajeUTF );

                    // Proceso el mensaje que me responde el servidor activo.
                    MensajeManager.obtenerMensajeManager()
                            .ProcesarMensajeServidor( mensajeEntrada, servidor );
                }

            }
            catch( IOException e )
            {
                // Si un servidor se desconecta en el proceso, se elimina de la lista de servidores activos.
                ServerManager.obtenerSingleton().getServidoresActivos().remove( servidor );
                System.out.println( String.format( LeerPropiedad.SERVIDOR_DESCONECTADO, servidor.getIp(),
                                                   servidor.getPuerto()) );
            }
        }

        // Una vez tengo el id de todos los servidores, genero mi id local.
        ComandoGenerarIdLocal generarIdServidor = new ComandoGenerarIdLocal();
        generarIdServidor.ejecutar();

        // Sincronizo el archivo segun el historico de todos los servidores.
        ComandoSincronizarHistoricos comandoSincronizar = new ComandoSincronizarHistoricos();
        comandoSincronizar.ejecutar();

        // Una vez genero mi id local, la envio a todos los servidores para que la asigen.
        ComandoEnviarIdLocal enviarIdGenerado = new ComandoEnviarIdLocal();
        enviarIdGenerado.ejecutar();

        return true;
    }
}