package com.ucab.fcpserver4j.logica.comandos.servidores.salida;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.Global;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.servidores.salida.ObtenerIdServidor;

import java.io.IOException;

public class ComandoEnviarPeticionObtenerId extends Comando<Boolean>
{
    public ComandoEnviarPeticionObtenerId()
    {

    }

    @Override
    public Boolean ejecutar()
    {
        for( Servidor servidor : Global.obtenerGlobal().getServidoresActivos() )
        {
            try
            {
                if(! servidor.isLocal() )
                {
                    servidor.getConexion().enviarCaracteres( new ObtenerIdServidor( Global.obtenerGlobal().getServidorLocal().getNombre())  );
                    String mensaje = servidor.getConexion().recibirCaracteres();
                    MensajeManager.obtenerMensajeManager()
                            .ProcesarMensajeServidor( new PaqueteEntrada( mensaje ), servidor );
                }

            }
            catch( IOException e )
            {
                Global.obtenerGlobal().getServidoresActivos().remove( servidor );
                System.out.println( String.format( LeerPropiedad.SERVIDOR_DESCONECTADO, servidor.getIp(),
                                                   servidor.getPuerto(), e.toString() ) );
            }
        }

        ComandoGenerarIdServidor generarIdServidor = new ComandoGenerarIdServidor();
        generarIdServidor.ejecutar();

        ComandoEnviarIdGenerado enviarIdGenerado = new ComandoEnviarIdGenerado();
        enviarIdGenerado.ejecutar();

        return true;
    }
}