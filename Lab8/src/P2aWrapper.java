import java.util.ArrayList;
import java.util.List;



public class P2aWrapper {

	public static interface Position<E> {
		
		public E getElement();

	}
	public static interface Comparator<E> {
		int compare(E a, E b);

	}
	
	public static class DefaultComparator<K> implements Comparator<K> {

		@SuppressWarnings("unchecked")
		@Override
		public int compare(K a, K b) {
			return ((Comparable<K>) a).compareTo(b);
		}
		

	}
	
	public static interface Entry<K, V> {
		
		public K getKey();
		
		public V getValue();

	}

	public static interface BinarySearchTree<K, V> {
		/**Finds all entries in the BST that have the given key. 
		 * @param key The key to search for. 
		 * @return ArrayList<Entry<K, V>> containing all those entries (in increasing order of the key) 
		 * in the bst that have the given key. That array list will be empty if none. 
		 */
		List<Entry<K, V>> getAll(K key);  
		
		/**Finds any entry in the bst having the given key. 
		 * @param key the key to search for
		 * @return null if no such entry; otherwise, reference to one of those
		 * entries with the given key. 
		 */
		Entry<K, V> get(K key); 
		
		/**Adds to the BST the new pair key-value as an entry. 
		 * @param key the key of the new entry
		 * @param value the value of the new entry
		 */
		void add(K key, V value); 
		
		/**Removes any copy found of entry with given key in the tree, if any. 
		 * @param key the key to search from
		 * @return null if no such entry is found; otherwise, reference to 
		 * the entry being removed from the tree.
		 */
		Entry<K, V> remove(K key); 
		
		int size();
		
		boolean isEmpty();
		
		
		///// EXAM
		
		public int leafCount();
					
		public Iterable<Entry<K,V>> internals();

	}

	@SuppressWarnings("unused")
	private static class BTEntry<K,V> implements Entry<K,V>{
		
		private K key;
		private V value;

		public BTEntry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
		
		
		public void setKey(K key) {
			this.key = key;
		}

		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}
		

	}
	
	@SuppressWarnings("unused")
	private static class BTNode<K,V> {
		private Entry<K,V> value;
		private BTNode<K,V> parent;
		private BTNode<K,V> leftChild;
		private BTNode<K,V> rightChild;
		public BTNode(Entry<K, V> value, BTNode<K, V> parent, BTNode<K, V> leftChild, BTNode<K, V> rightChild) {
			super();
			this.value = value;
			this.parent = parent;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
		public Entry<K, V> getValue() {
			return value;
		}
		public void setValue(Entry<K, V> value) {
			this.value = value;
		}
		public BTNode<K, V> getParent() {
			return parent;
		}
		public void setParent(BTNode<K, V> parent) {
			this.parent = parent;
		}
		public BTNode<K, V> getLeftChild() {
			return leftChild;
		}
		public void setLeftChild(BTNode<K, V> leftChild) {
			this.leftChild = leftChild;
		}
		public BTNode<K, V> getRightChild() {
			return rightChild;
		}
		public void setRightChild(BTNode<K, V> rightChild) {
			this.rightChild = rightChild;
		}	
	}


	public static class LinkedBinarySearchTree<K, V> implements BinarySearchTree<K, V> {
		
		
		
		private BTNode<K,V> root ;
		private int size;
		private Comparator<K> comparator ;
		
		public LinkedBinarySearchTree() {
			super();
			this.root = null;
			this.size = 0;
			this.comparator = new DefaultComparator<K>();
			
		}

		public LinkedBinarySearchTree(Comparator<K> comp) {
			super();
			this.root = null;
			this.size = 0;
			this.comparator = comp;
			
		}

		@Override
		public List<Entry<K, V>> getAll(K key) {
			List<Entry<K,V>> result = new ArrayList<Entry<K, V>>();
			if (this.isEmpty()) {
				return result;
			}
			else {
				this.getAllAux(this.root, key, result);
				return result;
			}
		}

		
		private void getAllAux(BTNode<K, V> N, K key, List<Entry<K,V>> L) {
			if (N == null) {
				return;
			}
			else {
				int c = this.comparator.compare(key, N.getValue().getKey());
				if (c == 0) {
					L.add(N.getValue());
					this.getAllAux(N.leftChild, key, L);
					this.getAllAux(N.rightChild, key, L);

				}
				else if (c < 0) {
					this.getAllAux(N.getLeftChild(), key, L);
				}
				else {
					this.getAllAux(N.getRightChild(), key, L);
				}
			}

		}

		@Override
		public Entry<K, V> get(K key) {
			if (this.isEmpty()) {
				return null;
			}
			else {
				return this.getAux(this.root, key);
			}
		}

		
		private Entry<K, V> getAux(BTNode<K, V> N, K key) {
			if (N == null) {
				// not found
				return null;
			}
			else {
				int c = this.comparator.compare(key, N.getValue().getKey());
				if (c == 0) {
					return N.getValue();
				}
				else if (c < 0) {
					return this.getAux(N.getLeftChild(), key);
				}
				else {
					return this.getAux(N.getRightChild(), key);
				}
			}
		}

		@Override
		public void add(K key, V value) {
			if (this.isEmpty()) {
				this.root = new BTNode<K,V>(new BTEntry<K, V>(key, value), null, null, null);
				this.size++;
			}
			else {
				this.addAux(this.root, key, value);
			}

		}
		private void addAux(BTNode<K, V> N, K key, V value) {
			// Assumes N is not null
			int c = this.comparator.compare(key, N.getValue().getKey());
			if (c == 0) {
				if (Math.random() > 0.5) {
					
					if (N.getRightChild() != null) {
						this.addAux(N.getRightChild(), key, value);

					}
					else {
						BTNode<K,V> newNode = 
								new BTNode<K,V>(new BTEntry<K, V>(key, value), null, null, null);
						N.setRightChild(newNode);
						newNode.setParent(N);
						this.size++;
						return;
					}
				}
				else {
					if (N.getLeftChild() != null) {
						this.addAux(N.getLeftChild(), key, value);
					}
					else {
						BTNode<K,V> newNode = 
								new BTNode<K,V>(new BTEntry<K, V>(key, value), null, null, null);
						N.setLeftChild(newNode);
						newNode.setParent(N);
						this.size++;
						return;
					}
				}
			}
			else if (c < 0) {
				// insert on left child
				if (N.getLeftChild() != null) {
					this.addAux(N.getLeftChild(), key, value);
				}
				else {
					BTNode<K,V> newNode = 
							new BTNode<K,V>(new BTEntry<K, V>(key, value), null, null, null);
					N.setLeftChild(newNode);
					newNode.setParent(N);
					this.size++;
					return;
				}
			}
			else {
				if (N.getRightChild() != null) {
					this.addAux(N.getRightChild(), key, value);

				}
				else {
					BTNode<K,V> newNode = 
							new BTNode<K,V>(new BTEntry<K, V>(key, value), null, null, null);
					N.setRightChild(newNode);
					newNode.setParent(N);
					this.size++;
					return;
				}
			}
			
		}
		@Override
		public Entry<K, V> remove(K key) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int size() {
			return this.size;
		}

		@Override
		public boolean isEmpty() {
			return this.size() == 0;
		}

		@Override
		public int leafCount() {
		if(this.isEmpty()) return 0;
		if(root != null && root.getLeftChild() == null && root.getRightChild() == null) 
			return 1;
			return leafCountAux(this.root);
			
		}
		
		public int leafCountAux(BTNode<K, V> N) {
			int count = 0;
			if(N == null) return 0;
			
			if(N.getLeftChild() == null && N.getRightChild() == null)
				count++;
			
			return count + leafCountAux(N.getLeftChild()) + leafCountAux(N.getRightChild());
		}

		@Override
		public Iterable<Entry<K, V>> internals() {
			List<Entry<K, V>> L = new ArrayList<Entry<K, V>>(this.size());
			internalsAux(L, this.root);
			
			return L;
		}
		public void internalsAux(List<Entry<K, V>> L,BTNode<K, V> N) {
			if(N == null) return;
			if(N.getLeftChild() != null || N.getRightChild() != null)
				L.add(N.getValue());
			
			internalsAux(L, N.getLeftChild());
			internalsAux(L, N.getRightChild());
		}
	}
}


