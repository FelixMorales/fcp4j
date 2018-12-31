package com.ucab.fcpserver4j.logica.comandos.salida.servidor;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.Global;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.salida.servidor.AsignarIdServidor;
import com.ucab.fcpserver4j.servicio.MonitorearServidor;

import java.io.IOException;

public class ComandoEnviarIdGenerado extends Comando<Boolean>
{
    public ComandoEnviarIdGenerado()
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
