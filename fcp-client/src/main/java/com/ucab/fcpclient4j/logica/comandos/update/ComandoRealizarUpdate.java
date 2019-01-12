package com.ucab.fcpclient4j.logica.comandos.update;

import com.ucab.fcpclient4j.comun.entidades.Archivo;
import com.ucab.fcpclient4j.comun.utilidades.ServerManager;
import com.ucab.fcpclient4j.logica.comandos.IComandoParametro;
import com.ucab.fcpclient4j.logica.comandos.gestionararchivos.ComandoFileToByte;
import com.ucab.fcpclient4j.logica.mensajes.core.MensajeManager;
import com.ucab.fcpclient4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpclient4j.logica.mensajes.salida.Commit;
import com.ucab.fcpclient4j.logica.mensajes.salida.Update;

import java.io.IOException;

public class ComandoRealizarUpdate implements IComandoParametro
{
    @Override
    public void ejecutarArgumento( String[] parametros ) throws IOException
    {

        String nombreArchivo = parametros[1].toLowerCase();

        Archivo archivo = new Archivo();
        archivo.setNombre( nombreArchivo );


        ServerManager.obtenerGlobal().getServidorPrincipal().enviarCaracteres( new Update( archivo ) );
        String mensajeUTF = ServerManager.obtenerGlobal().getServidorPrincipal().recibirCaracteres();
        MensajeManager.obtenerMensajeManager().ProcesarMensaje( new PaqueteEntrada( mensajeUTF ),
                                                                ServerManager.obtenerGlobal().getServidorPrincipal());
        ServerManager.obtenerGlobal().getServidorPrincipal().getConexion().close();
    }
}
