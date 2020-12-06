package fp.ciclismo;

import java.time.Duration;

import fp.utiles.Checkers;

public class GanadorImpl implements Ganador {
	private Integer anyo;
	private String nacionalidad;
	private String nombre;
	private String equipo;
	private Integer kmRecorridos;
	private Duration tiempoEmpleado;
    private Integer numEtapasGanadas;
	private Integer numDiasMaillotAmarillo;

	public GanadorImpl(Integer anyo, String nacionalidad, String nombre, String equipo, Integer kmRecorridos,
			Duration tiempoEmpleado, Integer numEtapasGanadas, Integer numDiasMaillotAmarillo) {
		
		Checkers.check("Año incorrecto", anyo>1903);
		this.anyo = anyo;
		this.nacionalidad = nacionalidad;
		this.nombre = nombre;
		this.equipo = equipo;
		Checkers.check("Kms incorrecto", kmRecorridos>0);
		this.kmRecorridos = kmRecorridos;
		Checkers.check("Tiempo incorrecto", tiempoEmpleado.compareTo(Duration.ZERO)>0);
		this.tiempoEmpleado = tiempoEmpleado;
		Checkers.check("Etapas incorrectas", numEtapasGanadas >=0);
		this.numEtapasGanadas = numEtapasGanadas;
		Checkers.check("Número días maillot amarillo", numDiasMaillotAmarillo>0);
		this.numDiasMaillotAmarillo = numDiasMaillotAmarillo;
	}
	
	public GanadorImpl(String s){
		Checkers.checkNoNull(s);
		String [] trozos = s.split(",");
		Checkers.check("Formato de ganador no válido", trozos.length == 8);
		
		Integer a= new Integer(trozos[0].trim());
		Checkers.check("Año incorrecto", a>1903);
		this.anyo = a;
		this.nacionalidad = trozos[1].trim();
		this.nombre = trozos[2].trim();
		this.equipo = trozos[3].trim();
		Integer kms = new Integer (trozos[4].trim());
		Checkers.check("Kms incorrecto", kms>0);
		this.kmRecorridos = kms;
		Duration tiempo = Duration.parse(trozos[5].trim());
		Checkers.check("Tiempo incorrecto", tiempo.compareTo(Duration.ZERO)>0);
		this.tiempoEmpleado = tiempo;
		Integer etapas = new Integer (trozos[6].trim());
		Checkers.check("Etapas incorrectas", etapas >=0);
		this.numEtapasGanadas = etapas;
		Integer diasMaillot = new Integer (trozos[7].trim());
		Checkers.check("Número días maillot amarillo", diasMaillot>0);
		this.numDiasMaillotAmarillo = diasMaillot;
		
	}

	public Integer getAnyo() {
		return anyo;
	}

	
	public String getNacionalidad() {
		return nacionalidad;
	}

	
	public String getNombre() {
		return nombre;
	}

	public String getEquipo() {
		return equipo;
	}

	
	public Integer getKmRecorridos() {
		return kmRecorridos;
	}


	public Duration getTiempoEmpleado() {
		return tiempoEmpleado;
	}

	public Integer getNumDiasMaillot() {
		return numDiasMaillotAmarillo;
	}

	public Integer getNumEtapasGanadas() {
		return numEtapasGanadas;
	}

    public Double getVelocidadMedia() { 

    	Double horas = getTiempoEmpleado().getSeconds()/3600.0;
        return getKmRecorridos().doubleValue() / horas;

    }
	public boolean equals(Object o){
	boolean res = false;
	    if (o != null && o instanceof Ganador){
	    	Ganador gt = (Ganador) o;
	    	res = getNombre().equals(gt.getNombre()) &&
	    			getEquipo().equals(gt.getEquipo()) &&
	    			getAnyo().equals(gt.getAnyo()); 
	    	
	    }
	return res;
	}
	
	public int hashCode() {
		return getNombre().hashCode()+ getEquipo().hashCode()* 31 + getAnyo().hashCode()*31*31;
	}
	public String toString() {
		return getAnyo()+" - " + getNombre() + " en " + getEquipo();
	}

	
}
