package com.ucab.fcpserver4j.logica.comandos.servidores.sincronizacioninicial;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.comandos.gestionararchivos.ComandoFileToByte;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeManager;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeSalida;
import com.ucab.fcpserver4j.logica.mensajes.servidores.salida.EnviarPersistencia;
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
        for( Servidor servidor : ServerManager.obtenerSingleton().getServidoresActivos() )
        {
            if(servidor.getHistorico() > mayorHistorico)
            {
                mayorHistorico = servidor.getHistorico();
                retorno = servidor;
            }
        }

        if(retorno != null)
        {
            if(retorno.getHistorico() < ServerManager.obtenerSingleton().getServidorLocal().getHistorico())
            {
                retorno = ServerManager.obtenerSingleton().getServidorLocal();
            }
        }

        return retorno;
    }

    private void enviarArchivoPersistenciaLocal()
    {
        boolean error = false;
        System.out.println( "Soy el local y envio mi persistencia a los demas (" + ServerManager.obtenerSingleton().getServidorLocal().getHistorico() + ")");

        byte[] contenidoPersistencia = null;

        ComandoFileToByte comando = new ComandoFileToByte(  );

        try
        {
            contenidoPersistencia = comando.ejecutar();
        }
        catch ( IOException e )
        {
            error = true;
            e.printStackTrace();
        }

        if(!error && contenidoPersistencia != null)
        {
            for(Servidor servidor : ServerManager.obtenerSingleton().getServidoresActivos())
            {
                if(!servidor.isLocal())
                {
                    if(servidor.getHistorico() < ServerManager.obtenerSingleton().getServidorLocal().getHistorico())
                    {
                        try
                        {
                            servidor.getConexion().enviarCaracteres( new EnviarPersistencia( contenidoPersistencia ) );
                        }
                        catch ( IOException e )
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    private void enviarPeticionObtenerArchivoPersistenciaActualizado(Servidor servidorActualizado)
    {
        if(servidorActualizado.getHistorico() > ServerManager.obtenerSingleton().getServidorLocal().getHistorico())
        {
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
