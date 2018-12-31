package com.ucab.fcpserver4j.logica.entrada;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Base64;

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

    public  String GetMensaje()
    {
        return mensaje.toString();
    }
}
