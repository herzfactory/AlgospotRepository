import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;


public class Fortress {

	public static class Node {
		private int x, y, r;
		private ArrayList<Node> childNode;
		public Node (int x, int y, int r) {
			this.x = x; this.y = y; this.r = r;
			childNode = new ArrayList<Node>();
		}
		public void setNode(Node child) {
			childNode.add(child);
		}
	}
	public static class Tree {
		private Node root;
		private int longest;
		public Tree() {}
		public void setRoot(Node node) {this.root = node;}
		public void setChild(Node node) {
			setChild(root, node);
		}
		private void setChild(Node a, Node b) {
			boolean isSibling = true;
			int size = a.childNode.size();
			if (size == 0)  { a.setNode(b); return; }
			for (int i = 0; i < size; i++) {
				Node childNode = a.childNode.get(i);
				if (isChild(childNode, b)) {setChild(childNode, b); isSibling = false; break;}
				if (isChild(b, childNode)) {b.setNode(childNode); a.childNode.remove(i); a.setNode(b); isSibling = false; break;}
			}
			if (isSibling) a.setNode(b);
		}
		public boolean isChild(Node a, Node b) {
			if (a.x == b.x && a.y == b.y) return a.r > b.r;
			else return a.r > b.r && Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) < Math.pow(a.r - b.r, 2);
		}
		
		public int getMaxLength() {
			longest = 0;
			int height = getLongestPath(root);
			return Math.max(longest, height);
		}

		private int getLongestPath(Node node) {
			ArrayList<Integer> heights = new ArrayList<Integer>();
			for (int i = 0; i < node.childNode.size(); i++) heights.add(getLongestPath(node.childNode.get(i)));
			if (heights.size() == 0) return 0;
			if (heights.size() >= 2) {
				Collections.sort(heights);
				longest = Math.max(longest, 2 + heights.get(heights.size() - 2) + heights.get(heights.size() - 1));
			}
			return heights.get(heights.size() - 1) + 1;
		}
	}

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());

			while (cases-- > 0) {
				int n = Integer.parseInt(reader.readLine());
				Tree tree = new Tree();
				for (int i = 0; i < n; i++) {
					String[] input = reader.readLine().split(" ");
					Node node = new Node(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]));
					if (i == 0) tree.setRoot(node);
					else tree.setChild(node);
				}
				writer.append(String.valueOf(tree.getMaxLength()));
				writer.newLine();
			}
			writer.flush();
			reader.close(); writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
