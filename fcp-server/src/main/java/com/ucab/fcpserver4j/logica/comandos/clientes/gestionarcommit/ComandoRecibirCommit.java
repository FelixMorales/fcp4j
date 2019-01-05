package com.ucab.fcpserver4j.logica.comandos.clientes.gestionarcommit;

import com.ucab.fcpserver4j.comun.entidades.Archivo;
import com.ucab.fcpserver4j.logica.comandos.Comando;
import com.ucab.fcpserver4j.logica.comandos.clientes.gestionararchivos.ComandoBytesToFile;

public class ComandoRecibirCommit extends Comando<Boolean>
{
    Archivo archivo;
    public ComandoRecibirCommit ( Archivo archivo )
    {
        this.archivo = archivo;
    }

    @Override
    public Boolean ejecutar()
    {

        ComandoBytesToFile comando = new ComandoBytesToFile( archivo.getContenido(), archivo.getNombre()  );

        comando.ejecutar();

        System.out.println( "Archivo creado exitosamente" );


        return true;
    }
}
