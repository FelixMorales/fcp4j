package com.ucab.fcpserver4j.logica.mensajes.clientes.salida;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.logica.mensajes.core.MensajeSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.CodigosSalida;
import com.ucab.fcpserver4j.logica.mensajes.core.constantes.PropiedadesMensajes;

public class EnviarServidorPrincipal extends MensajeSalida
{
    public EnviarServidorPrincipal( Servidor servidorPrincipal)
    {
        super();

        agregarElemento( PropiedadesMensajes.TIPOMENSAJE, CodigosSalida.ENVIAR_SERVIDOR_PRINCIPAL );
        agregarElemento( PropiedadesMensajes.IPSERVIDOR_PRINCIPAL, servidorPrincipal.getIp() );
        agregarElemento( PropiedadesMensajes.PUERTOSERVIDOR_PRINCIPAL, servidorPrincipal.getPuerto() );

    }
}
