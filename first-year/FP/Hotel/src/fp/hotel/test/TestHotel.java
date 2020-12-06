package fp.hotel.test;

import fp.hotel.CategoriaHotelera;
import fp.hotel.CategoriaPrecio;
import fp.hotel.Hotel;
import fp.hotel.HotelImpl;
import fp.hotel.TipoAlojamiento;

public class TestHotel {

	public static void main(String[] args) {

		constructor1();
		constructor2();
		constructor3();

	}

	private static void constructor1() {
		constructor1("Hotel Alfonso XIII", "calle Alfonso XIII", "Sevilla", "666666666", "A Luxury Collection Hotel",
				"Un hotel muy bonito", CategoriaHotelera.CINCO, TipoAlojamiento.HOTEL, CategoriaPrecio.LUJO, (float) 80,
				7, Boolean.TRUE, Boolean.TRUE);
	}

	private static void constructor2() {
		constructor2("Hotel Alfonso XIII", "calle Alfonso XIII", "Sevilla", "666666666", "A Luxury Collection Hotel",
				CategoriaHotelera.CUATRO, null, null, null, null);
	}

	private static void constructor3() {
		constructor3("Hotel Alfonso XIII", "A Luxury Collection Hotel", CategoriaHotelera.OTROS, null, null, null, null,
				null);
	}

	private static void constructor1(String nombre, String direccion, String ciudad, String telefono,
			String cadenaHotelera, String descripcion, CategoriaHotelera categoriaDelHotel,
			TipoAlojamiento tipoDeAlojamiento, CategoriaPrecio categoriaDePrecio, Float puntuacion,
			Integer numeroComentarios, Boolean admiteMascotas, Boolean adaptado) {

		Hotel x = new HotelImpl(nombre, direccion, ciudad, telefono, cadenaHotelera, categoriaDelHotel,
				categoriaDePrecio, numeroComentarios, admiteMascotas, adaptado);
		mostrarHotel(x);
	}

	private static void constructor2(String nombre, String direccion, String ciudad, String telefono,
			String cadenaHotelera, CategoriaHotelera categoriaDelHotel, CategoriaPrecio categoriaDePrecio,
			Integer numeroComentarios, Boolean admiteMascotas, Boolean adaptado) {

		Hotel x = new HotelImpl(nombre, direccion, ciudad, telefono, cadenaHotelera, categoriaDelHotel,
				categoriaDePrecio, numeroComentarios, admiteMascotas, adaptado);
		mostrarHotel(x);
	}

	private static void constructor3(String nombre, String cadenaHotelera, CategoriaHotelera categoriaDelHotel,
			TipoAlojamiento tipoDeAlojamiento, CategoriaPrecio categoriaDePrecio, Integer numeroComentarios,
			Boolean admiteMascotas, Boolean adaptado) {
		Hotel x = new HotelImpl(nombre, cadenaHotelera, categoriaDelHotel, tipoDeAlojamiento, categoriaDePrecio,
				numeroComentarios, admiteMascotas, adaptado);
		mostrarHotel(x);
	}

	private static void mostrarHotel(Hotel x) {
		System.out.println("Hotel: " + x);
		System.out.println("Nombre:" + x.getNombre());
		System.out.println("Dirección:" + x.getDireccion());
		System.out.println("Ciudad:" + x.getCiudad());
		System.out.println("Teléfono:" + x.getTelefono());
		System.out.println("Cadena Hotelera:" + x.getCadenaHotelera());
		System.out.println("Descripción:" + x.getDescripcion());
		System.out.println("Categoría Hotelera:" + x.getCategoriaDelHotel());
		System.out.println("Tipo de Alojamiento:" + x.getTipoDeAlojamiento());
		System.out.println("Categoría de Precio:" + x.getCategoriaDePrecio());
		System.out.println("Puntuación:" + x.getPuntuacion());
		System.out.println("Número de Comentarios:" + x.getNumeroComentarios());
		System.out.println("¿Admite mascotas?:" + x.getAdmiteMascotas());
		System.out.println("¿Está Adaptado?:" + x.getAdaptado());

	}
}
