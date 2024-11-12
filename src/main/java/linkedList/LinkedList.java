package linkedList;

public class LinkedList {
	private Node head;
	private Node tail;
	public LinkedList() {
		head = null;
		tail = null;
	}
	//agrega al final
	public void addLast(Object data) {
		Node nuevo = new Node(data);
		if(head==null) {
			head = tail = nuevo;
			return;
		}
		tail.next = nuevo;
		tail = nuevo;
	}
	//agrega al inicio
	public void addFirst(Object data) {
		Node nuevo = new Node(data);
		if(head==null) {
			head = tail = nuevo;
			return;
		}
		nuevo.next = head;
		head = nuevo;
	}
	//actualizar
	public void update(Object data, Object nuevaData) {
		Node actual = head;
		while (actual != null) {
			if(actual.data == data) {
				actual.data = nuevaData;
			}
			actual = actual.next;
		}
	}
	//eliminar
	public void remove(Object data) {
		Node actual = head;
		Node anterior = null;
		//iteramos
		while (actual != null) {
			//se valida que el actual sea igual a lo que estamos buscando
			if(actual.data.equals(data)) {		
				if (anterior != null) {
					anterior.next = actual.next;
	                if(actual.next==null) {
	                	tail = anterior;
	                }
	            } else {
	            	//si no tiene anterior actual sera la cabecera
	            	head = actual.next;
	            }
	            return; 
			}
			anterior = actual;
			actual = actual.next;
		}
	}
	//imprimir lista
	public void print() {
		Node actual = head;
		while(actual != null) {
			System.out.print(actual.data + " <-> ");
			actual = actual.next;
		}
		System.out.println("null");
	}
}
