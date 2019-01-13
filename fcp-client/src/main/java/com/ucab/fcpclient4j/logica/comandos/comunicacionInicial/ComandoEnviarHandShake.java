package com.ucab.fcpclient4j.logica.comandos.comunicacionInicial;

import com.ucab.fcpclient4j.comun.utilidades.ServerManager;
import com.ucab.fcpclient4j.logica.comandos.Comando;
import com.ucab.fcpclient4j.logica.mensajes.core.MensajeManager;
import com.ucab.fcpclient4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpclient4j.logica.mensajes.salida.HandShake;

import java.io.IOException;

public class ComandoEnviarHandShake extends Comando<Boolean>
{

    @Override
    public Boolean ejecutar() throws IOException
    {
        ServerManager.obtenerGlobal().getServidorPrincipal().enviarCaracteres( new HandShake() );

        String mensajeUTF = ServerManager.obtenerGlobal().getServidorPrincipal().recibirCaracteres();

        ServerManager.obtenerGlobal().getServidorPrincipal().getConexion().close();

        PaqueteEntrada mensajeEntrada = new PaqueteEntrada( mensajeUTF );
        MensajeManager.obtenerMensajeManager().ProcesarMensaje( mensajeEntrada, null );
        return true;
    }
}
