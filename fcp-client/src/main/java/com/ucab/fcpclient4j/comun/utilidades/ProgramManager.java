package com.ucab.fcpclient4j.comun.utilidades;

import com.ucab.fcpclient4j.logica.comandos.IComandoParametro;
import com.ucab.fcpclient4j.logica.comandos.commit.ComandoRealizarCommit;
import com.ucab.fcpclient4j.logica.comandos.update.ComandoRealizarUpdate;

import java.io.IOException;
import java.util.HashMap;

public class ProgramManager
{
    private static ProgramManager singleton;
    private static final HashMap<String, IComandoParametro> comandosParametro = new HashMap<>(  );

    private ProgramManager ()
    {
        comandosParametro.put(Parametros.COMMIT , new ComandoRealizarCommit() );
        comandosParametro.put( Parametros.UPDATE, new ComandoRealizarUpdate() );
    }

    public void ProcesarParametro(String[] parametros ) throws IOException
    {
        try
        {
            IComandoParametro comando = verificarParametro(parametros[0].toLowerCase());

            if(comando != null)
                comando.ejecutarArgumento( parametros );
            else
                comandoNoEncontrado();
        }
        catch ( IndexOutOfBoundsException e )
        {
            System.out.println( "Error al procesar los parámetros." );
        }
    }

    public IComandoParametro verificarParametro(String comando)
    {
        return comandosParametro.get( comando );
    }

    private void comandoNoEncontrado()
    {
        System.out.println( "El comando ingresado no es válido." );
    }



    public static ProgramManager obtenerSingleton()
    {
        if(singleton == null)
            singleton = new ProgramManager();

        return singleton;
    }


}
