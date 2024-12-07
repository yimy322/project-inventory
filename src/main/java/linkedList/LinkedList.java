package linkedList;

public class LinkedList {
	public Node head;
	public Node tail;
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
			if(actual.data.equals(data)) {
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
	//retorna el tamano
	public int size() {
		Node actual = head;
		int tam = 0;
		while(actual != null) {
			tam++;
			actual = actual.next;
		}
		return tam;
	}
	public Object get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("indice no valido: " + index);
        }
        Node actual = head;
        int cont = 0;
        //iteramos
        while (actual != null) {
            if (cont == index) {
                return actual.data;
            }
            actual = actual.next;
            cont++;
        }
        //si el indice es mayor
        throw new IndexOutOfBoundsException("indice fuera de rango: " + index);
    }
	
	//para reemplazar un valor por posicion
	public void set(int index, Object data) {
		Node actual = head;
		int contador = 0;
		//recorremos hasta encontrar
		while (actual != null && contador < index) {
			actual = actual.next;
			contador++;
        }
		//si es null
		if (actual == null) {
            System.out.println("indice fuera de rango");
            return;
        }
		//se reemplaza el valor, en este caso un obj
		actual.data = data;
	}
	
	public Node getNode(int pos) {
		Node actual = head;
        int cont = 0;
        //iteramos
        while (actual != null) {
            if (cont == pos) {
                return actual;
            }
            actual = actual.next;
            cont++;
        }
        return actual;
	}
	
}
