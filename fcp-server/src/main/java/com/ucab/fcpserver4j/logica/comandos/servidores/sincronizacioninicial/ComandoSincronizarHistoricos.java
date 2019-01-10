package com.ucab.fcpserver4j.logica.comandos.servidores.sincronizacioninicial;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeManager;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeSalida;
import com.ucab.fcpserver4j.logica.mensajes.servidores.salida.PeticionPersistencia;

import java.io.IOException;

public class ComandoSincronizarHistoricos extends Comando<Boolean>
{

    @Override
    public Boolean ejecutar()
    {

        Servidor servidorActualizado = obtenerServidorActualizado();

        if(servidorActualizado != null)
        {
            if(servidorActualizado.isLocal())
            {
                enviarArchivoPersistenciaLocal();
            }
            else
            {
                enviarPeticionObtenerArchivoPersistenciaActualizado(servidorActualizado);
            }
        }

        return false;
    }

    // Obtener el servidor con mayor contador de historicos (operaciones de escritura)
    private Servidor obtenerServidorActualizado()
    {
        int mayorHistorico = 0;
        Servidor retorno = null;
        for( Servidor servidor : ServerManager.obtenerGlobal().getServidoresActivos() )
        {
            if(servidor.getHistorico() > mayorHistorico)
            {
                mayorHistorico = servidor.getHistorico();
                retorno = servidor;
            }
        }

        if(retorno.getHistorico() < ServerManager.obtenerGlobal().getServidorLocal().getHistorico())
        {
            retorno = ServerManager.obtenerGlobal().getServidorLocal();
        }

        return retorno;
    }

    private void enviarArchivoPersistenciaLocal()
    {
        System.out.println( "Soy el local y envio mi persistencia a los demas ("+ServerManager.obtenerGlobal().getServidorLocal().getHistorico()+")");
        for(Servidor servidor : ServerManager.obtenerGlobal().getServidoresActivos())
        {
            if(!servidor.isLocal())
            {
                if(servidor.getHistorico() < ServerManager.obtenerGlobal().getServidorLocal().getHistorico())
                {
                    // Mensaje salida envio mi historico y archivoSqlite
                }
            }
        }
    }

    private void enviarPeticionObtenerArchivoPersistenciaActualizado(Servidor servidorActualizado)
    {
        if(servidorActualizado.getHistorico() > ServerManager.obtenerGlobal().getServidorLocal().getHistorico())
        {
            System.out.println( "Le pido al servidor " + servidorActualizado.getNombre()+" su historico: "+servidorActualizado.getHistorico() );
            IMensajeSalida peticionPersistencia = new PeticionPersistencia( );
            enviarMensaje( servidorActualizado, peticionPersistencia );
        }
    }

    private void enviarMensaje(Servidor servidor, IMensajeSalida mensaje)
    {
        try
        {
            servidor.getConexion().enviarCaracteres( mensaje );

            String mensajeUTF = servidor.getConexion().recibirCaracteres();

            PaqueteEntrada mensajeEntrada = new PaqueteEntrada( mensajeUTF);

            MensajeManager.obtenerMensajeManager()
                    .ProcesarMensajeServidor( mensajeEntrada, servidor );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }

    }


}
