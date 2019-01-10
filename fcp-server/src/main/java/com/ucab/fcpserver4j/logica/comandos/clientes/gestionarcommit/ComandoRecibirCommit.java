package com.ucab.fcpserver4j.logica.comandos.clientes.gestionarcommit;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.comandos.gestionararchivos.ComandoBytesToFile;
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

        ComandoBytesToFile comando = new ComandoBytesToFile( archivo.getContenido(), archivo.getNombre()  );

        comando.ejecutar();

        archivo.setLocalizacion( ServerManager.obtenerGlobal().getServidorLocal() );

        DatabaseManager.obtenerSingleton().AgregarArchivo( archivo );

        ServerManager.obtenerGlobal().getServidorLocal().setHistorico( DatabaseManager.obtenerSingleton().ObtenerHistorico() );

        System.out.println( "Archivo creado exitosamente" );
        System.out.println( "Historico actualizado:" +ServerManager.obtenerGlobal().getServidorLocal().getHistorico());

        return true;
    }
}
