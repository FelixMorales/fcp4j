package com.ucab.fcpclient4j;

import com.ucab.fcpclient4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpclient4j.comun.utilidades.Conexion;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class App 
{
    public static void main( String[] args ) throws NoSuchAlgorithmException, IOException
    {
        System.setProperty("javax.net.ssl.trustStore", LeerPropiedad.TRUSTSTOREPATH );
        System.setProperty("javax.net.ssl.trustStorePassword ", LeerPropiedad.TRUSTSTOREPASSWORD );

        Conexion conexion = new Conexion( "localhost", 3000 );
        conexion.enviarCaracteres( "Hola" );
        System.out.println( "Le dije hola" );

    }
}
