import java.util.EmptyStackException;
import java.util.Iterator;


public class Lab07Stack1Wrapper {
	
	public static interface Stack<E> {
		   public void push(E newEntry);
		   public E pop();
		   public E top();
		   public boolean isEmpty();
		   public int size(); 
		   public void clear();
	}
	
	public static class SinglyLinkedStack<E> implements Stack<E> {
		
		private static class Node<E> {
			private E element; 
			private Node<E> next; 
			
			public Node(E elm, Node<E> next) { 
				this.element = elm; 
				this.next = next; 
			}
			
			public Node(E data) { 
				this(data, null);
			}
			
			public Node() { 
				this(null, null);
			}

			public E getElement() {
				return element;
			}

			public Node<E> getNext() {
				return next;
			}
			
			
			public void setElement(E elm) {
				this.element = elm;
			}

			public void setNext(Node<E> next) {
				this.next = next;
			}

			public void clear() {  // no need to return data
				element = null; 
				next = null; 
			}
			
		}

		// instance variables for the stack object
		
		private Node<E> header; //Note that this is NOT a dummy header 
		private int currentSize; 
		
		public SinglyLinkedStack() { 
			header = new Node<>(); 
			currentSize = 0; 
		}
		
		@Override
		public void push(E newEntry) {
			Node<E> nNode = new Node<>(newEntry, header.getNext()); 
			header.setNext(nNode); 
			currentSize++; 
		}

		@Override
		public E pop() {
			E etr = top(); 
			Node<E> ntr = header.getNext(); 
			header.setNext(ntr.getNext()); 
			currentSize--; 
			ntr.clear();
			ntr = null;
			return etr;
		}

		@Override
		public E top() {
			if (isEmpty()) 
				throw new EmptyStackException(); 
			return header.getNext().getElement();
		}

		@Override
		public boolean isEmpty() {
			return size() == 0;
		}

		@Override
		public int size() {
			return currentSize;
		}

		@Override
		public void clear() {
			while (size() > 0) pop(); 
		}

	}

	public static boolean isPalindrome(String s) {
		SinglyLinkedStack <Character> first = new SinglyLinkedStack<>();
		SinglyLinkedStack <Character> second = new SinglyLinkedStack<>();
		int middle = 0;
		boolean result = true;
		
		s = s.replaceAll("\\s+", "");
		
		if(s.length() % 2 == 0) {
			middle = s.length() / 2;
			
			for (int i = 0; i < middle; i++) {
				first.push(s.charAt(i));
			}
			
			for (int i = s.length() - 1; i >= middle; i--) {
				second.push(s.charAt(i));
			}
		} else {
			middle = (s.length() - 1) / 2;
			
			for (int i = 0; i < middle; i++) {
				first.push(s.charAt(i));
			}
			
			for (int i = s.length() - 1; i > middle; i--) {
				second.push(s.charAt(i));
			}
		}
		
		while(!first.isEmpty()) {
			if(first.top().equals(second.top())){
				first.pop();
				second.pop();
			} else {
				result = false;
						break;
			}
		}
		return result;
	}
}

