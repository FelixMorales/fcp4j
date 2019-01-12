package com.ucab.fcpserver4j.comun.utilidades;

import com.ucab.fcpserver4j.comun.entidades.Servidor;

import java.util.ArrayList;
import java.util.List;

public class ServerManager
{
    private static ServerManager singleton;
    private static List<Servidor> servidoresActivos = new ArrayList<>();
    private static Servidor servidorLocal = new Servidor();
    private static boolean seleccionEnProceso = false;
    private Conexion cliente;

    public List<Servidor> getServidoresActivos()
    {
        return servidoresActivos;
    }

    public Servidor getServidorLocal()
    {
        return servidorLocal;
    }

    public void setServidorLocal( Servidor servidorLocal )
    {
        this.servidorLocal = servidorLocal;
    }

    public boolean isSeleccionEnProceso()
    {
        return seleccionEnProceso;
    }

    public void setSeleccionEnProceso( boolean seleccionEnProceso )
    {
        this.seleccionEnProceso = seleccionEnProceso;
    }

    public void setClienteActivo(Conexion cliente)
    {
        this.cliente = cliente;
    }

    public Conexion getClienteActivo()
    {
        return cliente;
    }

    private ServerManager()
    { }

    public static synchronized ServerManager obtenerSingleton()
    {
        if(singleton == null)
            singleton = new ServerManager();

        return singleton;
    }
}
