/************************************************************************
  Expresion.java

  Guarda una lista de variables y nos da metodos para usarla

  Jorge Roldan Lopez
************************************************************************/

import java.util.ArrayList;
import java.util.List;

public abstract class Expresion
{
	protected List<String> variables;

	public Expresion()
	{
		this.variables = new ArrayList<String>();
	}

	public abstract int valorar(Valoracion val);

	public abstract void sustituir(String v, int n);

	public abstract void renombrar(String v1, String v2);

	public int numVariables()
	{
		return variables.size();
	}

	public boolean calculable(Valoracion val)
	{
		for (String s: variables)
		{
			if(!val.estaValorada(s)) return false;
		}

		return true;
	}
}