package com.ucab.fcpclient4j.logica.mensajes.core;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Base64;
import java.util.Date;

/**
 * Name:                  PaqueteEntrada
 * Description:           Estructura base de todos los mensajes entrantes (JSON)
 *
 * @since 30/12/18
 *
 */
public class PaqueteEntrada
{
    private JsonObject mensaje;

    public PaqueteEntrada(String mensaje)
    {
        this.mensaje = new Gson().fromJson( mensaje, JsonObject.class );
    }

    public int obtenerInt(String propiedad)
    {
        return  mensaje.get( propiedad ).getAsInt();
    }

    public String obtenerString(String propiedad)
    {
        return  mensaje.get( propiedad ).getAsString();
    }

    public boolean obtenerBoolean(String propiedad)
    {
        return  mensaje.get( propiedad ).getAsBoolean();
    }

    public byte[] obtenerBytes(String propiedad)
    {
        return Base64.getDecoder().decode( mensaje.get( propiedad ).getAsString() );
    }

    public Date obtenerDate(String propiedad)
    {
        return new Date(mensaje.get(propiedad).getAsInt());
    }

    public  String GetMensaje()
    {
        return mensaje.toString();
    }
}
