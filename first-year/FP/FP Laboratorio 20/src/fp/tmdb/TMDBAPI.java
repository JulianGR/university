package fp.tmdb;

import fp.tmdb.pojo.TMDBMovie;
import fp.tmdb.pojo.TMDBNetwork;
import fp.tmdb.pojo.TMDBPerson;
import fp.tmdb.pojo.TMDBReview;

public interface TMDBAPI {

	/**
	 * @param idPersona
	 *            Identificador de la persona dentro del dominio de tmdb
	 * @return Un objeto de tipo TMDB con los datos de la persona que tiene ese id
	 */
	TMDBPerson getPerson(Integer idPersona);

	/**
	 * @param idCadena
	 *            Identificador de la cadena de televisi�n dentro del dominio de
	 *            tmdb
	 * @return Un objeto de tipo TMDBNetwork con los datos de la cadena que tiene
	 *         ese id.
	 */
	TMDBNetwork getNetwork(Integer idCadena);

	/**
	 * @param idMovie
	 *            Identificador de la pel�cula dentro del dominio de tmdb
	 * @return Un objeto de tipo TMDBMovie con los datos de la pel�cula que tiene
	 *         ese id.
	 */
	TMDBMovie getMovie(Integer idMovie);

	/**
	 * @param idReview
	 *            Identificador de la review dentro del dominio de tmdb
	 * @return Un objeto de tipo TMDBReview con los datos de la review que tiene ese
	 *         id.
	 */

	TMDBReview getReview(String idReview);

}