package com.ucab.fcpserver4j.logica.comandos.servidores.salida;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.Global;
import com.ucab.fcpserver4j.logica.comandos.Comando;

public class ComandoGenerarIdServidor extends Comando<Boolean>
{
    public ComandoGenerarIdServidor()
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

        for ( Servidor servidor : Global.obtenerGlobal().getServidoresActivos() )
        {
            if(servidor.isLocal())
                servidor.setId( Global.obtenerGlobal().getServidorLocal().getId() );
        }

        return true;
    }

}
