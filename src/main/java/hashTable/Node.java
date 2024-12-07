package hashTable;

public class Node {
	public int key;
	public Object value;
	public Node next;
	public Node() {
	}
	public Node(int key, Object value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
	@Override
	public String toString() {
		return "Node [key=" + key + ", value=" + value + ", next=" + next + "]";
	}
}
