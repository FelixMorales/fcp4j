package com.ucab.fcpserver4j.logica.comandos.servidores.sincronizacioninicial;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.comandos.servidores.seleccionservidorprincipal.ComandoSeleccionarPrincipal;
import com.ucab.fcpserver4j.logica.mensajes.servidores.salida.AsignarIdServidor;
import com.ucab.fcpserver4j.servicio.MonitorearServidor;

import java.io.IOException;

/**
 * Name:                  ComandoEnviarIdLocal
 * Description:           Envio mi id local a los otros servidores remotos y mantengo
 *                        la conexión abierta con cada servidor.
 *
 * @since 30/12/18
 *
 */

public class ComandoEnviarIdLocal extends Comando<Boolean>
{

    public ComandoEnviarIdLocal()
    {

    }

    @Override
    public Boolean ejecutar()
    {
        for( Servidor servidor : ServerManager.obtenerGlobal().getServidoresActivos() )
        {
            try
            {
                if( !servidor.isLocal() )
                {
                    servidor.getConexion().enviarCaracteres( new AsignarIdServidor()  );

                    // Una vez envio mi id local al servidor remoto, mantengo el canal abierto.
                    Thread hilo = new Thread( new MonitorearServidor( servidor ) );
                    hilo.start();
                }
            }
            catch( IOException e )
            {
                ComandoSeleccionarPrincipal comando = new ComandoSeleccionarPrincipal( servidor );
                comando.ejecutar();
                System.out.println( "muere servidor (2)  "
                                    +servidor.getNombre()+" - "
                                    +servidor.getIp()+servidor.getPuerto() );
            }
        }

        return true;
    }
}
