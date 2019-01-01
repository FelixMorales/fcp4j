package com.ucab.fcpserver4j.logica.comandos.servidores.seleccionservidorprincipal;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
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
        ServerManager.obtenerGlobal().getServidoresActivos().remove( servidorDesconectado );
        if(this.servidorDesconectado.isPrincipal())
        {
            long idMenor = Long.MAX_VALUE;
            for(Servidor servidor : ServerManager.obtenerGlobal().getServidoresActivos() )
            {
                if(servidor.getId() < idMenor)
                {
                    idMenor = servidor.getId();
                }
            }

            for(Servidor servidor : ServerManager.obtenerGlobal().getServidoresActivos())
            {
                if(idMenor == servidor.getId())
                {
                    servidor.setPrincipal( true );
                    System.out.println( "Nuevo servidor principal"+servidor.getNombre()+" - "+servidor.getId()
                                        +servidor.getIp()+servidor.getPuerto() );
                }
            }
        }

        return true;
    }
}
