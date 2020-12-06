package puzzlegv;

import us.lsi.astar.AStarGraph;
import us.lsi.graphs.SimpleEdge;
import us.lsi.graphs.SimpleVirtualGraph;

public class GrafoPuzzle 
	extends SimpleVirtualGraph<VerticePuzzle, SimpleEdge<VerticePuzzle>> 
	implements AStarGraph<VerticePuzzle, SimpleEdge<VerticePuzzle>>{
	
	public static GrafoPuzzle create(VerticePuzzle... v) {
		return new GrafoPuzzle();
	}
	protected GrafoPuzzle(VerticePuzzle... v) {
		super(v);
	}
}
