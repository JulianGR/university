package fp.musica;

import java.time.Duration;

public class CancionDescargaImpl extends CancionImpl implements CancionDescarga {

	private Double tamanyo;
	private FormatoCancion formato;

	public Double getTamanyo() {
		return tamanyo;
	}

	public void setTamanyo(Double tamanyo) {
		this.tamanyo = tamanyo;
	}

	public FormatoCancion getFormato() {
		return formato;
	}

	public CancionDescargaImpl(Artista artista, Duration duracion, String id, String nombre, Integer numeroPista,
			Integer popularidad, Double tamanyo, FormatoCancion formato) {
		super(nombre, artista, duracion, nombre, popularidad, popularidad);
		this.tamanyo = tamanyo;
		this.formato = formato;
	}

	public String toString() {
		return super.toString() + " - tamaño del fichero: " + this.getTamanyo();

	}
}