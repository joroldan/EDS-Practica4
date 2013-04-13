import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class Valoracion
{
	private Map<String, Integer> var;

	public Valoracion()
	{
		this.var = new HashMap<String, Integer>();
	}

	public void add(String v, int n)
	{
		this.var.put(v,n);
	}

	public void eliminar(String v)
	{
		this.var.remove(v);
	}

	public int valorVariable(String v)
	{
		return this.var.get(v);
	}

	public boolean estaValorada(String v)
	{
		return this.var.containsKey(v);
	}

	public boolean coherentes(Valoracion val)
	{
		Set<String> set = val.var.keySet();
		for (String s: set)
		{
			if (this.estaValorada(s)&&(val.valorVariable(s)!=this.valorVariable(s))) return false;
		}
		return true;
	}

	public void unir(Valoracion val)
	{
		if (coherentes(val))
		{
			Set<String> set = val.var.keySet();
			for (String s: set)
			{
				if (!this.estaValorada(s))
					this.add(s,val.valorVariable(s));
			}
		}
	}
}