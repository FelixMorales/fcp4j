package com.ucab.fcpserver4j.comun.utilidades;

import com.ucab.fcpserver4j.comun.entidades.Servidor;

import java.util.ArrayList;
import java.util.List;

public class Global
{
    private static Global singleton;
    private static List<Servidor> servidoresActivos = new ArrayList<>();
    private static Servidor servidorLocal = new Servidor();
    private static boolean seleccionEnProceso = false;

    public List<Servidor> getServidoresActivos()
    {
        return servidoresActivos;
    }

    public void setServidoresActivos( List<Servidor> servidoresActivos )
    {
        this.servidoresActivos = servidoresActivos;
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

    private Global()
    { }

    public static synchronized Global obtenerGlobal()
    {
        if(singleton == null)
            singleton = new Global();

        return singleton;
    }
}
