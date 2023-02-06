import java.util.Comparator;

public class Quiz2cWrapper {
	
	public static interface Set<E> {
	    public int size();
	    
	    public boolean isEmpty();
	    
	    public void add(E e);
	    
	    public boolean isMember(E e);
	    
	    public boolean remove(E e);
	            
	    public void clear();
	    
	    public boolean isSubset(Set<E> S);
	    
	    public Set<E> union(Set<E> S);
	    
	    public Set<E> difference(Set<E> S);

	    public Set<E> intersection(Set<E> S);

	    public Object[] toArray();
	    
	    public Set<E> rangeIn(E e1, E e2, Comparator<E> C);
	    
	    
	}

	@SuppressWarnings("unchecked")
	public static class ArraySet<E> implements Set<E> {
		private E[] elements;
		private int currentSize;
		private static final int DEFAULT_SIZE = 10;


		public ArraySet(int initialSize) {
			if (initialSize < 1) {
				throw new IllegalArgumentException("Size must be at least 1.");
			}
			this.elements = (E[]) new Object[initialSize];
			this.currentSize = 0;
		}

		public ArraySet() {
			this(DEFAULT_SIZE);
		}

		@Override
		public int size() {
			return this.currentSize;
		}

		@Override
		public boolean isEmpty() {
			return this.size() == 0;
		}

		@Override
		public void add(E e) {
			if (!this.isMember(e)) {
				if (this.size() == this.elements.length) {
					reAllocate();
				}
				this.elements[this.currentSize++] = e;
			}

		}

		private void reAllocate() {
	        E temp[] = (E[]) new Object[2*this.size()];
	        for (int i=0; i < this.size(); ++i) {
	                temp[i] = this.elements[i];
	        }
	        this.elements = temp;
			
		}

		@Override
		public boolean isMember(E e) {
			for (int i=0; i < this.size(); ++i) {
				if (this.elements[i].equals(e)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean remove(E e) {
			for (int i=0; i < this.size(); ++i) {
				if (this.elements[i].equals(e)) {
					this.elements[i] = this.elements[this.currentSize-1];
					this.elements[this.currentSize-1] = null;
					this.currentSize--;
					return true;
				}
			}
			return false;

		}

		@Override
		public void clear() {
	        for (int i=0; i < this.size(); ++i) {
	            this.elements[i] = null;
	        }
	        this.currentSize = 0; 
		}

		@Override
		public boolean isSubset(Set<E> S) {
			for (int i=0; i < this.size(); ++i) {
				if (!S.isMember(this.elements[i])) {
					return false;
				}
			}
			return true;
		}

		@Override
		public Set<E> union(Set<E> S) {

			Set<E> result = new ArraySet<E>(this.size() + S.size());
			for (int i=0; i < this.size(); ++i) {
				result.add(this.elements[i]);
			}
			Object[] temp = S.toArray();
			for (int i=0; i < S.size(); ++i) {
				if (!result.isMember((E) temp[i])) {
					result.add((E)temp[i]);
				}
			}
			return result;
		}

		@Override
		public Set<E> difference(Set<E> S) {

			Set<E> result = new ArraySet<E>(this.size());
			for (int i=0; i < this.size(); ++i) {
				if (!S.isMember(this.elements[i])) {
					result.add(this.elements[i]);
				}
			}
			return result;
			

		}

		@Override
		public Set<E> intersection(Set<E> S) {
			return this.difference(this.difference(S));
		}

		@Override
		public Object[] toArray() {
			Object result[] = new Object[this.size()];
			
			for (int i=0; i < this.size(); ++i) {
				result[i]  = this.elements[i];
			}
			
			return result;

		}

		@Override
		public Set<E> rangeIn(E e1, E e2, Comparator<E> C) {
			Set<E> result = new ArraySet<E>();
			for(int i = 0; i < size(); i++) {
				if(C.compare(elements[i], e1) >= 0 && C.compare(elements[i], e2) <= 0)
					result.add(elements[i]);
			}
			return result;
		}
	}
}
