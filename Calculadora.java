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
}