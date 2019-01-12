package com.ucab.fcpserver4j.logica.comandos.clientes.gestionarcommit;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.persistencia.DatabaseManager;

import java.io.IOException;

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

        ServerManager.obtenerSingleton().getServidorLocal().setHistorico( DatabaseManager.obtenerSingleton().ObtenerHistorico() );

        ComandoReplicarArchivo comandoReplica = new ComandoReplicarArchivo( archivo );
        comandoReplica.ejecutar();
        return true;
    }
}
