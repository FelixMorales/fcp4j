package com.ucab.fcpserver4j.comun.utilidades;

import com.ucab.fcpserver4j.logica.salida.IMensajeSalida;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class Conexion
{
    private SSLSocket conexion;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public Conexion ( Socket conexion ) throws IOException
    {

        this.conexion = (SSLSocket) conexion;

        System.out.println( "estoy en conexion" );

        entrada = new DataInputStream ( this.conexion.getInputStream() );
        salida = new DataOutputStream ( this.conexion.getOutputStream() );

        System.out.println( "estoy en conexion 2" );
    }

    public Conexion ( String ip, int puerto ) throws IOException, NoSuchAlgorithmException
    {
        SSLSocketFactory fabrica = SSLContext.getDefault().getSocketFactory();

        conexion = (SSLSocket) fabrica.createSocket( ip, puerto );

        entrada = new DataInputStream (conexion.getInputStream());
        salida = new DataOutputStream (conexion.getOutputStream());
    }

    public void desconectar() throws IOException
    {
        conexion.close();
    }

    public String recibirCaracteres() throws IOException
    {
        System.out.println( "Recibiendo mensaje UTF" );
        return entrada.readUTF();
    }

    public void enviarCaracteres ( IMensajeSalida entrada ) throws IOException
    {
        System.out.println("Enviando mensaje:"+entrada.getMensaje() );
        salida.writeUTF( entrada.getMensaje() );
    }

    public Socket getConexion()
    {
        return conexion;
    }

    public void setConexion( Socket conexion )
    {
        this.conexion = (SSLSocket) conexion;
    }
}
