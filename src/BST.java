import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @param <K> The type of the keys of this BST. They need to be comparable by nature of the BST
 * "K extends Comparable" means that BST will only compile with classes that implement Comparable
 * interface. This is because our BST sorts entries by key. Therefore keys must be comparable.
 * @param <V> The type of the values of this BST. 
 */
public class BST<K extends Comparable<? super K>, V> implements DefaultMap<K, V> {
	/* 
	 * TODO: Add instance variables 
	 * You may add any instance variables you need, but 
	 * you may NOT use any class that implements java.util.SortedMap
	 * or any other implementation of a binary search tree
	 */
	private Node<K, V>  root; 
	private int size;

	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null || value == null){
			throw new IllegalArgumentException("key/value cant be null");
		}

		if(containsKey(key)){
			return false;
		}
		root = putRecursive(root, key, value);
		size ++;

		return true;
	}

	private Node<K, V> putRecursive(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = putRecursive(node.left, key, value);
        } else if (compare > 0) {
            node.right = putRecursive(node.right, key, value);
        }
        return node;
    }


	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null || newValue == null) {
            throw new IllegalArgumentException("key/value can't be null");
        }
        Node<K, V> node = getNode(root, key);
        if (node != null) {
            node.setValue(newValue);
            return true;
        }
		return false;
	}

	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null){
			throw new IllegalArgumentException("Key can't be null");
		}
		if(!containsKey(key)){
			return false; 
		}
		root = removeRecursive(root, key);
		size --;
		return true;
	}

	private Node<K, V> removeRecursive(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = removeRecursive(node.left, key);
        } else if (compare > 0) {
            node.right = removeRecursive(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            Node<K, V> minNode = getMin(node.right);
            node.key = minNode.key;
            node.value = minNode.value;
            node.right = removeRecursive(node.right, minNode.key);
        }
        return node;
    }

	private Node<K, V> getMin(Node<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		put(key, value);
	}

	

	@Override
	public V get(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null){
			throw new IllegalArgumentException("Key can't be null");
		}
		Node<K,V> node = getNode(root, key);
		return node == null ? null : node.value;
	}


	private Node<K, V> getNode(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return getNode(node.left, key);
        } else if (compare > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }



	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null){
			throw new IllegalArgumentException("Key cannot be null");
		}
		return getNode(root, key) != null;
	}

	// Keys must be in ascending sorted order
	// You CANNOT use Collections.sort() or any other sorting implementations
	// You must do inorder traversal of the tree
	@Override
	public List<K> keys() {
		// TODO Auto-generated method stub
		List<K> keys = new ArrayList<>();
    Stack<Node<K, V>> stack = new Stack<>();
    Node<K, V> current = root;

    while (current != null || !stack.isEmpty()) {
        // Reach the leftmost node of the current node
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        // Current must be null at this point
        current = stack.pop();
        keys.add(current.key);

        // Visit the right subtree
        current = current.right;
    }
    
    return keys;
	
	}
	
	private static class Node<K extends Comparable<? super K>, V> 
								implements DefaultMap.Entry<K, V> {
		/* 
		 * TODO: Add instance variables
		 */
		K key;
		V value; 
		Node<K, V> left;
		Node<K, V> right; 

		Node (K key, V value){
			this.key = key; 
			this.value = value; 
			this.left = null;
			this.right = null;
		}

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public void setValue(V value) {
			// TODO Auto-generated method stub
			this.value = value;
		}
		
		
	}
	 
}