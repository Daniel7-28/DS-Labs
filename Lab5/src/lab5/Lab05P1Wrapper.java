package lab5;

public class Lab05P1Wrapper {
	
	public static interface List<E> {
		/* Interface methods are public by default */
		public void add(E e);
		public void add(int index, E e) throws IndexOutOfBoundsException;
		public boolean remove(int index);
		public E get(int index) throws IndexOutOfBoundsException;
		public E set(int index, E e) throws IndexOutOfBoundsException;
		public int size();
		public boolean isEmpty();
		public E[] toArray();
		public int removeAll(E e);
		public E first();
		public E last();
		public int firstIndex(E e);
		public int lastIndex(E e);
		public boolean contains(E e);
		public void clear();
		
	}
	
	public static class RecursiveLinkedList<E> implements List<E> {

		private Node<E> head; // References first data node
		private int currentSize;

		@SuppressWarnings("unused")
		private static class Node<E> {
			private E value;
			private Node<E> next;

			public Node(E value, Node<E> next) {
				this.value = value;
				this.next = next;
			}

			public Node(E value) {
				this(value, null); // Delegate to other constructor
			}

			public Node() {
				this(null, null); // Delegate to other constructor
			}

			public E getValue() {
				return value;
			}

			public void setValue(E value) {
				this.value = value;
			}

			public Node<E> getNext() {
				return next;
			}

			public void setNext(Node<E> next) {
				this.next = next;
			}

			public void clear() {
				value = null;
				next = null;
			}				
		} // End of Node class

		public RecursiveLinkedList() {
			head = null;
			currentSize = 0;
		}
		
		@Override
		public int size() {
			return currentSize;
		}
		
		@Override
		public boolean isEmpty() {
			return size() == 0;
		}

		/*TODO (Part 2) IMPLEMENT THE MISSING INTERFACE METHODS HERE*/
		
		@Override
		public int removeAll(E e) {
			int count = 0;
			while(firstIndex(e) != -1 && remove(firstIndex(e))) count++;
			return count;
		}

		@Override
		public E first() {
			
			return head != null ? head.getValue(): null;
		}

		@Override
		public E last() {
			if(currentSize == 0) return null;
			
			Node<E> currentNode = head;
			while(currentNode.getNext() != null) currentNode = currentNode.getNext();
			
			return currentNode.getValue();
		}

		@Override
		public int firstIndex(E e) {
			int i = 0;
			Node<E> currentNode = head;
			while(currentNode != null) {
				if(currentNode.getValue().equals(e))
					return i;
				currentNode = currentNode.getNext();
				i++;	
			}
			return -1;
		}

		@Override
		public int lastIndex(E e) {
			int lastIndex = 0;
			int i = 0;
			Node<E> currentNode = head;
			while(currentNode != null) {
				if(currentNode.getValue().equals(e))
					 lastIndex = i;
				currentNode = currentNode.getNext();
				i++;
			}
			return lastIndex;
		}

		@Override
		public boolean contains(E e) {
			for (Node<E> current = head; current != null; current = current.getNext()) {
				if(current.getValue().equals(e))
					return true;
			}
			return false;
		}

		@Override
		public void clear() {
			while(currentSize > 0) remove(0);
			
		}
		
		/*TODO (Part 2) IMPLEMENT THE MISSING INTERFACE METHODS HERE*/

		/*DO NOT MODIFY ANY OF THESE METHODS*/
		@Override
		public E get(int index) throws IndexOutOfBoundsException {
			if (index < 0 || index >= size()) 
				throw new IndexOutOfBoundsException(
						"RecursiveLinkedList.get: invalid index = " + index); 
			// index is valid
			return recGet(head, index); 
		}

		@Override
		public void add(E e) {
			add(size(), e); // Add at the end of the list
		}

		@Override
		public void add(int index, E e) throws IndexOutOfBoundsException {
			if (index < 0 || index > size()) 
				throw new IndexOutOfBoundsException(
						"RecursiveLinkedList.add: invalid index = " + index); 
			// index is valid for the add operation
			head = recAdd(head, index, e); 
			currentSize++;
		}

		@Override
		public boolean remove(int index) {
			if (index < 0 || index >= size()) 
				throw new IndexOutOfBoundsException(
						"RecursiveLinkedList.get: invalid index = " + index); 
			head = recRemove(head, index);
			this.currentSize--;
			return true; 
		}

		@Override
		public E set(int index, E e) throws IndexOutOfBoundsException {
			if (index < 0 || index >= size()) 
				throw new IndexOutOfBoundsException(
						"RecursiveLinkedList.set: invalid index = " + index); 

			// index is valid for set operation
			return recSet(head, index, e);  
		}

		/*******************************/
		/* Auxiliary recursive methods */
		/*******************************/

		/*TODO (Part 1)*/
		
		/**
		 * TODO ADD YOUR DESCRIPTION HERE 
		 * 
		 * @param f		reference node
		 * @param i		Index value of the node must be get
		 * @return		return the node that we want
		 */
		private static <E> E recGet(Node<E> f, int i) {
			if (i == 0)
				return f.getValue(); 
			else 
				return recGet(f.getNext(), i-1); 
		} 

		/**
		 * TODO ADD YOUR DESCRIPTION HERE  
		 * 
		 * @param f		The node we want to remove
		 * @param i		Index value of where new node must be remove
		 * @return		value we already removed
		 */
		@SuppressWarnings("unused")
		private static <E> Node<E> recRemove(Node<E> f, int i) {
			if (i == 0) { 
				Node<E> ntd = f; 
				f = f.getNext(); 
				ntd.clear(); 
			}
			else
				f.setNext(recRemove(f.getNext(), i-1)); 

			return f; 
		} 

		/**
		 * Inserts a new node in the linked list whose first node is being
		 * referenced by f so that the new node contains the data element e and it
		 * ends up occupying the position with index value i. Finally, it returns
		 * the reference to the first node of the list that results after the
		 * insertion is completed. It presumes that the list whose first node is
		 * f has at least i nodes.
		 * 
		 * @param f		First node of linked list where node must be inserted
		 * @param i		Index value of where new node must be inserted
		 * @param e		Value or element that must be contained within the new node
		 */
		private static <E> Node<E> recAdd(Node<E> f, int i, E e) { 

			if (i == 0) { 
				Node<E> newNode = new Node<>(e, f);
				return newNode;
			}
			else
				f.setNext(recAdd(f.getNext(), i-1, e)); 

			return f; 
		} 


		/**
		 * Returns the value in the node corresponding to the index value i in
		 * the linked list whose first node is being referenced by f. This value will
		 * be replaced by the element given in the parameter given (e). On any such
		 * list the first node is the one associated to index 0, the second node
		 * is the one associated with index 1, and so on. It presumes that the
		 * list whose first node is f has at least i+1 nodes. 
		 * 
		 * @param f		First node of linked list to traverse
		 * @param i		Index value of node whose value should be returned
		 * @param e 	Value that will replace whatever value f holds
		 * @return		Value within node at index i
		 */
		private static <E> E recSet(Node<E> f, int index, E e) { 
			if(index == 0) {
				E temp = f.getValue();
				f.setValue(e);
				return temp;
			}
			else
				return recSet(f.getNext(), index - 1, e); 
		}

		@SuppressWarnings("unchecked")
		@Override
		public E[] toArray() {
			E[] theArray = (E[]) new Object[size()];
			int i = 0;
			for (Node<E> curNode = head; curNode != null; curNode = curNode.getNext())
				theArray[i++] = curNode.getValue();
			return theArray;
		}
	}
}