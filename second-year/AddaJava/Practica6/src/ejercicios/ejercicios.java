package ejercicios;

import java.util.ArrayList;
import java.util.List;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class ejercicios {

	/*
	 * Encontrar la raíz cuadrada de un número (la raíz cuadrada de un entero a es
	 * el mayor valor de un entero n tal que a >= n^2 ). (Java)
	 * 
	 * (Iterativo, Recursivo y Funcional) a) Usar una búsqueda secuencial en el
	 * rango de 2..n-1 b) Usar una búsqueda binaria en el rango de 2..n-1.
	 */

	public static void main(String[] args) {

		Tree<Integer> t1 = Tree.leaf(1);
		Tree<Integer> t2 = Tree.leaf(4);
		Tree<Integer> t3 = Tree.leaf(3);
		Tree<Integer> t4 = Tree.leaf(5);
		Tree<Integer> t5 = Tree.nary(2, t1, t3, t4);
		Tree<Integer> t6 = Tree.nary(7, t2);
		Tree<Integer> t7 = Tree.nary(7, t6, t5);
		System.out.println(t7);

		BinaryTree<Integer> tb1 = BinaryTree.leaf(1);
		BinaryTree<Integer> tb2 = BinaryTree.leaf(4);
		BinaryTree<Integer> tb3 = BinaryTree.leaf(3);
		BinaryTree<Integer> tb5 = BinaryTree.binary(2, tb1, tb3);
		BinaryTree<Integer> tb6 = BinaryTree.binary(7, tb2, BinaryTree.empty());
		BinaryTree<Integer> tb7 = BinaryTree.binary(7, tb6, tb5);
		System.out.println("\nArbol binario " + tb7);
		System.out.println("Lista Posorden: " + listaPosorden(tb7));

	}
	// Suma de las etiquetas del árbol (binario / n-ario) que son pares
	// (asumiendo que son de tipo entero)

	public static Integer sumaPares(BinaryTree<Integer> bt) {
		Integer res = 0;

		switch (bt.getType()) {
		case Empty:
			res = res + 0;
			break;
		case Leaf:
			if (bt.getLabel() % 2 == 0) {
				res = res + bt.getLabel();
			}

			break;

		case Binary:
			if (bt.getLabel() % 2 == 0) {
				res = res + bt.getLabel() + sumaPares(bt.getLeft()) + sumaPares(bt.getRight());
			} else {
				res = res + sumaPares(bt.getLeft()) + sumaPares(bt.getRight());
			}

		}
		return res;
	}

	public static Integer sumaPares(Tree<Integer> t) {
		Integer res = 0;

		switch (t.getType()) {
		case Empty:
			res = res + 0;
			break;
		case Leaf:
			if (t.getLabel() % 2 == 0) {
				res = res + t.getLabel();
			}

			break;
		// recorrer hijos: Streams o while
		case Nary:
			if (t.getLabel() % 2 == 0) {
				res = res + t.getLabel();
			}
			res = res + t.getChildren().stream().mapToInt(x -> sumaPares(x)).sum();
			break;
		}
		return res;
	}

	private static <T> List<T> raiz(BinaryTree<T> bt) {

		List<T> res = new ArrayList<T>();

		switch (bt.getType()) {
		case Empty:

			break;
		case Leaf:
			res.add(bt.getLabel());
			break;
		case Binary:

			res.addAll(listaPosorden(bt.getLeft()));
			res.addAll(listaPosorden(bt.getRight()));
			res.add(bt.getLabel());

		}

		return res;

	}

	private static <T> List<T> listaPosorden(BinaryTree<T> bt) {

		List<T> res = new ArrayList<T>();

		switch (bt.getType()) {
		case Empty:

			break;
		case Leaf:
			res.add(bt.getLabel());
			break;
		case Binary:

			res.addAll(listaPosorden(bt.getLeft()));
			res.addAll(listaPosorden(bt.getRight()));
			res.add(bt.getLabel());

		}

		return res;

	}

}
