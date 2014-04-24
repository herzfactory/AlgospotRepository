import java.util.Objects;
import java.util.Random;


public class Treep {
	public class Node {
		private int key, priority, size;
		private Node left, right;
		public Node(final int key) {
			this.key = key; priority = new Random().nextInt(); size = 1; left = null; right = null;
		}
		public int getKey() 				{ return key;}
		public int getPriority() 			{ return priority; }
		public int getSize() 				{ return size; }
		public Node getLeft() 				{ return left; }
		public Node getRight() 				{ return right; }
		public void setLeft(Node left) 		{ this.left = left; calcSize();}
		public void setRight(Node right) 	{ this.right = right; calcSize();}
		public void calcSize() {
			size = 1;
			if (left != null) size += left.getSize();
			if (right != null) size += right.getSize();
		}
	}

	public class Pair<F, S> {
		public final F first;
		public final S second;
		public Pair(F first, S second) {
			this.first = first;
			this.second = second;
		}
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Pair)) {
				return false;
			}
			Pair<?, ?> p = (Pair<?, ?>) o;
			return Objects.equals(p.first, first) && Objects.equals(p.second, second);
		}
		@Override
		public int hashCode() {
			return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
		}
	}


	public Pair<Node, Node> split(Node root, int key) {
		if (root == null) return new Pair<Node, Node>(null, null);
		if (root.getKey() < key) {
			Pair<Node, Node> rs = split(root.getRight(), key);
			root.setRight(rs.first);
			return new Pair<Node, Node>(root, rs.second);
		}
		Pair<Node, Node> ls = split(root.getLeft(), key);
		root.setLeft(ls.first);
		return new Pair<Node, Node>(ls.first, root);

	}

	public Node insert(Node root, Node node) {
		if (root == null) return node;
		if (root.getPriority() < node.getPriority()) {
			Pair<Node, Node> splitted = split(root, node.getKey());
			node.setLeft(splitted.first);
			node.setRight(splitted.second);
			return node;
		}
		else if (root.getKey() > node.getKey()) root.setLeft(insert(root.getLeft(), node));
		else root.setRight(insert(root.getRight(), node));
		return root;
	}
	
	public Node merge(Node a, Node b) {
		if (a == null) return b;
		if (b == null) return a;
		if (a.getPriority() < b.getPriority()) {
			b.setLeft(merge(a, b.getLeft()));
			return b;
		}
		a.setRight(merge(a.getRight(), b));
		return a;
	}
	
	public Node erase(Node root, int key) {
		if (root == null) return root;
		if (root.getKey() == key) {
			Node ret = merge(root.getLeft(), root.getRight());
			return ret;
		}
		if (root.getKey() > key) root.setLeft(erase(root.getLeft(), key));
		else root.setRight(erase(root.getRight(), key));
		return root;
	}
	
	public Node kth(Node root, int key) {
		int leftSize = 0;
		if (root.getLeft() != null) leftSize = root.getLeft().getSize();
		if (leftSize >= key) return kth(root.getLeft(), key); 
		if (leftSize + 1 == key) return root;
		return kth(root.getRight(), key - leftSize - 1);
	}
	
	public int countLessThan(Node root, int key) {
		if (root == null) return 0;
		if (root.getKey() >= key) return countLessThan(root.getLeft(), key);
		int ls = (root.getLeft() != null ? root.getLeft().getSize() : 0);
		return ls + 1 + countLessThan(root.getRight(), key);
	}
}
