/* Binley Yang
 * CSC 172
 * Shakespeare
 * Email: byang8@u.rochester.edu
 * */

public class LinkedList implements MyLinkedList {
	
	private MyNode head, tail, node;
	private int count = 0;
	
	public LinkedList() {
		setHead(null);
		tail = null;
		node = null;
	}
	
	public void insert(Object x) {
		//expected runtime, O(count)
		if (lookup(x) == (Object)false) {
			if (getHead() == null) {
				setHead(new MyNode());
				tail = new MyNode();
				
				getHead().data = x;
				tail = getHead();
				getHead().next = tail;
			} else {
				tail.next = new MyNode();
				tail = tail.next;
				tail.data = x;
			} count++;
		}
	}
	
	public boolean isEmpty() {
		if (getHead() == null) return true;
		return false;
	}
	
	public void printList() {
		//expected runtime, O(count)
		node = getHead();
		for (int i = 0; i < count; i++) {
			System.out.print(node.data + ", ");
			node = node.next;
		}
		System.out.println();
	}
	
	public int getListSize() {
		return count;
	}

	public Object lookup(Object x) {
		node = getHead();
		for (int i = 0; i < count; i++) {
			if(node.data == x) return true;
			node = node.next;
		}
		return false;
	}
	
	public void delete(Object x) {
		if (lookup(x) == (Object)true) {
			node = getHead();
			MyNode isRemoved = new MyNode();
			for (int i = 0; i < count; i++) {
				if (node.next.data == x) {
					isRemoved = node.next;
					if (node.next.next != null) node.next = node.next.next;
					else node.next = tail;
					isRemoved = null;
					count--;
					break;
				} node = node.next;
			}
		}
	}

	public MyNode getHead() {
		return head;
	}

	public void setHead(MyNode head) {
		this.head = head;
	}
}
