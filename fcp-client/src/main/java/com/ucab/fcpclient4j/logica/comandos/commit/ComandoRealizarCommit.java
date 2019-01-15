package com.ucab.fcpclient4j.logica.comandos.commit;

import com.ucab.fcpclient4j.comun.entidades.Archivo;
import com.ucab.fcpclient4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpclient4j.comun.utilidades.ServerManager;
import com.ucab.fcpclient4j.logica.comandos.Comando;
import com.ucab.fcpclient4j.logica.comandos.IComandoParametro;
import com.ucab.fcpclient4j.logica.comandos.gestionararchivos.ComandoFileToByte;
import com.ucab.fcpclient4j.logica.mensajes.core.MensajeManager;
import com.ucab.fcpclient4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpclient4j.logica.mensajes.salida.Commit;

import java.io.IOException;

public class ComandoRealizarCommit implements IComandoParametro
{
    @Override
    public void ejecutarArgumento( String[] parametros ) throws IOException
    {

        String nombreArchivo = parametros[1].toLowerCase();

        ComandoFileToByte comando = new ComandoFileToByte( nombreArchivo );

        Archivo archivo = new Archivo();

        archivo.setContenido( comando.ejecutar() );
        archivo.setNombre( nombreArchivo );
        archivo.setAutor( LeerPropiedad.USUARIO );

        ServerManager.obtenerGlobal().getServidorPrincipal().enviarCaracteres( new Commit( archivo ) );
        String mensajeUTF = ServerManager.obtenerGlobal().getServidorPrincipal().recibirCaracteres();
        MensajeManager.obtenerMensajeManager().ProcesarMensaje(
                new PaqueteEntrada( mensajeUTF ),
                ServerManager.obtenerGlobal().getServidorPrincipal() );
        ServerManager.obtenerGlobal().getServidorPrincipal().getConexion().close();

    }
}
