package fp.musica;

public interface CancionDescarga extends Cancion {

	Double getTamanyo();

	public void setTamanyo(Double tamanyo);

	FormatoCancion getFormato();
}
