package com.ucab.fcpclient4j.comun.entidades;

public class Servidor
{
    private long id;
    private String ip;
    private boolean yo;

    private boolean isYo()
    {
        return yo;
    }

    private void setYo( boolean yo )
    {
        this.yo = yo;
    }

    private long getId()
    {
        return id;
    }

    private void setId( long id )
    {
        this.id = id;
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
