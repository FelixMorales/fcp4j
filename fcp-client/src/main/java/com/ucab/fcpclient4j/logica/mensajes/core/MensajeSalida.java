package com.ucab.fcpclient4j.logica.mensajes.core;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ucab.fcpclient4j.logica.mensajes.core.interfaces.IMensajeSalida;

import java.util.Base64;

/**
 * Name:                  MensajeSalida
 * Description:           Estructura base de todos los mensajes de salidas (JSON)
 *
 * @since 30/12/18
 *
 */
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
