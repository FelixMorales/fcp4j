package com.ucab.fcpserver4j.logica.comandos.clientes.gestionarcommit;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.comandos.gestionararchivos.ComandoBytesToFile;
import com.ucab.fcpserver4j.persistencia.DatabaseManager;

import java.io.IOException;
import java.util.List;

public class ComandoRecibirCommit extends Comando<Boolean>
{
    Archivo archivo;
    public ComandoRecibirCommit ( Archivo archivo )
    {
        this.archivo = archivo;
    }

    @Override
    public Boolean ejecutar() throws IOException
    {


        ComandoActualizarCantidadArchivos comandoCantidadArchivos = new ComandoActualizarCantidadArchivos();
        comandoCantidadArchivos.ejecutar();

        ComandoObtenerServidoresReplicas comandoServidores = new ComandoObtenerServidoresReplicas();

        archivo.setLocalizacion( comandoServidores.ejecutar() );



        DatabaseManager.obtenerSingleton().AgregarArchivo( archivo, true );

        ServerManager.obtenerGlobal().getServidorLocal().setHistorico( DatabaseManager.obtenerSingleton().ObtenerHistorico() );

        ComandoReplicarArchivo comandoReplica = new ComandoReplicarArchivo( archivo );
        comandoReplica.ejecutar();

        System.out.println( "Archivo creado exitosamente" );
        System.out.println( "Historico actualizado:" +ServerManager.obtenerGlobal().getServidorLocal().getHistorico());

        return true;
    }
}
