package com.ucab.fcpserver4j.comun.entidades;

import com.ucab.fcpserver4j.comun.utilidades.Conexion;

public class Servidor
{
    private long id;
    private String nombre;
    private String ip;
    private int puerto;
    private boolean principal;
    private boolean local;
    private Conexion conexion;

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getIp()
    {
        return ip;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre( String nombre )
    {
        this.nombre = nombre;
    }

    public void setIp( String ip )
    {
        this.ip = ip;
    }

    public int getPuerto()
    {
        return puerto;
    }

    public void setPuerto( int puerto )
    {
        this.puerto = puerto;
    }

    public boolean isPrincipal()
    {
        return principal;
    }

    public void setPrincipal( boolean principal )
    {
        this.principal = principal;
    }

    public boolean isLocal()
    {
        return local;
    }

    public void setLocal( boolean local )
    {
        this.local = local;
    }

    public Conexion getConexion()
    {
        return conexion;
    }

    public void setConexion( Conexion conexion )
    {
        this.conexion = conexion;
    }
}
