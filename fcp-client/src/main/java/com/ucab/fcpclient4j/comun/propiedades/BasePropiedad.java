package com.ucab.fcpclient4j.comun.propiedades;

import java.io.*;
import java.util.Properties;

/**
 * System:                 Fcp
 * Name:                   BasePropiedad
 *
 * @author Felix Morales
 * @version 1.0
 * @since 30/12/18
 */
public class BasePropiedad
{
    protected static final String CONFIG = "config.properties";
    private static final String ERROR = "Error cargando las propiedades de %s";

    /**
     * Name:                  obtenerPropiedades
     * Description:           Obtiene las propiedades de un archivo de configuracion
     *
     * @author Felix Morales
     * @version 1.0
     * @since 30/12/18
     *
     * @param nombre nombre del archivo
     * @return lista de propiedades
     */
    protected static Properties obtenerPropiedades( String nombre )
    {
        Properties propiedades;

        try
        {
            propiedades = new Properties();
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream( nombre );
            Reader reader = new InputStreamReader( stream, "UTF-8" );
            propiedades.load( reader );
            stream.close();
        }
        catch ( IOException e )
        {
            throw new IllegalArgumentException( String.format( ERROR, nombre) );
        }

        return propiedades;
    }
}
