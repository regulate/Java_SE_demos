package org.baddev.learning.demos.se_basics.collections.binarytreedeleting39;

/**
 * Created by Potapchuk Ilya on 10.12.2015.
 */

//Task: add method responsible for deleting nodes
public class BinaryTree {

    private static class Node {
        int key;
        String value;
        Node leftChild;
        Node rightChild;

        Node(int key, String name) {
            this.key = key;
            this.value = name;
        }

        public String toString() {
            return "Key: " + key + ", value: " + value + ", left child: " + ((leftChild==null)?"null":leftChild.key)
                    + ", right child: " + ((rightChild==null)?"null":rightChild.key);
        }
    }

    private Node root;

    public void addNode(int key, String value) {
        Node newNode = new Node(key, value);
        if (root == null) {
            root = newNode;
        } else {
            // Begin iterating
            Node currentNode = root;
            Node parent;
            while (true) {
                parent = currentNode;
                // Checking keys:
                if (key < currentNode.key) {
                    currentNode = currentNode.leftChild;
                    if (currentNode == null) {
                        // Move node to needed place:
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    currentNode = currentNode.rightChild;
                    if (currentNode == null) {
                        // Move node to needed place:
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    // Iterating over nodes in natural keys order
    public void traverseTree(Node currentNode) {
        if (currentNode != null) {
            traverseTree(currentNode.leftChild);
            System.out.println(currentNode);
            traverseTree(currentNode.rightChild);
        }
    }

    public void traverseTree() {
        traverseTree(root);
    }

    // Searching node by key
    public Node findNode(int key) {
        Node focusNode = root;
        while (focusNode.key != key) {
            if (key < focusNode.key) {
                focusNode = focusNode.leftChild;
            } else {
                focusNode = focusNode.rightChild;
            }
            if (focusNode == null) {
                return null;
            }
        }
        return focusNode;
    }

    public boolean removeNode(int key) {
        return false;
    }

    public Node minValueNode(Node node) {
        if (node.leftChild == null)
            return node;
        else
            return minValueNode(node.leftChild);
    }


    public Node findParent(Node child) {
        Node current = root;
        Node parent = null;
        while (true) {
            if (child.key > current.key) {
                parent = current;
                current = current.rightChild;
            } else if (child.key < current.key) {
                parent = current;
                current = current.leftChild;
            } else {
                //when keys are equal
                break;
            }
        }
        return parent;
    }

    public static void main(String[] args) {
        BinaryTree continents = new BinaryTree();
        continents.addNode(1, "Europe");
        continents.addNode(3, "Africa");
        continents.addNode(5, "Australia");
        continents.addNode(4, "South America");
        continents.addNode(2, "Asia");
        continents.addNode(6, "Antarctica");
        continents.addNode(12, "North America");
        continents.addNode(8, "leftOfRight");
        continents.addNode(9, "rightOfLeft");
        continents.removeNode(1);
        continents.traverseTree();
    }

}
