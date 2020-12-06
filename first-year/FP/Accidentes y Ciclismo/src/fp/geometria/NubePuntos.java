package fp.geometria;

import java.util.Collection;
import java.util.List;

public interface NubePuntos {
	/**
	 * @return Una lista con los puntos de la nube
	 */
	List<Punto> getPuntos();
	/**
	 * @return El número de puntos de la nube
	 */
	Integer getNumPuntos();
	void incorporaPunto (Punto p);
	void incorporaPuntos (Collection<Punto> colPuntos);
	void incorporaPuntos (NubePuntos nube);
	void eliminaPunto (Punto p); 
	/**
	 * @return La media de las coordenadas X de los puntos de la nube. 
	 * Si no hay ningún punto en la nube, se eleva IllegalArgumentException.
	 */
	Double calculaMediaX();
	
	/**
	 * @return La media de las coordenadas Y de los puntos de la nube.
	 * Si no hay ningún punto en la nube, se eleva IllegalArgumentException.
	 */
	Double calculaMediaY();
	/**
	 * @return true si existe algún punto de la nube que esté en el eje X.
	 */
	Boolean hayAlgunPuntoEnEjeX();
	/**
	 * @param distancia umbral de la distancia
	 * @return true si todos los puntos de  la nube están a una distancia del origen de 
	 * coordenadas menor que distancia
	 */
	Boolean estanTodosADistanciaMenorQue(Double distancia);

	/**
	 * @param umbral 
	 * @return Devuelve el número de puntos de la nube con coordenada X mayor que el 
	 * umbral dado como parámetro.
	 */
	Integer cuentaPuntosConXMayorA(Double umbral);
	/**
	 * @return Devuelve un array de cinco elementos que contendrá el 
	 * número de puntos de la nube que hay en cada cuadrante y en los ejes.
	 */
	Integer [] cuentaPuntosPorCuadrante();
	
	/**
	 * @return Devuelve una NubePuntos con aquellos puntos que estén a 
	 * una distancia del origen menor que la  dada como parámetro.
	 */
	NubePuntos seleccionaPuntos (Double distancia);
	
	Punto getPuntoMasDistante();
	
	/**
	 * @param p
	 * @return
	 */
	Punto getPuntoMasCercano(Punto p);
	/**
	 * 
	 */
	void aplicaSimetriaEjeX();
	/**
	 * @return
	 */
	Double sumaCoordenadasXPrimerCuadrante();
	Double  multiplicaSumaCoordenadasXeY (Cuadrante c);
	/**
	 * @return
	 */
	List<Punto> getPuntosBisectriz();
	/**
	 * @param c
	 * @return
	 */
	int getIndicePunto(Cuadrante c);
}
