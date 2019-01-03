package com.ucab.fcpclient4j.logica.comandos.comunicacionInicial;


import com.ucab.fcpclient4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpclient4j.comun.utilidades.Conexion;
import com.ucab.fcpclient4j.comun.utilidades.ServerManager;
import com.ucab.fcpclient4j.logica.comandos.Comando;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;

/**
 * Name:                  ComandoSeleccionarServidor
 * Description:           Crea la lista de servidores activos al momento de iniciar el programa,
 *                        verificando cuales servidores estan activos agregando unicamente los activos.
 *
 * @since 30/12/18
 *
 */

public class ComandoSeleccionarServidor extends Comando<Boolean>
{


    @Override
    public Boolean ejecutar()
    {
        String[] listaServidores = LeerPropiedad.SERVIDORES.split( ";" );
        boolean retorno = false;
        for(int i = 0; i < listaServidores.length; i++)
        {
            String[] direccion = listaServidores[i].split( ":" );
            try
            {
                Conexion conexion = new Conexion( direccion[ 0 ], Integer.parseInt( direccion[ 1 ] ) );
                ServerManager.obtenerGlobal().setServidorPrincipal( conexion );
                retorno = true;
                break;

            }
            catch ( IOException | NoSuchAlgorithmException e )
            {
                System.out.println( "Servidor inactivo | "+direccion[0]+":"+direccion[1]+":"+direccion[2] );
                //System.out.println( String.format( LeerPropiedad.NO_ACTIVO, servidor, e.toString() ) );
            }
        }

        if(!retorno)
        {
            System.out.println( "No se pudo establecer la conexión" );
        }

        return retorno;
    }
}
