package com.ucab.fcpserver4j.logica.comandos.servidores.salida.generacionidservidores;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.Conexion;
import com.ucab.fcpserver4j.comun.utilidades.Global;
import com.ucab.fcpserver4j.logica.comandos.Comando;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Name:                  ComandoCrearListaServidores
 * Description:           Crea la lista de servidores activos al momento de iniciar el programa,
 *                        verificando cuales servidores estan activos agregando unicamente los activos.
 *
 * @since 30/12/18
 *
 */

public class ComandoCrearListaServidores extends Comando<Boolean>
{
    String local;

    public ComandoCrearListaServidores(String local)
    {
        this.local = local;
    }

    @Override
    public Boolean ejecutar()
    {
        String[] listaServidores = LeerPropiedad.SERVIDORES.split( ";" );

        for ( String servidor: listaServidores )
        {
            if( !servidor.equals( local ) )
            {
                String[] direccion = servidor.split( ":" );

                try
                {
                    Conexion conexion = new Conexion( direccion[ 0 ], Integer.parseInt( direccion[ 1 ] ) );
                    Servidor host = new Servidor();
                    host.setIp( direccion[ 0 ] );
                    host.setPuerto( Integer.parseInt( direccion[ 1 ] ) );
                    host.setNombre( direccion[ 2 ] );
                    host.setConexion( conexion );
                    Global.obtenerGlobal().getServidoresActivos().add( host );
                }
                catch ( IOException | NoSuchAlgorithmException e )
                {
                    System.out.println( String.format( LeerPropiedad.NO_ACTIVO, servidor, e.toString() ) );
                }
            }
        }

        return true;
    }
}
