package arbol;

public class Arbol {
	Nodo raiz;

	public Nodo insertarRecursivo(Nodo nodo, int valor) {
		if (nodo == null) {
			return new Nodo(valor);
		}
		if (valor < nodo.valor) {
			nodo.izquierda = insertarRecursivo(nodo.izquierda, valor);
		} else {
			nodo.derecha = insertarRecursivo(nodo.derecha, valor);
		}
		return nodo;
	}

	public void insertar(int valor) {
		raiz = insertarRecursivo(raiz, valor);
	}

	public void imprimirInorden(Nodo nodo) {
		if (nodo != null) {
			imprimirInorden(nodo.izquierda);
			System.out.print(nodo.valor + " ");
			imprimirInorden(nodo.derecha);
		}
	}

	public void imprimirArbol() {
		imprimirInorden(raiz);
	}

	public Nodo buscar(int valor) {
		return buscarRecursivo(raiz, valor);
	}

	private Nodo buscarRecursivo(Nodo nodo, int valor) {
		if (nodo == null) {
			return null;
		}
		if (valor == nodo.valor) {
			return nodo;
		}
		if (valor < nodo.valor) {
			return buscarRecursivo(nodo.izquierda, valor);
		}
		return buscarRecursivo(nodo.derecha, valor);
	}
}
