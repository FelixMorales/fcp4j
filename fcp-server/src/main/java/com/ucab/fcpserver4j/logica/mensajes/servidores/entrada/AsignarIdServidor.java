package com.ucab.fcpserver4j.logica.mensajes.servidores.entrada;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.Global;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpserver4j.logica.mensajes.core.interfaces.IMensajeEntrada;

public class AsignarIdServidor implements IMensajeEntrada
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Servidor servidor )
    {
        servidor.setId( mensaje.obtenerInt( PropiedadesMensajes.IDSERVIDOR ) );

        for(Servidor serv : Global.obtenerGlobal().getServidoresActivos() )
            System.out.println( "id: " +serv.getId() + " | nombre: " + serv.getNombre() );
    }

}
