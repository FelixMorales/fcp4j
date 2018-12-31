package com.ucab.fcpserver4j.logica.salida;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Base64;

public class MensajeSalida implements IMensajeSalida
{
    private JsonObject mensaje;

    public MensajeSalida()
    {
        mensaje = new JsonObject();
    }

    @Override
    public String getMensaje()
    {
        Gson gson = new Gson();
        return gson.toJson( mensaje );
    }

    public void agregarElemento(String nombre, String valor)
    {
        mensaje.addProperty( nombre, valor );
    }

    public void agregarElemento(String nombre, int valor)
    {
        mensaje.addProperty( nombre, valor );
    }

    public void agregarElemento(String nombre, boolean valor)
    {
        mensaje.addProperty( nombre, valor );
    }

    public void agregarElemento(String nombre, double valor)
    {
        mensaje.addProperty( nombre, valor );
    }

    public void agregarElemento(String nombre, byte[] valor)
    {
        mensaje.addProperty( nombre, Base64.getEncoder().encodeToString( valor ) );
    }
}
