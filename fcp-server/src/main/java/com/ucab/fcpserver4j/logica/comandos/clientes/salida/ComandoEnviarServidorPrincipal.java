package com.ucab.fcpserver4j.logica.comandos.clientes.salida;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.utilidades.Conexion;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.mensajes.clientes.salida.EnviarServidorPrincipal;

import java.io.IOException;

public class ComandoEnviarServidorPrincipal extends Comando<Boolean>
{
    private Conexion cliente;

    private  Servidor servidorPrincipal;

    public ComandoEnviarServidorPrincipal(Conexion cliente)
    {
        this.cliente = cliente;
    }

    @Override
    public Boolean ejecutar()
    {
        for(int i = 0; i < ServerManager.obtenerGlobal().getServidoresActivos().size(); i++ )
        {
            Servidor servidor = ServerManager.obtenerGlobal().getServidoresActivos().get( i );

            if(servidor.isPrincipal())
            {
                this.servidorPrincipal = servidor;
                break;
            }
        }

        if(this.servidorPrincipal != null)
        {
            try
            {
                this.cliente.enviarCaracteres( new EnviarServidorPrincipal( this.servidorPrincipal ) );
            }
            catch ( IOException e )
            {
                System.out.println( "Error al enviar responder al cliente | "+e.toString() );
            }
        }

        return false;
    }
}
