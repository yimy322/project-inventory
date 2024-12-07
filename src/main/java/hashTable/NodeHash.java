package hashTable;

public class NodeHash {
	public int key;
	public Object value;
	public NodeHash next;
	public NodeHash() {
	}
	public NodeHash(int key, Object value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
	@Override
	public String toString() {
		return value != null ? value.toString() : "null";
	}
}
