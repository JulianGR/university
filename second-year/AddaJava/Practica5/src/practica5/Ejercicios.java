package practica5;

import java.util.List;

import us.lsi.common.Streams2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TreeType;

public class Ejercicios {
	public static void main(String[] args) {

		Tree<Integer> t1 = Tree.leaf(1);
		Tree<Integer> t2 = Tree.leaf(4);
		Tree<Integer> t3 = Tree.leaf(3);
		Tree<Integer> t4 = Tree.leaf(5);
		Tree<Integer> t5 = Tree.nary(2, t1, t3, t4);
		Tree<Integer> t6 = Tree.nary(7, t2);
		Tree<Integer> t7 = Tree.nary(7, t6, t5);
		System.out.println(t7);
		System.out.println(contieneEtiquetaConRecursivoNoFuncional(t7, 3));
		System.out.println(contieneEtiquetaConRecursivoFuncional(t7, 3));
		System.out.println(contieneEtiquetaConRecursivoNoFuncional(t7, 8));
		System.out.println(contieneEtiquetaConRecursivoFuncional(t7, 8));

		BinaryTree<Integer> tb1 = BinaryTree.leaf(1);
		BinaryTree<Integer> tb2 = BinaryTree.leaf(4);
		BinaryTree<Integer> tb3 = BinaryTree.leaf(3);
		BinaryTree<Integer> tb5 = BinaryTree.binary(2, tb1, tb3);
		BinaryTree<Integer> tb6 = BinaryTree.binary(7, tb2, BinaryTree.empty());
		BinaryTree<Integer> tb7 = BinaryTree.binary(7, tb6, tb5);
		System.out.println("\nArbol binario " + tb7);
		System.out.println("Obtener copia simétrica: " + obtenerCopiaSimetrica(tb7));

	}

//P1 – (EJ104): Determinar si un árbol n-ario contiene una etiqueta dada

	public static <E> Boolean contieneEtiquetaConRecursivoNoFuncional(Tree<E> arbol, E etiqueta) {
		Boolean res = false;
		switch (arbol.getType()) {

		case Empty:
			res = false;
			break;
		case Leaf:
			res = arbol.getLabel().equals(etiqueta);
			break;
		default:
			if (arbol.getLabel().equals(etiqueta)) {
				res = true;
			} else {
				List<Tree<E>> hijos = arbol.getChildren();
				int i = 0;
				// !res para que en el momento que haya uno, fuera
				while (i < hijos.size() && !res) {
					res = contieneEtiquetaConRecursivoNoFuncional(hijos.get(i), etiqueta);
					i++;
				}
			}
		}

		return res;
	}

	public static <E> Boolean contieneEtiquetaConRecursivoFuncional(Tree<E> arbol, E etiqueta) {
		Boolean res = false;
		switch (arbol.getType()) {

		case Empty:
			res = false;
			break;
		case Leaf:
			res = arbol.getLabel().equals(etiqueta);
			break;
		default:
			res = arbol.getLabel().equals(etiqueta)
					|| arbol.getChildren().stream().anyMatch(a -> contieneEtiquetaConRecursivoFuncional(a, etiqueta));
		}
		return res;
	}

	public static <E> BinaryTree<E> obtenerCopiaSimetrica(BinaryTree<E> bt) {
		BinaryTree<E> res = null;

		switch (bt.getType()) {
		case Empty:
			res = BinaryTree.empty();
			break;
		case Leaf:
			res = BinaryTree.leaf(bt.getLabel());
			break;
		case Binary:

			res = BinaryTree.binary(bt.getLabel(), obtenerCopiaSimetrica(bt.getRight()),
					obtenerCopiaSimetrica(bt.getLeft()));
			break;
		}

		return res;

	}
}
//// P2 – (EJ106): Comprobar si dos árboles n-arios son iguales.
//
//	public static <E> Boolean sonIgualesConRecursivoFuncional(Tree<E> t1, Tree<E> t2) {
//		Boolean res = false;
//		TreeType type1 = t1.getType();
//		TreeType type2 = t2.getType();
//
//		if (type1 != type2) {
//			res = false;
//
//			break;
//		}
//
//		switch (type1) {
//
//		case Empty:
//			res = true;
//			break;
//		case Leaf:
//			t1.getLabel().equals(t2.getLabel());
//			break;
//		default:
//			res = t2.isNary() && t1.isNary() && t1.getLabel().equals(t2.getLabel())
//					&& Streams2.zip(t1.getChildren().stream(), t2.getChildren().stream(),
//							(t1, t2) -> sonIgualesConRecursivoFuncional(t1, t2).allMatch(x -> x == true));
//
//		}
//	}
//
//	public static <E> Boolean sonIgualesConRecursivoNoFuncional(Tree<E> t1, Tree<E> t2) {
//
//		Boolean res = false;
//		TreeType type1 = t1.getType();
//		TreeType type2 = t2.getType();
//
//		if (type1 != type2) {
//			res = false;
//			break;
//		}
//		switch (type1) {
//
//		case Empty:
//			res = true;
//			break;
//		case Leaf:
//			t1.getLabel().equals(t2.getLabel());
//			break;
//		default:
//			List<Tree<E>> hijos1 = t1.getChildren();
//			List<Tree<E>> hijos2 = t2.getChildren();
//
//			if (hijos2 == null || hijos1.size() != hijos2.size() || t1.getLabel().equals(t2.getLabel())) {
//				res = false;
//			} else {
//				int i = 0;
//				while ((i < hijos1.size()) && res) {
//					res = sonIgualesConRecursivoNoFuncional(t1.getChild(i), t2.getChild(i));
//
//				}
//			}
//		}
//
//		return res;
//	}
//
//// P3 – (EJ102): Obtener la menor etiqueta de un árbol binario respecto a un
//// orden.
//	/*
//	 * public static <E> E menor(BinaryTree<E> t1, Comparator<E> cp) { E res = null;
//	 * switch (t1.getType()) { case Empty: res = null; break; case Leaf: res =
//	 * t1.getLabel(); break;
//	 * 
//	 * default: E menorIzq = menor(t1.getLeft(), cp); E menorDcha =
//	 * menor(t1.getRight(), cp);
//	 * 
//	 * 
//	 * //hacer if para comparar if
//	 * 
//	 * } return res;
//	 * 
//	 * }
//	 */
//
//// P4 – (EJ108): Obtener la copia simétrica de un árbol binario
//	/*
//	 * public static <E> BinaryTree<E> copiaSimetricaNoFuncional(BinaryTree<E> t1) {
//	 * 
//	 * <T> BinaryTree<T> res= new BinaryTree<T>();
//	 * 
//	 * switch (t1.getType()) {
//	 * 
//	 * case Empty: res = true; break; case Leaf:
//	 * t1.getLabel().equals(t2.getLabel()); break; default: List<Tree<E>> hijos1 =
//	 * t1.getChildren(); List<Tree<E>> hijos2 = t2.getChildren();
//	 * 
//	 * if( hijos2==null || hijos1.size()!= hijos2.size() ||
//	 * t1.getLabel().equals(t2.getLabel())) { res= false; }else { int i=0; while
//	 * ((i< hijos1.size())&&res) { res=
//	 * sonIgualesConRecursivoNoFuncional(t1.getChild(i), t2.getChild(i));
//	 * 
//	 * 
//	 * } } }
//	 */
//}