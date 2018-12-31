package com.ucab.fcpserver4j.logica.comandos.servidores.salida.generacionidservidores;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.Global;
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
        for ( Servidor servidor : Global.obtenerGlobal().getServidoresActivos() )
        {
            if( !servidor.isLocal() )
            {
                if( servidor.getId() > Global.obtenerGlobal().getServidorLocal().getId() )
                    Global.obtenerGlobal().getServidorLocal().setId( servidor.getId() );
            }
        }

        Global.obtenerGlobal().getServidorLocal().setId( Global.obtenerGlobal().getServidorLocal().getId() + 1 );

        return true;
    }

}
