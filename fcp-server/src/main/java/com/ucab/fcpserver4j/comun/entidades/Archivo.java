package com.ucab.fcpserver4j.comun.entidades;

import java.util.Date;
import java.util.List;

public class Archivo
{
    private long id;
    private String nombre;
    private int version;
    private String autor;
    private String ip;
    private Date fechaCreacion;
    private List<String> localizacion;
    private byte[] contenido;

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre( String nombre )
    {
        this.nombre = nombre;
    }

    public int getVersion()
    {
        return version;
    }

    public void setVersion( int version )
    {
        this.version = version;
    }

    public String getAutor()
    {
        return autor;
    }

    public void setAutor( String autor )
    {
        this.autor = autor;
    }

    public Date getFechaCreacion()
    {
        return fechaCreacion;
    }

    public void setFechaCreacion( Date fechaCreacion )
    {
        this.fechaCreacion = fechaCreacion;
    }

    public List<String> getLocalizacion()
    {
        return localizacion;
    }

    public void setLocalizacion( List<String> localizacion )
    {
        this.localizacion = localizacion;
    }

    public byte[] getContenido()
    {
        return contenido;
    }

    public void setContenido( byte[] contenido )
    {
        this.contenido = contenido;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp( String ip )
    {
        this.ip = ip;
    }

}
