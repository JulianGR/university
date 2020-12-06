package problema1;

import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio138 {
	/*
	 * Decidir si un árbol binario es equilibrado. Un árbol binario está equilibrado
	 * si bien es vacío o bien cumple que la diferencia de alturas de sus dos hijos
	 * es como mucho 1 y además ambos están equilibrados.
	 */

	public static void main(String[] args) {
		System.out.println("==============TESTS=================");

		BinaryTree<Integer> vacio = BinaryTree.empty();
		System.out.println(vacio + " este debería dar true y da --> " + esEquilibrado(vacio));
		if (esEquilibrado(vacio).equals(true)) {
			System.out.println("[OK]" + "\n");
		} else {
			System.out.println("[NOT OK]" + "\n");
		}

		BinaryTree<Integer> hoja = BinaryTree.leaf(1);
		System.out.println(hoja + " este debería dar true y da --> " + esEquilibrado(hoja));
		if (esEquilibrado(hoja).equals(true)) {
			System.out.println("[OK]" + "\n");
		} else {
			System.out.println("[NOT OK]" + "\n");
		}

		BinaryTree<Integer> binarioSimple = BinaryTree.binary(1, hoja, BinaryTree.empty());
		System.out.println(binarioSimple + " este debería dar true y da --> " + esEquilibrado(binarioSimple));

		if (esEquilibrado(binarioSimple).equals(true)) {
			System.out.println("[OK]" + "\n");
		} else {
			System.out.println("[NOT OK]" + "\n");
		}

		BinaryTree<Integer> t1 = BinaryTree.leaf(1);
		BinaryTree<Integer> t2 = BinaryTree.leaf(2);
		BinaryTree<Integer> t3 = BinaryTree.leaf(1);
		BinaryTree<Integer> t5 = BinaryTree.binary(2, t1, t3);
		BinaryTree<Integer> t6 = BinaryTree.binary(3, t2, BinaryTree.empty());
		BinaryTree<Integer> t7 = BinaryTree.binary(2, t6, t5);
		System.out.println(t7 + " este debería dar true y da --> " + esEquilibrado(t7));
		if (esEquilibrado(t7).equals(true)) {
			System.out.println("[OK]" + "\n");
		} else {
			System.out.println("[NOT OK]" + "\n");
		}

		BinaryTree<Integer> t11 = BinaryTree.leaf(2);
		BinaryTree<Integer> t12 = BinaryTree.leaf(5);
		BinaryTree<Integer> t13 = BinaryTree.leaf(0);
		BinaryTree<Integer> t14 = BinaryTree.binary(8, t11, BinaryTree.empty());
		BinaryTree<Integer> t15 = BinaryTree.binary(11, t13, BinaryTree.empty());
		BinaryTree<Integer> t16 = BinaryTree.binary(1, t14, t12);
		BinaryTree<Integer> t17 = BinaryTree.binary(2, t16, t15);
		BinaryTree<Integer> t18 = BinaryTree.binary(37, t11, BinaryTree.empty());
		BinaryTree<Integer> t19 = BinaryTree.binary(12, t18, t17);
		System.out.println(t19 + " este debería dar false y da --> " + esEquilibrado(t19));
		if (esEquilibrado(t19).equals(false)) {
			System.out.println("[OK]");
		} else {
			System.out.println("[NOT OK]");
		}

	}

	public static <T> Boolean esEquilibrado(BinaryTree<T> bt) {
		Boolean res = false;

		switch (bt.getType()) {

		case Empty:
			res = true;
			break;
		case Leaf:
			res = true;
			break;
		default:

			Integer aux = bt.getLeft().getHeight() - bt.getRight().getHeight();
			if (-1 <= aux && aux <= 1) {
				res = esEquilibrado(bt.getLeft()) && esEquilibrado(bt.getRight());

			} else {
				res = false;
			}

		}
		return res;

	}
}
