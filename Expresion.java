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
}