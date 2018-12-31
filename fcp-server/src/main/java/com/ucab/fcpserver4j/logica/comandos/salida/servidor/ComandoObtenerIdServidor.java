package com.ucab.fcpserver4j.logica.comandos.salida.servidor;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.Global;
import com.ucab.fcpserver4j.logica.comandos.Comando;

public class ComandoObtenerIdServidor extends Comando<Boolean>
{
    @Override
    public Boolean ejecutar()
    {
        String local = LeerPropiedad.LOCAL;

        Servidor localHost = new Servidor();
        localHost.setIp( local.split( ":" )[0] );
        localHost.setPuerto( Integer.parseInt( local.split( ":" )[1] ) );
        localHost.setNombre( local.split( ":" )[2] );
        localHost.setLocal( true );

        ComandoCrearListaServidores crearListaServidores = new ComandoCrearListaServidores( local );
        crearListaServidores.ejecutar();

        Global.obtenerGlobal().setServidorLocal( localHost );

        if( verificarPrincipal() )
        {
            localHost.setPrincipal( true );
            localHost.setId( 1 );
            System.out.println( "soy el principal" );
        }
        else
        {
            ComandoEnviarPeticionObtenerId enviarPeticionObtenerId = new ComandoEnviarPeticionObtenerId();
            enviarPeticionObtenerId.ejecutar();
        }

        Global.obtenerGlobal().getServidoresActivos().add( localHost );
        return true;
    }

    private boolean verificarPrincipal()
    {
        return ( Global.obtenerGlobal().getServidoresActivos().size() < 1 );
    }
}