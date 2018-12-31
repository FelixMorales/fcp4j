package com.ucab.fcpclient4j.comun.entidades;

import java.util.Date;

public class Archivo
{
    private long id;
    private String nombre;
    private int version;
    private String autor;
    private String ip;
    private Date fechaCreacion;
    private Servidor localizacion;
    private byte[] contenido;

    private long getId()
    {
        return id;
    }

    private void setId( long id )
    {
        this.id = id;
    }

    private String getNombre()
    {
        return nombre;
    }

    private void setNombre( String nombre )
    {
        this.nombre = nombre;
    }

    private int getVersion()
    {
        return version;
    }

    private void setVersion( int version )
    {
        this.version = version;
    }

    private String getAutor()
    {
        return autor;
    }

    private void setAutor( String autor )
    {
        this.autor = autor;
    }

    private Date getFechaCreacion()
    {
        return fechaCreacion;
    }

    private void setFechaCreacion( Date fechaCreacion )
    {
        this.fechaCreacion = fechaCreacion;
    }

    private Servidor getLocalizacion()
    {
        return localizacion;
    }

    private void setLocalizacion( Servidor localizacion )
    {
        this.localizacion = localizacion;
    }

    private byte[] getContenido()
    {
        return contenido;
    }

    private void setContenido( byte[] contenido )
    {
        this.contenido = contenido;
    }

    private String getIp()
    {
        return ip;
    }

    private void setIp( String ip )
    {
        this.ip = ip;
    }
}
