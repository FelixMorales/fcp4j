package com.ucab.fcpclient4j.logica.comandos.commit;

import com.ucab.fcpclient4j.comun.entidades.Archivo;
import com.ucab.fcpclient4j.comun.utilidades.ServerManager;
import com.ucab.fcpclient4j.logica.comandos.Comando;
import com.ucab.fcpclient4j.logica.comandos.IComandoParametro;
import com.ucab.fcpclient4j.logica.comandos.gestionararchivos.ComandoFileToByte;
import com.ucab.fcpclient4j.logica.mensajes.salida.Commit;

import java.io.IOException;
import java.util.Date;

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
        archivo.setAutor( "Felix" );

        ServerManager.obtenerGlobal().getServidorPrincipal().enviarCaracteres( new Commit( archivo ) );
        String mensajeUTF = ServerManager.obtenerGlobal().getServidorPrincipal().recibirCaracteres();
        ServerManager.obtenerGlobal().getServidorPrincipal().getConexion().close();

        System.out.println( "mensaje respuesta commit: "+mensajeUTF );
    }
}
