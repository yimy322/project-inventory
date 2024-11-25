package hashTable;

public class HashTable {
	
	private int tam;
	private Node[] arr;
	//al crear el hashtable pasamos el tamaño para asignar al arreglo de nodos
	public HashTable(int tam) {
		this.tam = tam;
		this.arr = new Node[tam];
	}
	//metodo para hashear
	public int hash(int valor) {
		return valor%this.tam;
	}
	//agregar al hasttable
	public void addToHashTable(Node nodo) {
		int indice = hash(nodo.key);
		nodo.next = this.arr[indice];
		this.arr[indice] = nodo;
	}
	//imprimir elementos
	public void print() {
		for(Node e:arr) {
			System.out.println(e);
		}
	}
	//buscar por clave
	public Node search(int clave) {
		int indice = hash(clave);
		Node actual = this.arr[indice];
		Node nuevo = new Node();
		if(actual == null) {
			return null;
		}
		while(actual.next != null) {
			if(actual.key == clave) {
				nuevo.key = actual.key;
				nuevo.value = actual.value;
				return nuevo;
			}
			actual = actual.next;
		}
		return actual;
	}
	
}
