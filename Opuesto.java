/************************************************************************
  Opuesto.java

  Expresion que toma el valor negativo de exp

  Jorge Roldan Lopez
************************************************************************/

import java.util.ArrayList;

public class Opuesto extends Expresion
{
	private Expresion exp;

	public Opuesto(Expresion e)
	{
		super();
		this.exp = e;
		variables.addAll(e.variables);
	}

	public int valorar(Valoracion val)
	{
		return - exp.valorar(val);
	}

	public void sustituir(String v, int n)
	{
		exp.sustituir(v,n);
		variables.remove(v);
	}

	public Expresion copiar()
	{
		return new Opuesto(exp);
	}

	public void renombrar(String v1, String v2)
	{
		if (variables.contains(v1))
		{
			exp.renombrar(v1,v2);
			variables.remove(v1);
			variables.add(v2);
		}
	}

	public String toString()
	{
		return "(-" + exp.toString()+")";
	}
}