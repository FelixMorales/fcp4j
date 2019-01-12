package com.ucab.fcpserver4j.logica.comandos.clientes.gestionarcommit;

import com.ucab.fcpserver4j.comun.entidades.Servidor;
import com.ucab.fcpserver4j.comun.propiedades.LeerPropiedad;
import com.ucab.fcpserver4j.comun.utilidades.ServerManager;
import com.ucab.fcpserver4j.logica.comandos.Comando;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComandoObtenerServidoresReplicas extends Comando<List<String>>
{

    public ComandoObtenerServidoresReplicas() {

    }
    @Override
    public List<String> ejecutar()
    {
        List<String> retorno = new ArrayList<>(  );

        int k = Integer.parseInt(LeerPropiedad.TOLERANCIA) + 1;

        List<Servidor> listaOrdenada = new ArrayList<>(  );

        for(Servidor servidor : ServerManager.obtenerSingleton().getServidoresActivos())
        {
            listaOrdenada.add( servidor );
        }

        Collections.sort( listaOrdenada );

        for(int i = 0; i < listaOrdenada.size(); i++)
        {
            if(i < k)
            {
                retorno.add( listaOrdenada.get(i).getNombre() );
            }
        }

        return retorno;
    }
}
