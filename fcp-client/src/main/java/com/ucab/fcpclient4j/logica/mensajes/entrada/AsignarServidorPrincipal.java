package com.ucab.fcpclient4j.logica.mensajes.entrada;

import com.ucab.fcpclient4j.comun.utilidades.Conexion;
import com.ucab.fcpclient4j.comun.utilidades.ServerManager;
import com.ucab.fcpclient4j.logica.mensajes.core.PaqueteEntrada;
import com.ucab.fcpclient4j.logica.mensajes.core.constantes.PropiedadesMensajes;
import com.ucab.fcpclient4j.logica.mensajes.core.interfaces.IMensajeEntrada;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AsignarServidorPrincipal implements IMensajeEntrada
{
    @Override
    public void ejecutar( PaqueteEntrada mensaje, Conexion servidor )
    {
        try
        {
            String ipPrincipal;
            int puertoPrincipal;

            ipPrincipal = mensaje.obtenerString( PropiedadesMensajes.IPSERVIDOR_PRINCIPAL );
            puertoPrincipal = mensaje.obtenerInt( PropiedadesMensajes.PUERTOSERVIDOR_PRINCIPAL );

            // Establezco la conexión con el servidor principal
            ServerManager.obtenerGlobal().setServidorPrincipal( new Conexion( ipPrincipal,puertoPrincipal ) );

            System.out.println( "Conexion establecida con "+ipPrincipal+":"+puertoPrincipal );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        catch ( NoSuchAlgorithmException e )
        {
            e.printStackTrace();
        }
    }
}
