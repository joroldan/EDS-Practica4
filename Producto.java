/************************************************************************
  Producto.java

  Expresion que multiplica dos expresiones

  Jorge Roldan Lopez
************************************************************************/

public class Producto extends ExpBinaria
{
	public Producto(Expresion e1, Expresion e2)
	{
		super(e1,e2);
	}

	public int valorar(Valoracion val)
	{
		return exp1.valorar(val)*exp2.valorar(val);
	}

	public Expresion copiar()
	{
		return new Producto(exp1,exp2);
	}

	public String toString()
	{
		return "(" + exp1.toString() + "*" + exp2.toString() + ")";
	}
}