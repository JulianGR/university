package fp.universidades;

public interface Espacio{
	String getNombre();
	Integer getPlanta();
	TipoEspacio getTipo();
	Integer getCapacidad();
	void setNombre(String nombre);
	void setTipo(TipoEspacio tipo);
	void setCapacidad(Integer capacidad);
}
