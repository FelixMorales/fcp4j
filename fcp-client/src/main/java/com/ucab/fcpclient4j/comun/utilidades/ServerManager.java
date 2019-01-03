package com.ucab.fcpclient4j.comun.utilidades;

import java.util.ArrayList;
import java.util.List;

public class ServerManager
{
    private static ServerManager singleton;
    private static Conexion servidorPrincipal;

    private ServerManager()
    { }


    public Conexion getServidorPrincipal()
    {
        return servidorPrincipal;
    }

    public void setServidorPrincipal( Conexion servidorPrincipal )
    {
        ServerManager.servidorPrincipal = servidorPrincipal;
    }

    public static synchronized ServerManager obtenerGlobal()
    {
        if(singleton == null)
            singleton = new ServerManager();

        return singleton;
    }
}
