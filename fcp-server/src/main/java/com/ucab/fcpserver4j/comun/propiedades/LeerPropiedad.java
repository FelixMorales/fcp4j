package com.ucab.fcpserver4j.comun.propiedades;

import java.util.Properties;

/**
 * System:                 Fcp
 * Name:                   LeerPropiedad
 *
 * @since 30/12/18
 */
public class LeerPropiedad extends BasePropiedad
{
    public static final String SERVIDORES = get( "CONFIG.servidores" );
    public static final String LOCAL = get( "CONFIG.local" );
    public static final String TOLERANCIA = get( "CONFIG.k-tolerancia" );
    public static final String KEYSTOREPATH = get( "CONFIG.keyStore.path" );
    public static final String KEYSTOREPASSWORD = get( "CONFIG.keyStore.password" );
    public static final String TRUSTSTOREPATH = get( "CONFIG.trustStore.path" );
    public static final String TRUSTSTOREPASSWORD = get( "CONFIG.trustStore.password" );
    public static final String FORMATO_VERSION_ARCHIVO = "&&v=";

    /*Mensajes generales*/
    public static final String SERVIDOR_DESCONECTADO = get( "GENERAL.Mensaje.Desconectado" );
    public static final String NO_ACTIVO = get( "GENERAL.Mensaje.NoActivo" );
    public static final String INFO_SERVIDOR = get("GENERAL.Mensaje.Servidor.Info");
    public static final String INFO_ARCHIVO_REPLICA = get("GENERAL.Mensaje.Archivo.Replica");
    public static final String INFO_ARCHIVO_LOCAL = get("GENERAL.Mensaje.Archivo.Local");

    // Errores
    public static final String ERROR_GENERAL = get("GENERAL.Mensaje.General");
    public static final String ERROR_ARCHIVO_NOEXISTE = get("GENERAL.Mensaje.Archivo.NoEncontrado");

    /**
     *
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
