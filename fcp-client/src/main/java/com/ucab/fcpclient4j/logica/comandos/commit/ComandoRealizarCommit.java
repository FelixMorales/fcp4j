package com.ucab.fcpclient4j.logica.comandos.commit;

import com.ucab.fcpclient4j.comun.entidades.Archivo;
import com.ucab.fcpclient4j.comun.utilidades.ServerManager;
import com.ucab.fcpclient4j.logica.comandos.Comando;
import com.ucab.fcpclient4j.logica.comandos.gestionararchivos.ComandoFileToByte;
import com.ucab.fcpclient4j.logica.mensajes.salida.Commit;

import java.io.IOException;
import java.util.Date;

public class ComandoRealizarCommit extends Comando<Boolean>
{
    String nombreArchivo;
    public ComandoRealizarCommit(String nombreArchivo)
    {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public Boolean ejecutar() throws IOException
    {
        ComandoFileToByte comando = new ComandoFileToByte( nombreArchivo );

        Archivo archivo = new Archivo();

        archivo.setContenido( comando.ejecutar() );
        archivo.setNombre( nombreArchivo );
        archivo.setAutor( "Felix" );
        archivo.setFechaCreacion( new Date() );

        ServerManager.obtenerGlobal().getServidorPrincipal().enviarCaracteres( new Commit( archivo ) );
        String mensajeUTF = ServerManager.obtenerGlobal().getServidorPrincipal().recibirCaracteres();
        ServerManager.obtenerGlobal().getServidorPrincipal().getConexion().close();

        System.out.println( "mensaje respuesta commit: "+mensajeUTF );


        return null;
    }
}
