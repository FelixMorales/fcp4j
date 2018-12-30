package com.ucab.fcpserver4j.comun.propiedades;

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
    public static final String LOCAL = get( "CONFIG.local" );
    public static final String PUERTO = get( "CONFIG.puerto" );
    public static final String TOLERANCIA = get( "CONFIG.k-tolerancia" );
    public static final String KEYSTOREPATH = get( "CONFIG.keyStore.path" );
    public static final String KEYSTOREPASSWORD= get( "CONFIG.keyStore.password" );

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