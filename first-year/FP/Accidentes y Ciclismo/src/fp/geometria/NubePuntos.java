package fp.geometria;

import java.util.Collection;
import java.util.List;

public interface NubePuntos {
	/**
	 * @return Una lista con los puntos de la nube
	 */
	List<Punto> getPuntos();
	/**
	 * @return El n�mero de puntos de la nube
	 */
	Integer getNumPuntos();
	void incorporaPunto (Punto p);
	void incorporaPuntos (Collection<Punto> colPuntos);
	void incorporaPuntos (NubePuntos nube);
	void eliminaPunto (Punto p); 
	/**
	 * @return La media de las coordenadas X de los puntos de la nube. 
	 * Si no hay ning�n punto en la nube, se eleva IllegalArgumentException.
	 */
	Double calculaMediaX();
	
	/**
	 * @return La media de las coordenadas Y de los puntos de la nube.
	 * Si no hay ning�n punto en la nube, se eleva IllegalArgumentException.
	 */
	Double calculaMediaY();
	/**
	 * @return true si existe alg�n punto de la nube que est� en el eje X.
	 */
	Boolean hayAlgunPuntoEnEjeX();
	/**
	 * @param distancia umbral de la distancia
	 * @return true si todos los puntos de  la nube est�n a una distancia del origen de 
	 * coordenadas menor que distancia
	 */
	Boolean estanTodosADistanciaMenorQue(Double distancia);

	/**
	 * @param umbral 
	 * @return Devuelve el n�mero de puntos de la nube con coordenada X mayor que el 
	 * umbral dado como par�metro.
	 */
	Integer cuentaPuntosConXMayorA(Double umbral);
	/**
	 * @return Devuelve un array de cinco elementos que contendr� el 
	 * n�mero de puntos de la nube que hay en cada cuadrante y en los ejes.
	 */
	Integer [] cuentaPuntosPorCuadrante();
	
	/**
	 * @return Devuelve una NubePuntos con aquellos puntos que est�n a 
	 * una distancia del origen menor que la  dada como par�metro.
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
