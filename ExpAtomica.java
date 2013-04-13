public class ExpAtomica extends Expresion
{
	private int valor;

	public ExpAtomica(String s)
	{
		super();
		variables.add(s);
	}

	public ExpAtomica(int n)
	{
		super();
		this.valor = n;
	}

	public int valorar(Valoracion val)
	{
		if (variables.size()==1)
		{
			try
			{
				return val.valorVariable(variables.get(0));
			}
			catch (NullPointerException e)
			{
				throw new ArithmeticException("No se ha declarado la variable " + variables.get(0) + ".");
			}
		}
		else return valor;
	}

	public void sustituir(String v, int n)
	{
		if (variables.contains(v))
		{
			valor = n;
			variables.remove(v);
		}
	}

	public Expresion copiar()
	{
		if (variables.size()==1)
		{
			return new ExpAtomica(variables.get(0));
		}
		else return new ExpAtomica(valor);
	}

	public void renombrar(String v1, String v2)
	{
		if (variables.contains(v1))
		{
			variables.remove(v1);
			variables.add(v2);
		}
	}

	public String toString()
	{
		if (variables.size()==1)
		{
			return new String(variables.get(0));
		}
		else return "" + valor;
	}
}