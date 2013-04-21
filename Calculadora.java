public class Calculadora
{
	private Expresion exp;
	private Valoracion val;

	public Calculadora()
	{
		this.exp = null;
		this.val = new Valoracion();
	}

	public Calculadora(Expresion e, Valoracion v)
	{
		this.exp = e;
		this.val = v;
	}

	public int calcular()
	{
		return exp.valorar(val);
	}

	public void setExpresion(Expresion e)
	{
		this.exp = e;
	}

	public void setValoracion(Valoracion v)
	{
		this.val = v;
	}

	public void darValorVariable(String v)
	{
		exp.sustituir(v, val.valorVariable(v));
	}

	public void renombrarVariable(String v1, String v2)
	{
		exp.renombrar(v1, v2);
	}

	public int numVariables()
	{
		return exp.numVariables();
	}

	public void addVariable(String v, int n)
	{
		val.add(v,n);
	}

	public void eliminarVariable(String v)
	{
		val.eliminar(v);
	}

	public int valorVariable(String v)
	{
		return val.valorVariable(v);
	}

	public boolean estaValorada(String v)
	{
		return val.estaValorada(v);
	}

	public boolean valoracionCoherente(Valoracion val)
	{
		return val.coherentes(val);
	}

	public void unirValoracion(Valoracion val)
	{
		val.unir(val);
	}

	public Expresion getExpresion()
	{
		return exp;
	}

	public Valoracion getValoracion()
	{
		return val;
	}

	public boolean sePuedeCalcular()
	{
		return exp.calculable(val);
	}
}