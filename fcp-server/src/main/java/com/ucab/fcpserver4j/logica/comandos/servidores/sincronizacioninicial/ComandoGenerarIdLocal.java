package com.ucab.fcpserver4j.logica.comandos.servidores.sincronizacioninicial;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;

/**
 * Name:                  ComandoGenerarIdLocal
 * Description:           Al tener todos los id de los servidores remotos, genero mi id local.
 *                        Calculando el id del servidor remoto mayor + 1.
 *
 * @since 30/12/18
 *
 */
public class ComandoGenerarIdLocal extends Comando<Boolean>
{
    public ComandoGenerarIdLocal()
    {

    }


    @Override
    public Boolean ejecutar()
    {
        for ( Servidor servidor : ServerManager.obtenerGlobal().getServidoresActivos() )
        {
            if( !servidor.isLocal() )
            {
                if( servidor.getId() > ServerManager.obtenerGlobal().getServidorLocal().getId() )
                    ServerManager.obtenerGlobal().getServidorLocal().setId( servidor.getId() );
            }
        }

        ServerManager
                .obtenerGlobal().getServidorLocal().setId( ServerManager.obtenerGlobal().getServidorLocal().getId() + 1 );

        return true;
    }

}
