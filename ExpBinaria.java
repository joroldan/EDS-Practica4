import java.util.ArrayList;
import java.util.List;

public abstract class ExpBinaria extends Expresion
{
	protected Expresion exp1, exp2;

	public ExpBinaria(Expresion e1, Expresion e2)
	{
		super();
		this.exp1 = e1;
		this.exp2 = e2;
		variables.addAll(e1.variables);
		List<String> l = e2.variables;
		for (String s : l)
		{
			if (!variables.contains(s)) variables.add(s);
		}
	}

	public abstract int valorar(Valoracion val);

	public void sustituir(String v, int n)
	{
		exp1.sustituir(v,n);
		exp2.sustituir(v,n);
		variables.remove(v);
	}

	public abstract Expresion copiar();

	public void renombrar(String v1, String v2)
	{
		if (variables.contains(v1))
		{
			exp1.renombrar(v1,v2);
			exp2.renombrar(v1,v2);
			variables.remove(v1);
			variables.add(v2);
		}
	}
}