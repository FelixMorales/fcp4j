package com.ucab.fcpserver4j.logica.mensajes.clientes.salida;

import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;

public class RespuestaHandShake extends MensajeSalida
{
    public RespuestaHandShake()
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.ENVIAR_SERVIDOR_PRINCIPAL );
        agregarElemento( PropiedadesMensajes.IPSERVIDOR_PRINCIPAL, ServerManager.obtenerSingleton().getServidorLocal().getIp() );
        agregarElemento( PropiedadesMensajes.PUERTOSERVIDOR_PRINCIPAL, ServerManager.obtenerSingleton().getServidorLocal().getPuerto() );
    }
}
