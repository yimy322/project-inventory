package pilas;

import javax.swing.JOptionPane;

public class Pilas {
	public Node head;
	public Node tail;
	public Pilas() {
		head = null;
		tail = null;
	}

	//agrega al inicio de la pila
	public void push(Object data) {
		Node nuevo = new Node(data);
		if(head==null) {
			head = tail = nuevo;
			return;
		}
		nuevo.next = head;
		head = nuevo;
	}

	//eliminar el ultimo valor ingresado, este caso coincide con la cabaza de la cola
	public Object pop() {
		if(head == null) {
			JOptionPane.showMessageDialog(null,"La pila esta vacio!");
			return null;
		}else {
			Object data = head.data;
			head = head.next;
			return data;
		}
	}
	
	//permite obtener el objeto que se encuantra a la cabeza de la cola, es decir el ultimo elemento que se agrego
	public Object peak() {
		if(head == null) {
			JOptionPane.showMessageDialog(null,"La pila esta vacio!");
			return null;
		}else {
			return head.data;
		}
	}
	
	//permite identificar si la pila esta vacio
	public boolean isEmpty() {
		return (head == null);
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
