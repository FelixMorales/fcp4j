package com.ucab.fcpserver4j.logica.mensajes.servidores.entrada;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntrada;

public class AsignarIdServidor implements IMensajeEntrada
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Servidor servidor )
    {
        servidor.setId( mensaje.obtenerInt( PropiedadesMensajes.IDSERVIDOR ) );

        System.out.println( "Servidores conectados:" );
        for(Servidor servidorActivo : ServerManager.obtenerSingleton().getServidoresActivos())
        {
            System.out.println( String.format( LeerPropiedad.INFO_SERVIDOR, servidorActivo.getIp(),
                                               servidorActivo.getPuerto(), servidorActivo.getNombre(),
                                               servidorActivo.getId(), servidorActivo.isPrincipal()) );
        }

    }

}
