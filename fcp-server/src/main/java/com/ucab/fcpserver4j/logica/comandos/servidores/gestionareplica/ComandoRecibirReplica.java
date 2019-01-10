package com.ucab.fcpserver4j.logica.comandos.servidores.gestionareplica;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.comandos.gestionararchivos.ComandoBytesToFile;
import com.ucab.fcpserver4j.persistencia.DatabaseManager;

import java.io.IOException;

public class ComandoRecibirReplica extends Comando<Boolean>
{
    Archivo archivo;
    boolean almacenar;

    public ComandoRecibirReplica( Archivo archivo, boolean almacenar )
    {
        this.archivo = archivo;
        this.almacenar = almacenar;
    }

    @Override
    public Boolean ejecutar()
    {

        try
        {
            if(almacenar)
            {
                ComandoBytesToFile comandoAlmacenar = new ComandoBytesToFile( archivo );
                comandoAlmacenar.ejecutar();
            }

            DatabaseManager.obtenerSingleton().AgregarArchivo( archivo, false );
            ServerManager.obtenerGlobal().getServidorLocal().setHistorico( DatabaseManager.obtenerSingleton().ObtenerHistorico() );

            System.out.println( "Archivo creado exitosamente" );
            System.out.println( "Historico actualizado:" +ServerManager.obtenerGlobal().getServidorLocal().getHistorico());
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }

        return true;
    }
}
