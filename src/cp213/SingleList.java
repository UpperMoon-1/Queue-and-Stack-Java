package cp213;

/**
 * A single linked list structure of <code>Node T</code> objects. These data
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> object contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author David Brown
 * @version 2024-09-01
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

	/**
	 * Searches for the first occurrence of key in this SingleList. Private helper
	 * methods - used only by other ADT methods.
	 *
	 * @param key The object to look for.
	 * @return A pointer to the node previous to the node containing key.
	 */
	private SingleNode<T> linearSearch(final T key) {

		// your code here
		SingleNode<T> current = this.front;
		SingleNode<T> previous = null;
		while ((current != null) && (!current.getObject().equals(key))) {
			previous = current;
			current = current.getNext();

		}

		return (current != null) ? previous : null;
	}

	/**
	 * Appends data to the end of this SingleList.
	 *
	 * @param data The object to append.
	 */
	public void append(final T data) {

		// your code here
		SingleNode<T> node = new SingleNode<>(data, null);
		if (this.rear == null) {
			this.front = node;
			this.rear = node;
		} else {
			this.rear.setNext(node);
			this.rear = node;
		}
		this.length++;

		return;
	}

	/**
	 * Removes duplicates from this SingleList. The list contains one and only one
	 * of each object formerly present in this SingleList. The first occurrence of
	 * each object is preserved.
	 */
	public void clean() {

		// your code here
		if (this.front != null) {
			SingleNode<T> current = this.front;

			while (current != null) {

				SingleNode<T> cn = current.getNext();
				SingleNode<T> pre = current;
				while (cn != null) {
					if (current.getObject().equals(cn.getObject())) {
						pre.setNext(cn.getNext());
						this.length--;
					} else {
						pre = cn;
					}

					cn = cn.getNext();
				}
				current = current.getNext();

			}
		}
		return;
	}

	/**
	 * Combines contents of two lists into a third. Values are alternated from the
	 * origin lists into this SingleList. The origin lists are empty when finished.
	 * NOTE: data must not be moved, only nodes.
	 *
	 * @param left  The first list to combine with this SingleList.
	 * @param right The second list to combine with this SingleList.
	 */
	public void combine(final SingleList<T> left, final SingleList<T> right) {

		// your code here

		while (!left.isEmpty() || !right.isEmpty()) {

			if (!left.isEmpty()) {
				this.moveFrontToRear(left);
			}
			if (!right.isEmpty()) {
				this.moveFrontToRear(right);
			}
		}

		return;
	}

	/**
	 * Determines if this SingleList contains key.
	 *
	 * @param key The key object to look for.
	 * @return true if key is in this SingleList, false otherwise.
	 */
	public boolean contains(final T key) {

		// your code here
		SingleNode<T> node = this.front;
		while (node != null) {
			if (node.getObject().equals(key)) {
				return true;
			}
			node = node.getNext();
		}

		return false;
	}

	/**
	 * Finds the number of times key appears in list.
	 *
	 * @param key The object to look for.
	 * @return The number of times key appears in this SingleList.
	 */
	public int count(final T key) {

		// your code here
		int count = 0;
		SingleNode<T> current = this.front;
		while (current != null) {
			if (current.getObject() == key) {
				count++;
			}
			current = current.getNext();
		}

		return count;
	}

	/**
	 * Finds and returns the object in list that matches key.
	 *
	 * @param key The object to search for.
	 * @return The object that matches key, null otherwise.
	 */
	public T find(final T key) {

		// your code here
		SingleNode<T> node = this.linearSearch(key);
		if (node != null) {
			return node.getNext().getObject();
		}

		return null;
	}

	/**
	 * Get the nth object in this SingleList.
	 *
	 * @param n The index of the object to return.
	 * @return The nth object in this SingleList.
	 * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
	 */
	public T get(final int n) throws ArrayIndexOutOfBoundsException {

		// your code here
		if (n >= this.length || n < 0) {
			throw new ArrayIndexOutOfBoundsException("out");
		} else {
			SingleNode<T> node = this.front;
			int count = 0;
			while (count < n) {
				node = node.getNext();
				count += 1;
			}
			if (node != null) {
				return node.getObject();
			}
		}

		return null;
	}

	/**
	 * Determines whether two lists are identical.
	 *
	 * @param source The list to compare against this SingleList.
	 * @return true if this SingleList contains the same objects in the same order
	 *         as source, false otherwise.
	 */
	public boolean equals(final SingleList<T> source) {

		// your code here
		if (this.length == source.length) {
			SingleNode<T> current = this.front;
			SingleNode<T> soc = source.front;
			boolean boo = true;
			while (current != null && current.getObject() == soc.getObject()) {
				current = current.getNext();
				soc = soc.getNext();
			}
			if (current == null) {
				return boo;
			}
		}

		return false;
	}

	/**
	 * Finds the first location of a object by key in this SingleList.
	 *
	 * @param key The object to search for.
	 * @return The index of key in this SingleList, -1 otherwise.
	 */
	public int index(final T key) {

		// your code here
		SingleNode<T> current = this.front;
		int count = 0;
		while (current != null) {
			if (current.getObject().equals(key)) {
				return count;
			}
			count++;
			current = current.getNext();

		}

		return -1;
	}

	/**
	 * Inserts object into this SingleList at index i. If i greater than the length
	 * of this SingleList, append data to the end of this SingleList.
	 *
	 * @param i    The index to insert the new data at.
	 * @param data The new object to insert into this SingleList.
	 */
	public void insert(int i, final T data) {

		// your code here
		SingleNode<T> node = new SingleNode<>(data, null);
		if (this.front == null) {
			this.front = node;
			this.rear = node;
		} else if (i > this.length) {
			this.rear.setNext(node);
			this.rear = node;
		} else if (i == 0) {
			node.setNext(this.front);
			this.front = node;
		} else {
			int count = 0;
			SingleNode<T> current = this.front;
			SingleNode<T> previous = null;
			while (count != i) {
				count++;
				previous = current;
				current = current.getNext();

			}
			node.setNext(current);
			previous.setNext(node);
		}
		this.length++;

		return;
	}

	/**
	 * Creates an intersection of two other SingleLists into this SingleList. Copies
	 * data to this SingleList. left and right SingleLists are unchanged. Values
	 * from left are copied in order first, then objects from right are copied in
	 * order.
	 *
	 * @param left  The first SingleList to create an intersection from.
	 * @param right The second SingleList to create an intersection from.
	 */
	public void intersection(final SingleList<T> left, final SingleList<T> right) {

		// your code here
		SingleNode<T> current = left.front;
		while (current != null) {
			if (right.contains(current.getObject()) && !this.contains(current.getObject())) {
				this.append(current.getObject());
			}
			current = current.getNext();
		}

		return;
	}

	/**
	 * Finds the maximum object in this SingleList.
	 *
	 * @return The maximum object.
	 */
	public T max() {

		// your code here
		T m = this.front.getObject();
		SingleNode<T> current = this.front;
		while (current != null) {

			if (m.compareTo(current.getObject()) < 0) {
				m = current.getObject();
			}
			current = current.getNext();
		}

		return m;
	}

	/**
	 * Finds the minimum object in this SingleList.
	 *
	 * @return The minimum object.
	 */
	public T min() {

		// your code here
		T m = this.front.getObject();
		SingleNode<T> current = this.front;
		while (current != null) {

			if (m.compareTo(current.getObject()) > 0) {
				m = current.getObject();
			}
			current = current.getNext();
		}

		return m;
	}

	/**
	 * Inserts object into the front of this SingleList.
	 *
	 * @param data The object to insert into the front of this SingleList.
	 */
	public void prepend(final T data) {

		// your code here
		SingleNode<T> node = new SingleNode<>(data, null);
		if (this.front == null) {
			this.front = node;
			this.rear = node;
		} else {
			node.setNext(this.front);
			this.front = node;
		}
		this.length++;

		return;
	}

	/**
	 * Finds, removes, and returns the object in this SingleList that matches key.
	 *
	 * @param key The object to search for.
	 * @return The object matching key, null otherwise.
	 */
	public T remove(final T key) {

		// your code here
		SingleNode<T> previous = null;
		SingleNode<T> current = this.front;
		if (this.front == null) {
			return null;
		}
		T re = null;
		while (current != null && current.getObject() != key) {
			previous = current;
			current = current.getNext();
		}
		if (current != null) {
			previous.setNext(current.getNext());
			current.setNext(null);
			re = current.getObject();
		}

		return re;
	}

	/**
	 * Removes the object at the front of this SingleList.
	 *
	 * @return The object at the front of this SingleList.
	 */
	public T removeFront() {

		// your code here
		if (this.front == null) {
			return null;
		}
		T re = this.front.getObject();
		this.front = this.front.getNext();

		return re;
	}

	/**
	 * Finds and removes all objects in this SingleList that match key.
	 *
	 * @param key The object to search for.
	 */
	public void removeMany(final T key) {

		// your code here
		SingleNode<T> current = this.front;
		SingleNode<T> previous = null;
		while (current != null) {
			if (current.getObject().equals(key)) {
				if (previous == null) {
					this.front = current.getNext();
				} else {
					previous.setNext(current.getNext());

				}
				current = current.getNext();
				this.length--;
			} else {
				previous = current;
				current = current.getNext();
			}
		}

		return;
	}

	/**
	 * Reverses the order of the objects in this SingleList.
	 */
	public void reverse() {

		// your code here
		SingleNode<T> current = this.front;
		SingleNode<T> previous = null;
		SingleNode<T> next = null;
		while (current != null) {
			next = current.getNext();
			current.setNext(previous);
			previous = current;
			current = next;
		}
		this.front = previous;

		return;
	}

	/**
	 * Splits the contents of this SingleList into the left and right SingleLists.
	 * Moves nodes only - does not move object or call the high-level methods insert
	 * or remove. this SingleList is empty when done. The first half of this
	 * SingleList is moved to left, and the last half of this SingleList is moved to
	 * right. If the resulting lengths are not the same, left should have one more
	 * object than right. Order is preserved.
	 *
	 * @param left  The first SingleList to move nodes to.
	 * @param right The second SingleList to move nodes to.
	 */
	public void split(final SingleList<T> left, final SingleList<T> right) {

		// your code here
		int n = (this.length + 1) / 2;
		// int m = this.length - n;
		for (int i = 0; i < n; i++) {
			left.moveFrontToRear(this);
		}
		if (!this.isEmpty()) {
			while (!this.isEmpty()) {
				right.moveFrontToRear(this);
			}
		}

		return;
	}

	/**
	 * Splits the contents of this SingleList into the left and right SingleLists.
	 * Moves nodes only - does not move object or call the high-level methods insert
	 * or remove. this SingleList is empty when done. Nodes are moved alternately
	 * from this SingleList to left and right. Order is preserved.
	 *
	 * @param left  The first SingleList to move nodes to.
	 * @param right The second SingleList to move nodes to.
	 */
	public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {

		// your code here
		boolean boo = true;
		while (!this.isEmpty()) {
			if (boo) {
				left.moveFrontToRear(this);
			} else {
				right.moveFrontToRear(this);
			}
			boo = !boo;
		}

		return;
	}

	/**
	 * Creates a union of two other SingleLists into this SingleList. Copies object
	 * to this list. left and right SingleLists are unchanged. Values from left are
	 * copied in order first, then objects from right are copied in order.
	 *
	 * @param left  The first SingleList to create a union from.
	 * @param right The second SingleList to create a union from.
	 */
	public void union(final SingleList<T> left, final SingleList<T> right) {

		// your code here
		if (left.front != null) {
			this.append(left);
		}
		if (right.front != null) {
			this.append(right);
		}

		return;
	}
}
