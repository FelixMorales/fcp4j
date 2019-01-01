package com.ucab.fcpserver4j.logica.comandos.servidores.generacionidservidores;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;

/**
 * Name:                  ComandoCompuestoGenerarIds
 * Description:           Crea la instancia del servidor local (localhost), crea la lista de
 *                        servidores remotos activos y si hay 1 o mas servidores activos, empieza el
 *                        proceso de obtención y generación de id locales y remotas.
 *
 * @since 30/12/18
 *
 */
public class ComandoCompuestoGenerarIds extends Comando<Boolean>
{
    @Override
    public Boolean ejecutar()
    {
        String local = LeerPropiedad.LOCAL;

        Servidor localHost = CrearServidorLocal(local);

        ComandoCrearListaServidores crearListaServidores = new ComandoCrearListaServidores( local );
        crearListaServidores.ejecutar();

        ServerManager.obtenerGlobal().setServidorLocal( localHost );

        // Verifica si soy el primer servidor activo, entonces soy el principal.
        // No hace falta enviar la peticion de obtener de id de otros servidores si soy el único.
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

        // Al final del proceso de generacion de id, me agrego a la lista de servidores activos.
        ServerManager.obtenerGlobal().getServidoresActivos().add( localHost );

        for(Servidor servidorActivo : ServerManager.obtenerGlobal().getServidoresActivos())
        {
            System.out.println( "Servidor: "+servidorActivo.getNombre()+" - Info:"
                                +servidorActivo.getIp()+servidorActivo.getPuerto() +
                                " - Id:"+servidorActivo.getId() + " - Principal:"+servidorActivo.isPrincipal() );
        }

        return true;
    }

    private boolean verificarPrincipal()
    {
        return ( ServerManager.obtenerGlobal().getServidoresActivos().size() < 1 );
    }

    private Servidor CrearServidorLocal(String direccionLocal)
    {
        Servidor localHost = new Servidor();

        localHost.setIp( direccionLocal.split( ":" )[0] );
        localHost.setPuerto( Integer.parseInt( direccionLocal.split( ":" )[1] ) );
        localHost.setNombre( direccionLocal.split( ":" )[2] );
        localHost.setLocal( true );

        return  localHost;
    }
}