package fp.hotel;

public interface Hotel {
	String getNombre();

	void setNombre(String nombre);

	String getDireccion();

	void setDireccion(String direccion);

	String getCiudad();

	void setCiudad(String ciudad);

	String getTelefono();

	void setTelefono(String telefono);

	String getCadenaHotelera();

	void setCadenaHotelera(String cadenaHotelera);

	String getDescripcion();

	void setDescripcion(String descripcion);

	CategoriaHotelera getCategoriaDelHotel();

	void setCategoriaDelHotel(CategoriaHotelera categoriaDelHotel);

	TipoAlojamiento getTipoDeAlojamiento();

	void setTipoDeAlojamiento(TipoAlojamiento tipoDelAlojamiento);

	CategoriaPrecio getCategoriaDePrecio();

	void setCategoriaDePrecio(CategoriaPrecio categoriaDePrecio);

	Float getPuntuacion();

	void setPuntuacion(Float puntuacion);

	Integer getNumeroComentarios();

	void setNumeroComentarios(Integer numeroComentarios);

	Boolean getAdmiteMascotas();

	void setAdmiteMascotas(Boolean admiteMascotas);

	Boolean getAdaptado();

	void setAdaptado(Boolean adaptado);
}
