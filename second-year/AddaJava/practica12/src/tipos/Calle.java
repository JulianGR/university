package tipos;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Calle extends DefaultWeightedEdge {

	private static final long serialVersionUID = 1L;

	public static Calle create(Estacion c1, Estacion c2) {
		return new Calle();
	}

	public static Calle create(Estacion c1, Estacion c2, String[] formato) {
		return new Calle(c1,c2,formato);
	}

	public static Calle create() {
		return new Calle();
	}
	
	private static int ultimo = 0;
	private int id;
	private double minutos = 0.0;
	private Estacion source;
	private Estacion target;

	public Calle(Estacion c1, Estacion c2, String[] formato) {
		super();
		this.source = c1;
		this.target = c2;
		this.minutos = Double.valueOf(formato[2]);
		this.id = ultimo;
		ultimo++;
	}

	public int getId() {
		return id;
	}

	public double getMinutos() {
		return minutos;
	}

	
	public Estacion getSource(){
		return source;
	}
	
	public Estacion getTarget(){
		return target;
	}
	
	public double getWeight(){
		return minutos;
	}
		
	public Calle() {
		super();
		this.id = ultimo;
		ultimo++;
	}

	@Override
	public String toString() {
		return this.minutos + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calle other = (Calle) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
