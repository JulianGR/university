package fp.aeropuertos;

public interface VueloCompanyia extends Vuelo {
	String getNombreCompania();

	Persona getCapitan();

	public void setCapitan(Persona capitan);

}
