package com.ucab.fcpserver4j.logica.entrada.servidor;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.Global;
import com.ucab.fcpserver4j.comun.utilidades.PropiedadesMensajes;
import com.ucab.fcpserver4j.logica.entrada.PaqueteEntrada;

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
