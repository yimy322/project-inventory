package hashTable;

public class HashTable {
	
	private int tam;
	private NodeHash[] arr;
	//al crear el hashtable pasamos el tamaño para asignar al arreglo de nodos
	public HashTable(int tam) {
		this.tam = tam;
		this.arr = new NodeHash[tam];
	}
	//metodo para hashear
	public int hash(int valor) {
		return valor%this.tam;
	}
	//agregar al hasttable
	public void addToHashTable(NodeHash nodo) {
		int indice = hash(nodo.key);
		nodo.next = this.arr[indice];
		this.arr[indice] = nodo;
	}
	//imprimir elementos
	public void print() {
		for(NodeHash e:arr) {
			System.out.println(e);
		}
	}
	//buscar por clave
	public NodeHash search(int clave) {
		int indice = hash(clave);
		NodeHash actual = this.arr[indice];
		NodeHash nuevo = new NodeHash();
		if(actual == null) {
			return null;
		}
		while(actual != null) {
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
