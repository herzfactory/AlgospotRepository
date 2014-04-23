#include <cstdio>
#include <vector>
#include <cmath>
#include <algorithm>
using namespace std;

class Node {
	private :
    int x, y, r;
    vector<Node*> childNode;
	public :
    Node(){};
    Node(int x, int y, int r) : x(x), y(y), r(r) {childNode.clear();}
    void setChildNode(Node* child) {childNode.push_back(child);}
    int getX(){return x;} int getY(){return y;} int getR() {return r;}
    int getChildNodeSize(){return (int)childNode.size();}
    bool isEmpty() {return childNode.empty();}
    Node* getChildNode(int index) {return childNode[index];}
    void removeChildNode(int index) {childNode.erase(childNode.begin() + index);}
};

class Tree {
	private :
    Node* root;
    int longest;
	public :
    void setRoot(Node* root) { this->root = root;};
    void setChild(Node* node) {setChild(root, node);}
    void setChild(Node* a, Node* b) {
        bool isSibling = true;
        int size = a->getChildNodeSize();
        if (a->isEmpty()) {a->setChildNode(b); return;}
        for (int i = 0; i < size; i++) {
            Node* node = a->getChildNode(i);
            if (isChild(node, b)) {
                setChild(node, b); isSibling = false; break;
            }
            if (isChild(b, node)) {
                b->setChildNode(node); a->removeChildNode(i); a->setChildNode(b); isSibling = false; break;
            }
        }
        if (isSibling) {a->setChildNode(b);}
    }
    bool isChild(Node* a, Node* b) {
        if (a->getX() == b->getX() && a->getY() == b->getY()) return a->getR() < b->getR();
        else return a->getR() > b->getR() && (int)pow(a->getX() - b->getX(), 2) + (int)pow(a->getY() - b->getY(), 2) < (int)pow(a->getR() - b->getR(), 2);
    }

    int getMaxLength() {
        longest = 0;
        int height = getLongestPath(root);
        return max(longest, height);
    }

    int getLongestPath(Node* node) {
        vector<int> heights;
        for (int i = 0; i < node->getChildNodeSize(); i++) heights.push_back(getLongestPath(node->getChildNode(i)));
        if (heights.size() == 0) return 0;
        if (heights.size() >= 2) {
            sort(heights.begin(), heights.end());
            longest = max(longest, 2 + heights[heights.size() - 2] + heights[heights.size() - 1]);
        }
        return heights.back() + 1;
    }

};

int main() {
	int cases; scanf("%d", &cases);
	while (cases--) {
		int n; scanf("%d", &n);
		Tree tree;
        vector<Node> nodes;
		for (int i = 0; i < n; i++) {
			int x, y, r; scanf("%d %d %d", &x, &y, &r); Node node(x, y, r); nodes.push_back(node);
		}
        for (int i = 0; i < n; i++) {
            if (i == 0) tree.setRoot(&nodes[i]);
            else tree.setChild(&nodes[i]);
        }
		printf("%d\n", tree.getMaxLength());
	}
}
