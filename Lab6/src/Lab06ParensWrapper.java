import java.util.EmptyStackException;
import java.util.Scanner;

public class Lab06ParensWrapper {

	public static interface Stack<E> {

		public int size();
		public boolean isEmpty();
		public void clear();
		public void push(E e);
		public E pop();
		public E top(); //A.K.A. peek()

	}


	public static class LinkedStack<E> implements Stack<E>{

		@SuppressWarnings("hiding")
		private class Node<E> {

			private E element;
			private Node<E> next;

			public Node(E elm, Node<E> next) {
				this.element = elm;
				this.next = next;
			}


			public Node(E elm) {
				this(elm, null);
			}

			public Node() {
				this(null, null);
			}

			public void clear() {
				this.element = null;
				this.next = null;
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
		}

		private int currentSize;
		private Node<E> header;

		public LinkedStack() {
			this.currentSize =0;
			this.header = new Node<>();
		}

		@Override
		public int size() {
			return currentSize;
		}

		@Override
		public boolean isEmpty() {
			return size() == 0;
		}

		@Override
		public void clear() {
			while(!isEmpty()) pop();
		}


		/**  
		 * Note: For operations to be O(1), 
		 * header will always point to the top of the stack
		 * 
		 * Example:
		 * header -> Jim -> Bob -> Ned -> Jil -> Kim -> null
		 * 
		 * Example:
		 * newNode = Jim
		 * header -> Jim -> null
		 * 
		 * Bob <- Top of stack
		 * Ned
		 * Jil
		 * Kim 
		 */
		@Override
		public void push(E e) {
			//Equivalent to SLL.add(e, 0)
			Node<E> newNode;
			if(isEmpty())
				newNode = new Node<>(e);
			else
				newNode = new Node<>(e, header.getNext());

			header.setNext(newNode);
			currentSize++;
		}

		@Override
		public E pop() {
			//Equivalent to SLL.remove(0)
			if(isEmpty())
				throw new EmptyStackException();

			Node<E> curNode = header;
			Node<E> rmNode = curNode.getNext();
			E value = rmNode.getElement();

			curNode.setNext(rmNode.getNext());
			rmNode.clear();
			rmNode = null;
			currentSize--;

			return value;
		}

		@Override
		public E top() {
			//Equivalent to SLL.get(0)
			if(isEmpty()) 
				throw new EmptyStackException();
			return header.getNext().getElement();
		}

	}

	/**
	 * You can implement any auxiliary function, as well as declare any static 
	 * fields inside this wrapper class to help you out.
	 * For ease of use declare the static fields here, above this method
	 */
	public static void fullyParens() {
		LinkedStack <Character> parenthesis = new LinkedStack<>();
		LinkedStack <Character> operators= new LinkedStack<>();
		LinkedStack <Integer> operands = new LinkedStack<>();
		char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		Integer[] variableValues = new Integer[alphabet.length];

		Scanner scan = new Scanner(System.in);

		String line = scan.nextLine();
		String lineMod = line;
		lineMod = line.replaceAll("\\s+", "");
		int x = 0;
		char[] chLine = line.toCharArray();
		while(line != "") {
			if(line.contains("=")){
				for (int j = 0; j < chLine.length; j++) {
					if(chLine[0] == alphabet[j]) {
						variableValues[j] = Integer.valueOf(line.substring(line.indexOf("=") + 1));
					}
				}
			}
			if(line.contains(":")) {
				for (int i = 0; i < chLine.length; i++) {
					lineMod = lineMod.substring(line.indexOf(":" + 1));
					if(chLine[i] == '(' || chLine[i] == '{' || chLine[i] == '[') {
						parenthesis.push(chLine[i]);
					}else if(chLine[i] == '/' || chLine[i] == '*' || chLine[i] == '+' || chLine[i] == '-') {
						operators.push(chLine[i]);
					} else if(chLine[i] == ')' || chLine[i] == '}' || chLine[i] == ']'){
						if(chLine[i] < 4) {
							System.out.println(line + " is invalid");
							if(parenthesis.top() == '(' && chLine[i] != ')' 
									|| parenthesis.top() == '{' && chLine[i] != '}' || parenthesis.top() == '[' && chLine[i] != ']'
									|| parenthesis.isEmpty() && chLine[i] == '}' || parenthesis.isEmpty() && chLine[i] == ']' 
									|| parenthesis.isEmpty() && chLine[i] == ')') {	
								System.out.println(line + " is invalid");
							}
							//me falta la condicion de cuando falten parentesis por cerrar
							int result = 0;
							int a = operands.top();
							operands.pop();
							int b = operands.top();
							operands.pop();

							if(operators.top().equals('+'))
								result = a + b;
							if(operators.top().equals('-'))
								result = a - b;
							if(operators.top().equals('*'))
								result = a * b;
							if(operators.top().equals('/'))
								result = a / b;

							parenthesis.pop();
							operators.pop();

						}
					}else {
						for (int j = 0; j < alphabet.length; j++) {
							if(alphabet[i] == chLine[i]) {
								operands.push(variableValues[i]);
							}
						}
					}
				}
			}
		}
	}

	/**********************************
	 * ADD YOUR AUXILIARY METHODS HERE*
	 **********************************/
}