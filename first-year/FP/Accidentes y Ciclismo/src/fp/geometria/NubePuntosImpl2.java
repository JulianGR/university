package fp.geometria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NubePuntosImpl2 extends NubePuntosImpl {

	public Double calculaMediaX() {
		return null;

	}
	public Boolean hayAlgunPuntoEnEjeX() {
		
		return getPuntos().stream()
			.anyMatch(p->p.getY().equals(0.0));
	}


	
	public Boolean estanTodosADistanciaMenorQue(Double distancia) {
		return getPuntos().stream()
		.allMatch(p->p.getDistancia(ORIGEN).compareTo(distancia)<0);
	}

	public Integer cuentaPuntosConXMayorA(Double umbral) {
		return null;
	}

	// Integer [] cuentaPuntosPorCuadrante(): 
	public Integer[] cuentaPuntosPorCuadrante() {
		return null;
	}


	// NubePuntos seleccionaPuntos (Double distancia): 
	public NubePuntos seleccionaPuntos(Double distancia) {
		return null;
	}

	// Punto getPuntoMasDistante(): Devuelve el punto de la nube que está más
	// lejos del origen de coordenadas. Si no hay puntos en la nube, devuelve
	// null.
	public  Punto  getPuntoMasDistante(){
		Comparator<Punto> c =
			Comparator.comparing(p->p.getDistancia(ORIGEN));
		
		return getPuntos().stream()
			.max(c)
			.orElse(null);
		
	}
	
	// Punto getPuntoMasCercano(Punto p): Devuelve el punto de la nube cuya
	// distancia al punto p dado como parámetro es menor. Si no hay puntos en la
	// nube, devuelve null.
	public  Punto getPuntoMasCercano(Punto p){
		 Comparator<Punto> c =
			Comparator.comparing(punto->punto.getDistancia(p));
		
			return getPuntos().stream()
				.min(c)
				.orElse(null);
	}
	// void aplicaSimetriaEjeX(): Le cambia el signo a la coordenada X de todos
	// los puntos de la nube.
	
	public void aplicaSimetriaEjeX(){
	}
	
	// Double sumaCoordenadasXPrimerCuadrante(): Suma las coordenadas X de los
	// puntos del primer cuadrante.
	public Double sumaCoordenadasXPrimerCuadrante(){
		return null;
	}
	
	// Double multiplicaSumaCoordenadasXeY (Cuadrante c): Calcula el producto de
	// la suma de las coordenadas X e Y de los puntos del cuadrante dado como
	// parámetro.
	public Double multiplicaSumaCoordenadasXeY (Cuadrante c){
		return null;
	}
	
	public Punto getPuntoMasCercanoOrigen(Cuadrante c){
		Comparator<Punto> comp =
				Comparator.comparing(p->p.getDistancia(ORIGEN));

		return getPuntos().stream()
				.filter(p->p.getCuadrante().equals(c))
				.min(comp)
				.orElse(null);
	}
	// List<Punto> getPuntosBisectriz(): Devuelve los puntos situados en la
	// bisectriz.
	public List<Punto> getPuntosBisectriz() {
		return null;
	}
	// int getIndicePunto(Cuadrante c): Devuelve el índice del primer punto
	// situado en el cuadrante dado como parámetro.

	public int getIndicePunto(Cuadrante c) {
		return  -1;
	}
	
}
