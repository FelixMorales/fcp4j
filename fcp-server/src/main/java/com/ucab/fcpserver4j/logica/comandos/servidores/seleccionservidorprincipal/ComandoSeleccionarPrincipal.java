package com.ucab.fcpserver4j.logica.comandos.servidores.seleccionservidorprincipal;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;

/**
 * Name:                  ComandoSeleccionarPrincipal
 * Description:           Realiza el proceso de selección de principal.
 *
 * @since 30/12/18
 *
 */

public class ComandoSeleccionarPrincipal extends Comando<Boolean>
{
    private Servidor servidorDesconectado;

    public ComandoSeleccionarPrincipal(Servidor servidorDesconectado)
    {
        this.servidorDesconectado = servidorDesconectado;
    }

    @Override
    public Boolean ejecutar()
    {
        ServerManager.obtenerSingleton().getServidoresActivos().remove( servidorDesconectado );
        if(this.servidorDesconectado.isPrincipal())
        {
            long idMenor = Long.MAX_VALUE;
            for(Servidor servidor : ServerManager.obtenerSingleton().getServidoresActivos() )
            {
                if(servidor.getId() < idMenor)
                {
                    idMenor = servidor.getId();
                }
            }

            for(Servidor servidor : ServerManager.obtenerSingleton().getServidoresActivos())
            {
                if(idMenor == servidor.getId())
                {
                    servidor.setPrincipal( true );
                }
            }

            System.out.println("El servidor principal se ha desconectado" );
            System.out.println( "Informacion de los servidores activos actualizada:" );
            for(Servidor servidorActivo : ServerManager.obtenerSingleton().getServidoresActivos())
            {
                System.out.println( String.format( LeerPropiedad.INFO_SERVIDOR, servidorActivo.getIp(),
                                                   servidorActivo.getPuerto(), servidorActivo.getNombre(),
                                                   servidorActivo.getId(), servidorActivo.isPrincipal()) );
            }
        }

        return true;
    }
}
