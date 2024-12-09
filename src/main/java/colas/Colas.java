package colas;

import javax.swing.JOptionPane;

import linkedList.Node;

public class Colas {
	public Node head;
	public Node tail;
	public Colas() {
		head = null;
		tail = null;
	}

	//agrega al final de la pila que coincide con la cola o tail de la lista	
	public void enqueue(Object data) {
		Node nuevo = new Node(data);
		if(head==null) {
			head = tail = nuevo;
			return;
		}
		tail.next = nuevo;
		tail = nuevo;
	}

	//eliminar el primer valor ingresado, este caso coincide con la cabaza de la cola
	public Object dequeue() {
		if(head == null) {
			JOptionPane.showMessageDialog(null,"La cola esta vacio!");
			return null;
		}else {
			Object data = tail.data;
			head = head.next;
			return data;
		}
	}
	
	//permite obtener el objeto que se encuantra a la cabeza de la cola, es decir el ultimo elemento que se agrego
	public Object peek() {
		if(head == null) {
			JOptionPane.showMessageDialog(null,"La pila esta vacio!");
			return null;
		}else {
			return tail.data;
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
	
	public Node getNodoAnterior(int posicion) {
		int contador = 0;
        Node actual = head, atras = null;
        while(actual!=null && contador!=posicion){
        	atras = actual;
            actual = actual.next;
            contador++;
        }
        return atras;
	}
	
	public void exchage_position(int pos_a, int pos_b) {
		if(pos_a < 0 || pos_b < 0 || pos_a == pos_b) {
			JOptionPane.showMessageDialog(null, "Valide si los indices ingresados son correctos");
			return;
		}
		
		Node actual_a = null, actual_b = null;
		Node atras_a = this.getNodoAnterior(pos_a);
		Node atras_b = this.getNodoAnterior(pos_b);
		
		if(atras_a == null)
			actual_a = head;
		else
			actual_a = atras_a.next;
		
		if(atras_b == null)
			actual_b = head;
		else
			actual_b = atras_b.next;
		
		if(actual_a == null || actual_b == null) {
			JOptionPane.showMessageDialog(null, "Los indices superan el tamano del arreglo");
			return;
		}
		
		if(atras_a != null)			//si me encuatro con el indice 0, valida que su posterior sea null
			atras_a.next = actual_b;
		else			//si es nulo significa que el valor de la cabeza debe replazarse
			head = actual_b; 		//puesto que validase que estamos contado con indice 0
		
		if(atras_b != null)
			atras_b.next = actual_a;		//se repite la situacion, sucede esto por que
		else						//no hay restriccion sobre si pos_a debe necesariamente ser
			head = actual_a;		//mayor que pos_b, tenemos dos casos pos_a < pos_b y pos_b < pos_a
		
		Node temp = actual_a.next;
		actual_a.next = actual_b.next;
		actual_b.next = temp;
	}
}
