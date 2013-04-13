public class Principal
{
	public static void main(String[] args)
	{
		Suma s = new Suma(new Producto(new Opuesto(new ExpAtomica("x")),new ExpAtomica("y")),new Suma(new ExpAtomica(2),new ExpAtomica("x")));
		Valoracion val = new Valoracion();
		val.add("x",1);
		val.add("y",0);
		System.out.println("Cuando x=1 e y=0, entonces "+  s + " = " + s.valorar(val));
		CalculadoraGrafica cg = new CalculadoraGrafica();
	}
}