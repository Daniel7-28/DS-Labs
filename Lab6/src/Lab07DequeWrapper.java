public class Lab07DequeWrapper {

	public static interface Deque<E> {

		public void addFirst(E elm);
		public void addLast(E elm);
		public E removeFirst();
		public E removeLast();
		public E getFirst();
		public E getLast();
		public boolean removeFirstOccurrence(E elm);
		public boolean removeLastOccurrence(E elm);
		public int size();
		public boolean isEmpty();

		// DO NOT USE THIS IN EXERCISES
		public String[] toArray(); // DO NOT REMOVE, TEST WILL FAIL
	}

	/**
	 * Implementation of Deque ADT using a Circular Doubly-Linked Queue
	 *
	 * @param <E>
	 */
	public static class CircularDoublyLinkedQueque<E> implements Deque<E> {

		private class Node<E> {
			private E element;
			private Node<E> next, prev;

			public Node(E elm, Node<E> next, Node<E> prev) {
				this.element = elm;
				this.next = next;
				this.prev = prev;
			}
			public Node(E elm, Node<E> next) {
				this(elm, next, null);
			}

			public Node(E elm) {
				this(elm, null, null);
			}

			public Node() {
				this(null, null, null);
			}

			public E getElement() {
				return element;
			}
			public void setElement(E element) {
				this.element = element;
			}
			public Node<E> getNext() {
				return next;
			}
			public void setNext(Node<E> next) {
				this.next = next;
			}
			public Node<E> getPrev() {
				return prev;
			}
			public void setPrev(Node<E> prev) {
				this.prev = prev;
			}
			public void clear() {
				next = null;
				prev = null;
				element = null;
			}
		} // End of Node Class

		private Node<E> header;
		private int currentSize;

		public CircularDoublyLinkedQueque() {
			// first initializing header
			header = new Node<>(null);
			// then setting header as next and prev of header
			header.setNext(header);
			header.setPrev(header);
			currentSize = 0;
		}

		@Override
		public void addFirst(E elm) {
			Node<E> newNode = new Node<E>(elm);

			newNode.setNext(header.getNext());
			newNode.setPrev(header);
			header.getNext().setPrev(newNode);
			header.setNext(newNode);

			currentSize++;
		}

		@Override
		public void addLast(E elm) {
			Node<E> newNode = new Node<E>(elm);


			Node<E> last = header.getPrev();

			newNode.setNext(header);
			newNode.setPrev(last);
			last.setNext(newNode);
			header.setPrev(newNode);
			// updating size
			currentSize++;
		}

		@Override
		public E removeFirst() {
			if (isEmpty())
				return null;

			Node<E> remNode = header.getNext();

			remNode.getPrev().setNext(remNode.getNext());
			remNode.getNext().setPrev(remNode.getPrev());

			currentSize--;
			return remNode.getElement();
		}

		@Override
		public E removeLast() {	
			if (isEmpty())
				return null;

			Node<E> remNode = header.getPrev();

			remNode.getPrev().setNext(remNode.getNext());
			remNode.getNext().setPrev(remNode.getPrev());
			currentSize--;
			return remNode.getElement();
		}

		@Override
		public E getFirst() {
			
			if (isEmpty())
				return null;

			return header.getNext().getElement();
		}

		@Override
		public E getLast() {

			if (isEmpty())
				return null;

			return header.getPrev().getElement();
		}

		@Override
		public boolean removeFirstOccurrence(E elm) {

			Node<E> remNode = header.getNext();

			while (remNode != header) {
				if (remNode.getElement().equals(elm)) {
					remNode.getPrev().setNext(remNode.getNext());
					remNode.getNext().setPrev(remNode.getPrev());
					currentSize--;
					return true; 
				}

				remNode = remNode.getNext();
			}
			return false; 
		}

		@Override
		public boolean removeLastOccurrence(E elm) {
			Node<E> remNode = header.getPrev();
			
			while (remNode != header) {
				if (remNode.getElement().equals(elm)) {
					remNode.getPrev().setNext(remNode.getNext());
					remNode.getNext().setPrev(remNode.getPrev());
					currentSize--;
					return true;
				}
				remNode = remNode.getPrev();
			}
			return false;
		}

		@Override
		public int size() {
			return currentSize;
		}

		@Override
		public boolean isEmpty() {
			return size() == 0;
		}

		// DO NOT USE THIS IN EXERCISES
		// DO NOT DELETE, TESTS WILL FAILS
		@Override
		public String[] toArray() {
			String[] arr = new String[size()];

			Node<E> curNode = header.getNext();
			for (int i = 0; curNode != header; curNode = curNode
					.getNext(), i++) {
				arr[i] = (String) curNode.getElement();
			}
			return arr;
		}
	}
}