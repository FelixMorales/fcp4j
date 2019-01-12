package com.ucab.fcpclient4j.comun.propiedades;

import java.io.File;
import java.util.Properties;

/**
 * System:                 Fcp
 * Name:                   LeerPropiedad
 *
 * @author Felix Morales
 * @version 1.0
 * @since 30/12/18
 */
public class LeerPropiedad extends BasePropiedad
{
    public static final String SERVIDORES = get( "CONFIG.servidores" );
    public static final String USUARIO = get( "CONFIG.username" );
    public static final String TRUSTSTOREPATH = get( "CONFIG.trustStore.path" );
    public static final String TRUSTSTOREPASSWORD = get( "CONFIG.trustStore.password" );
    public static final String SEPARADOR_ARCHIVO = File.separator;

    /**
     * Name:                  get
     * Description:           Get property by name
     *
     * @author Felix Morales
     * @version 1.0
     * @since 30/12/18
     *
     * @param name property name
     * @return property value
     */
    private static String get( String name )
    {
        Properties properties = obtenerPropiedades( CONFIG );

        return properties.getProperty( name );
    }
}
