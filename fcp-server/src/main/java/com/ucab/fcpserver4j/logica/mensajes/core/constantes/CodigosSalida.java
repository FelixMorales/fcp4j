package com.ucab.fcpserver4j.logica.mensajes.core.constantes;

/**
 * Name:                  CodigosSalida
 * Description:           Codigos para poder identificar los mensajes salientes al recibirlos.
 *
 * @since 30/12/18
 *
 */
public final class CodigosSalida
{
    public static final int ENVIAR_SERVIDOR_PRINCIPAL = 0;
    public static final int RESPONDER_HANDSHAKE_EXITOSO = 1;
    public static final int RESPONDER_COMMIT = 2;
    public static final int RESPUESTA_UPDATE = 3;

    // Servidores
    public static final int OBTENER_ID = 100;
    public static final int OBTENER_ID_OCUPADO = 101;
    public static final int ASIGNAR_ID_SERVIDORES = 102;
    public static final int ASIGNAR_ID_SERVIDOR = 103;
    public static final int PETICION_PERSISTENCIA = 104;
    public static final int RESPUESTA_PETICION_PERSISTENCIA = 105;
    public static final int ENVIAR_PERSISTENCIA = 106;
    public static final int REPLICAR_ARCHIVO = 107;
    public static final int ENVIAR_PETICION_ARCHIVO = 108;
    public static final int RESPUESTA_PETICION_ARCHIVO = 109;

    // Errores
    public static final int ENVIAR_ERROR = 200;
}
