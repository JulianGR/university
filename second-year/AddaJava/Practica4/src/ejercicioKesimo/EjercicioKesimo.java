package ejercicioKesimo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class EjercicioKesimo {
	public static void main(String[] args) {
		List<Cancion> canciones = cancionesTest();
		Cancion c= us.lsi.recursivos.problemasdelistas.ProblemasDeListas.getKesimo(canciones, 3, Comparator.comparing (Cancion::getDuracion));
		System.out.println("La cancion según duración en posición 3 sería" + c);
	}
	
	
	
private static List <Cancion> cancionesTest(){
	List <Cancion> res= new ArrayList<>();
	
	res.add( new CancionImpl ("Cancion 1", Duration.ofSeconds(100)));
	res.add( new CancionImpl ("Cancion 2", Duration.ofSeconds(200)));
	res.add( new CancionImpl ("Cancion 3", Duration.ofSeconds(300)));
	res.add( new CancionImpl ("Cancion 4", Duration.ofSeconds(400)));
	res.add( new CancionImpl ("Cancion 5", Duration.ofSeconds(500)));
	res.add( new CancionImpl ("Cancion 6", Duration.ofSeconds(600)));	
	res.add( new CancionImpl ("Cancion 7", Duration.ofSeconds(700)));
	res.add( new CancionImpl ("Cancion 8", Duration.ofSeconds(800)));
	res.add( new CancionImpl ("Cancion 9", Duration.ofSeconds(900)));
	
	
	return res;
}
}
