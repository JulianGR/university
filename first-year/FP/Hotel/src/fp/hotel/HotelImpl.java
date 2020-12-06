package fp.hotel;

public class HotelImpl implements Hotel {

	// ATRIBUTOS
	private String nombre;
	private String direccion;
	private String ciudad;
	private String telefono;
	private String cadenaHotelera;
	private String descripcion;
	private CategoriaHotelera categoriaDelHotel;
	private TipoAlojamiento tipoDeAlojamiento;
	private CategoriaPrecio categoriaDePrecio;
	private Float puntuacion;
	private Integer numeroComentarios;
	private Boolean admiteMascotas;
	private Boolean adaptado;

	// CONSTRUCTOR C1
	public HotelImpl(String nombre, String direccion, String ciudad, String telefono, String cadenaHotelera,
			String descripcion, CategoriaHotelera categoriaDelHotel, Float puntuacion,
			TipoAlojamiento tipoDeAlojamiento, CategoriaPrecio categoriaDePrecio, Integer numeroComentarios,
			Boolean admiteMascotas, Boolean adaptado) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.cadenaHotelera = cadenaHotelera;
		this.descripcion = descripcion;
		this.categoriaDelHotel = categoriaDelHotel;
		this.tipoDeAlojamiento = tipoDeAlojamiento;
		this.categoriaDePrecio = categoriaDePrecio;
		this.puntuacion = puntuacion;
		this.numeroComentarios = numeroComentarios;
		this.admiteMascotas = admiteMascotas;
		this.adaptado = adaptado;
	}

	// CONSTRUCTOR C2
	public HotelImpl(String nombre, String direccion, String ciudad, String telefono, String cadenaHotelera,
			CategoriaHotelera categoriaDelHotel, CategoriaPrecio categoriaDePrecio, Integer numeroComentarios,
			Boolean admiteMascotas, Boolean adaptado) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.cadenaHotelera = cadenaHotelera;
		this.categoriaDelHotel = categoriaDelHotel;
		this.categoriaDePrecio = CategoriaPrecio.MEDIA;
		this.numeroComentarios = 0;
		this.admiteMascotas = Boolean.FALSE;
		this.adaptado = Boolean.FALSE;
	}

	// CONSTRUCTOR C3
	public HotelImpl(String nombre, String cadenaHotelera, CategoriaHotelera categoriaDelHotel,
			TipoAlojamiento tipoDeAlojamiento, CategoriaPrecio categoriaDePrecio, Integer numeroComentarios,
			Boolean admiteMascotas, Boolean adaptado) {
		this.nombre = nombre;
		this.direccion = null;
		this.ciudad = null;
		this.telefono = null;
		this.cadenaHotelera = cadenaHotelera;
		this.descripcion = null;
		this.categoriaDelHotel = categoriaDelHotel;
		this.tipoDeAlojamiento = null;
		this.categoriaDePrecio = CategoriaPrecio.MEDIA;
		this.puntuacion = null;
		this.numeroComentarios = 0;
		this.admiteMascotas = Boolean.FALSE;
		this.adaptado = Boolean.FALSE;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCadenaHotelera() {
		return cadenaHotelera;
	}

	public void setCadenaHotelera(String cadenaHotelera) {
		this.cadenaHotelera = cadenaHotelera;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CategoriaHotelera getCategoriaDelHotel() {
		return categoriaDelHotel;
	}

	public void setCategoriaDelHotel(CategoriaHotelera categoriaDelHotel) {
		this.categoriaDelHotel = categoriaDelHotel;
	}

	public TipoAlojamiento getTipoDeAlojamiento() {
		return tipoDeAlojamiento;
	}

	public void setTipoDeAlojamiento(TipoAlojamiento tipoDeAlojamiento) {
		this.tipoDeAlojamiento = tipoDeAlojamiento;
	}

	public CategoriaPrecio getCategoriaDePrecio() {
		return categoriaDePrecio;
	}

	public void setCategoriaDePrecio(CategoriaPrecio categoriaDePrecio) {
		this.categoriaDePrecio = categoriaDePrecio;
	}

	public Float getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Float puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Integer getNumeroComentarios() {
		return numeroComentarios;
	}

	public void setNumeroComentarios(Integer numeroComentarios) {
		this.numeroComentarios = numeroComentarios;
	}

	public Boolean getAdmiteMascotas() {
		return admiteMascotas;
	}

	public void setAdmiteMascotas(Boolean admiteMascotas) {
		this.admiteMascotas = admiteMascotas;
	}

	public Boolean getAdaptado() {
		return adaptado;
	}

	public void setAdaptado(Boolean adaptado) {
		this.adaptado = adaptado;
	}

	// Para la representación con asteriscos
	private String resCategoria() {
		String res = null;
		switch (this.getCategoriaDelHotel()) {
		case UNA:
			res = "*";
			break;
		case DOS:
			res = "**";
			break;
		case TRES:
			res = "***";
			break;
		case CUATRO:
			res = "****";
			break;
		case CINCO:
			res = "*****";
			break;
		case OTROS:
			res = "N/A";
			break;
		}
		return res;
	}

	public String toString() {
		return this.getNombre() + " (" + this.resCategoria() + ")";

	}
}
