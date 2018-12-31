package com.ucab.fcpserver4j.logica.comandos.servidores.salida.generacionidservidores;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.Global;
import com.ucab.fcpserver4j.logica.comandos.Comando;
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
        for( Servidor servidor : Global.obtenerGlobal().getServidoresActivos() )
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
                //TODO: Verificar quien es el nuevo servidor principal
                Global.obtenerGlobal().getServidoresActivos().remove( servidor );
                System.out.println( String.format( LeerPropiedad.SERVIDOR_DESCONECTADO, servidor.getIp(),
                                                   servidor.getPuerto(), e.toString() ) );
            }
        }

        return true;
    }
}
