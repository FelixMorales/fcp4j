package com.ucab.fcpserver4j.logica.mensajes.core.constantes;

/**
 * Name:                  CodigosEntrada
 * Description:           Codigos para identificar los tipos de mensajes de entrada.
 *
 * @since 30/12/18
 *
 */
public final class CodigosEntrada
{

    //Clientes
    public static final int HANDSHAKE_CLIENTE = 1;
    public static final int COMMIT = 2;
    public static final int UPDATE = 3;


    // Servidores
    public static final int OBTENER_ID = 100;
    public static final int OBTENER_ID_OCUPADO = 101;
    public static final int ASIGNAR_ID_SERIVDORES = 102;
    public static final int ASIGNAR_ID_SERVIDOR = 103;
    public static final int PETICION_PERSISTENCIA = 104;
    public static final int RESPUESTA_PETICION_PERSISTENCIA = 105;
    public static final int ENVIAR_PERSISTENCIA = 106;
    public static final int REPLICAR_ARCHIVO = 107;
    public static final int ENVIAR_PETICION_ARCHIVO = 108;
    public static final int RESPUESTA_PETICION_ARCHIVO = 109;

}
