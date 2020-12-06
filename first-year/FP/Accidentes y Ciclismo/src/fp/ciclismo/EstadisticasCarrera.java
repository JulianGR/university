package fp.ciclismo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author reinaqu_2
 *
 */
@JsonIgnoreProperties({ "numeroGanadores", "ganadoresConRecorridoInferiorA", "hanGanadoTodosAlgunaEtapa",
		"buscaGanador", "kmMenorRecorrido", "mediaEtapasGanadas", "ganadorMasRapido",
		"guardaGanadoresNacionalidadConAnyos", "guardaGanadoresDeAnyosOrdenados", "ganadoresPorNacionalidad",
		"cuentaGanadoresPorNacionalidad", "hayAlgunGanadorNacionalidad" })

public interface EstadisticasCarrera {
	/**
	 * @return El nombre de la carrera
	 */
	String getNombreCarrera();

	/**
	 * @return Una lista con todos los ganadores del tour
	 */
	@JsonDeserialize(contentAs = GanadorImpl.class)
	List<Ganador> getGanadores();

	/**
	 * @param km
	 *            Umbral de kilómetros recorrido por el ciclista en el tour
	 * @return Una lista con los nombres de los ganadores que han recorrido menos
	 *         kilómetros que los dados como parámetro
	 */
	List<String> getGanadoresConRecorridoInferiorA(Integer km);

	/**
	 * @return true si todos los ganadores han ganado alguna etapa en la edición en
	 *         la que ganaron el tour
	 */
	Boolean hanGanadoTodosAlgunaEtapa();

	/**
	 * @return El número de ganadores distintos del tour. Si un ciclista ha ganado
	 *         el tour en varias ediciones, solo se debe contar una vez.
	 */
	Long getNumeroGanadores();

	/**
	 * @return Un conjunto con los nombres de los equipos que han ganado la carrera.
	 */
	@JsonDeserialize(contentAs = String.class)
	Set<String> getEquiposGanadores();

	/**
	 * @param nombre
	 *            Nombre del ciclista
	 * @return El primer ganador que concuerde con el nombre dado como parámetro. Si
	 *         no se encuentra ninguno, se devuelve null
	 */
	Ganador buscaGanador(String nombre);

	/**
	 * @param anyo
	 *            Año de celebración de la carrera
	 * @return El ciclista ganador de ese año, si es que existe. Si no se encuentra
	 *         ninguno, se devuelve null.
	 */
	Ganador buscaGanador(Integer anyo);

	/**
	 * @return La suma de los kilómetros recorridos en todas las ediciones de la
	 *         carrera.
	 */
	Integer calculaDistanciaTotal();

	/**
	 * @return El número de kilómetros de la edición en la que se ha hecho el
	 *         recorrido más corto. Si no se puede calcular, devuelve cero.
	 * 
	 */
	Integer getKmMenorRecorrido();

	/**
	 * @param equipo
	 *            Nombre de un equipo.
	 * @return Número medio de etapas que han ganado los ciclistas que corren en ese
	 *         equipo. Si no se puede calcular, devuelve cero.
	 */
	Double getMediaEtapasGanadas(String equipo);

	/**
	 * @return El nombre del ganador que ha alcanzado una mayor velocidad media en
	 *         la edición en la que ha sido ganador. Si no se puede calcular
	 *         devuelve null.
	 */

	String getGanadorMasRapido();

	/**
	 * @return Map en el que las claves son las nacionalidades y los valores la
	 *         lista de ganadores de esa nacionalidad
	 */
	Map<String, List<Ganador>> getGanadoresPorNacionalidad();

	/**
	 * @return Map en el que las claves son las nacionalidades, y los valores el
	 *         número de veces que un corredor de esa nacionalidad ha ganado la
	 *         carrera
	 */
	Map<String, Long> cuentaGanadoresPorNacionalidad();

	Map<String, Integer> getTotalEtapasGanadasPorEquipo();

	/**
	 * @param nacionalidad
	 *            Nacionalidad del ciclista
	 * @return Devuelve cierto si hay algún ganador de la nacionalidad dada como
	 *         parámetro.
	 */
	Boolean hayAlgunGanadorNacionalidad(String nacionalidad);

	/**
	 * @param nombreFichero
	 *            Nombre del fichero en el que se guarda la información.
	 * @param nacionalidad
	 *            Nacionalidad buscada. Guarda en un fichero los ganadores de la
	 *            carrera de la nacionalidad dada como parámetro, con los años en
	 *            los que ha ganado. Un ejemplo de línea del fichero generado es
	 *            Alberto Contador-> [2007, 2009]
	 */
	void guardaGanadoresNacionalidadConAnyos(String nombreFichero, String nacionalidad);

	/**
	 * @param nombreFichero
	 *            Nombre fichero
	 * @param anyoInicial
	 *            Año inicial del intervalo. Se incluye en el resultado
	 * @param anyoFinal
	 *            Año final del intervalo. No se incluye en el resultado.
	 */
	void guardaGanadoresDeAnyosOrdenados(String nombreFichero, Integer anyoInicial, Integer anyoFinal);

}
