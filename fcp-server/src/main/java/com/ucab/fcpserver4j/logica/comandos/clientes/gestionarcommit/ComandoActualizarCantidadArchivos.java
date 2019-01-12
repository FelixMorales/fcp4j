package com.ucab.fcpserver4j.logica.comandos.clientes.gestionarcommit;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.persistencia.DatabaseManager;

import java.util.HashMap;

public class ComandoActualizarCantidadArchivos extends Comando<Boolean>
{

    @Override
    public Boolean ejecutar()
    {
        HashMap<String, Integer> archivosPorServidor =
                DatabaseManager.obtenerSingleton().ObtenerCantidadArchivosPorServidor();

        for( Servidor servidor : ServerManager.obtenerSingleton().getServidoresActivos() )
        {
            try
            {
                int cantidadArchivos = archivosPorServidor.get( servidor.getNombre() );
                servidor.setCantidadArchivos( cantidadArchivos );
            }
            catch ( NullPointerException e)
            {
                servidor.setCantidadArchivos( 0 );
            }
        }

        return true;
    }
}
