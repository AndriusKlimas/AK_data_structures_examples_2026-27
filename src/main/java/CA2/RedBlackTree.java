package CA2;

/**
 * A red-black binary search tree implementation.
 *
 * - The root is always black.
 * - Red nodes cannot have red children.
 * - Every root-to-NIL path contains the same number of black nodes.
 * - Every missing child is represented by the shared NIL sentinel.
 */
public class RedBlackTree {

    private enum Color {
        RED,
        BLACK
    }

    private static class Node {
        int key;
        Color color;
        Node parent;
        Node left;
        Node right;

        Node(int key, Color color) {
            this.key = key;
            this.color = color;
            this.parent = null;
            this.left = null;
            this.right = null;
        }
    }

    // NIL represents every empty left/right child.
    // It is one shared node that is always black.
    private final Node nil = new Node(0, Color.BLACK);

    private Node root = nil;

    public RedBlackTree() {
        nil.parent = nil;
        nil.left = nil;
        nil.right = nil;
    }

    // ==========================================================
    //                       INSERTION
    // ==========================================================

    /**
     * Inserts a key using the normal binary-search-tree rules.
     *
     * The new node is always red because adding a red node usually
     * changes the black height less than adding a black node.
     *
     * fixInsert() is then called to restore the red-black properties.
     */
    public void insert(int key) {
        Node parent = nil;
        Node current = root;

        // Search for the position where the new node belongs.
        while (current != nil) {
            parent = current;

            if (key == current.key) {
                // Duplicate keys are ignored.
                return;
            }

            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // Create the new node, initially colored red.
        Node newNode = new Node(key, Color.RED);
        newNode.parent = parent;
        newNode.left = nil;
        newNode.right = nil;

        // Attach the new node to the correct side of its parent.
        if (parent == nil) {
            // The new node is the first node in the tree.
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        // Repair the tree after insertion.
        fixInsert(newNode);
    }

    /**
     * Repairs red-parent/red-child violations after insertion.
     *
     * The loop continues upward because recoloring the grandparent
     * can create another violation farther up the tree.
     */
    private void fixInsert(Node node) {
        // A red node is only illegal while its parent is red.
        while (node.parent.color == Color.RED) {
            Node parent = node.parent;
            Node grandparent = parent.parent;

            // Parent is the left child of the grandparent.
            if (parent == grandparent.left) {
                Node uncle = grandparent.right;

                // CASE 1: the uncle is red.
                // Recoloring can solve the problem.
                if (uncle.color == Color.RED) {
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    grandparent.color = Color.RED;

                    // Move upward and repair the grandparent.
                    node = grandparent;
                } else {
                    // CASE 2: the uncle is black and the node is
                    // the right child of its parent.
                    //
                    // This is a zigzag, so it must become a straight line.
                    if (node == parent.right) {
                        node = parent;
                        rotateLeft(node);
                    }

                    // CASE 3: uncle is black and the node and parent
                    // form a straight left-left line.
                    //
                    // Recolor and rotate right.
                    Node straightParent = node.parent;
                    Node straightGrandparent = straightParent.parent;

                    straightParent.color = Color.BLACK;
                    straightGrandparent.color = Color.RED;
                    rotateRight(straightGrandparent);
                }

            } else {
                // Parent is the right child of the grandparent.
                // These are the mirror images of the left-side cases.
                Node uncle = grandparent.left;

                // CASE 1 MIRROR: the uncle is red.
                if (uncle.color == Color.RED) {
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    grandparent.color = Color.RED;

                    // Continue checking farther up the tree.
                    node = grandparent;
                } else {
                    // CASE 2 MIRROR: the node is the left child.
                    // Convert the zigzag into a straight line.
                    if (node == parent.left) {
                        node = parent;
                        rotateRight(node);
                    }

                    // CASE 3 MIRROR: recolor and rotate left.
                    Node straightParent = node.parent;
                    Node straightGrandparent = straightParent.parent;

                    straightParent.color = Color.BLACK;
                    straightGrandparent.color = Color.RED;
                    rotateLeft(straightGrandparent);
                }
            }
        }

        // The loop may have recolored the root red.
        // The root must always be black.
        root.color = Color.BLACK;
    }

    // ==========================================================
    //                        DELETION
    // ==========================================================

    /**
     * Deletes a key using the normal binary-search-tree rules.
     *
     * If the physically removed node was black, fixDelete() is called
     * because removing it can reduce the black height of a path.
     */
    public void delete(int key) {
        Node nodeToDelete = find(key);

        if (nodeToDelete == nil) {
            return;
        }

        Node successor = null;
        Node physicallyRemovedNode = nodeToDelete;
        Node affectedChild;
        Color removedColor = nodeToDelete.color;

        // CASE 1: nodeToDelete has no left child.
        if (nodeToDelete.left == nil) {
            affectedChild = nodeToDelete.right;
            transplant(nodeToDelete, affectedChild);

            // CASE 2: nodeToDelete has no right child.
        } else if (nodeToDelete.right == nil) {
            affectedChild = nodeToDelete.left;
            transplant(nodeToDelete, affectedChild);

            // CASE 3: nodeToDelete has two children.
        } else {
            // Find the next-largest or in-order-successor node.
            successor = minimum(nodeToDelete.right);

            // Remember the successor's original color.
            removedColor = successor.color;
            affectedChild = successor.right;

            if (successor.parent == nodeToDelete) {
                // Successor is nodeToDelete's direct right child.
                affectedChild.parent = successor;
            } else {
                // Successor is farther down the right subtree.

                // Remove the successor and put its right child in its place.
                transplant(successor, successor.right);

                // Attach nodeToDelete's complete right subtree to successor.
                successor.right = nodeToDelete.right;
                successor.right.parent = successor;
            }

            // Put successor where nodeToDelete was.
            transplant(nodeToDelete, successor);

            // Successor takes nodeToDelete's left subtree.
            successor.left = nodeToDelete.left;
            successor.left.parent = successor;

            // The tree position keeps nodeToDelete's original color.
            successor.color = nodeToDelete.color;
        }

        // Repair only if removing the node/successor lost black height.
        if (removedColor == Color.BLACK) {
            fixDelete(affectedChild);
        }
    }

    /**
     * Repairs a black-height loss after deletion.
     *
     * node is the replacement position that is conceptually
     * double-black. It is repaired using the node's sibling.
     */
    private void fixDelete(Node node) {
        // Continue until node is the root or it is no longer black.
        while (node != root && node.color == Color.BLACK) {
            // The node is a left child.
            if (node == node.parent.left) {
                Node sibling = node.parent.right;

                // CASE 1: the sibling is red.
                //
                // Rotate and recolor to obtain a black sibling.
                if (sibling.color == Color.RED) {
                    sibling.color = Color.BLACK;
                    node.parent.color = Color.RED;
                    rotateLeft(node.parent);

                    // Recalculate the sibling after the rotation.
                    sibling = node.parent.right;
                }

                // CASE 2: the sibling and both of its children are black.
                //
                // Recolor the sibling and move the problem upward.
                if (sibling.left.color == Color.BLACK
                        && sibling.right.color == Color.BLACK) {
                    sibling.color = Color.RED;
                    node = node.parent;
                } else {
                    // CASE 3: the sibling is black, its near child is red,
                    // and its far child is black.
                    //
                    // Rotate at the sibling to make the red child an outer child.
                    if (sibling.right.color == Color.BLACK) {
                        sibling.left.color = Color.BLACK;
                        sibling.color = Color.RED;
                        rotateRight(sibling);

                        sibling = node.parent.right;
                    }

                    // CASE 4: the sibling is black and its far child is red.
                    //
                    // This case recolors and rotates to finish the repair.
                    sibling.color = node.parent.color;
                    node.parent.color = Color.BLACK;
                    sibling.right.color = Color.BLACK;
                    rotateLeft(node.parent);

                    // The repair is complete.
                    node = root;
                }

            } else {
                // MIRROR CASE: the node is a right child.
                Node sibling = node.parent.left;

                // MIRROR CASE 1: sibling is red.
                if (sibling.color == Color.RED) {
                    sibling.color = Color.BLACK;
                    node.parent.color = Color.RED;
                    rotateRight(node.parent);

                    sibling = node.parent.left;
                }

                // MIRROR CASE 2: sibling and both children are black.
                if (sibling.right.color == Color.BLACK
                        && sibling.left.color == Color.BLACK) {
                    sibling.color = Color.RED;
                    node = node.parent;
                } else {
                    // MIRROR CASE 3: red child is on the near side.
                    if (sibling.left.color == Color.BLACK) {
                        sibling.right.color = Color.BLACK;
                        sibling.color = Color.RED;
                        rotateLeft(sibling);

                        sibling = node.parent.left;
                    }

                    // MIRROR CASE 4: outer child is red.
                    sibling.color = node.parent.color;
                    node.parent.color = Color.BLACK;
                    sibling.left.color = Color.BLACK;
                    rotateRight(node.parent);

                    // The repair is complete.
                    node = root;
                }
            }
        }

        // Finish by making the affected node black.
        // This also ensures that the root is black.
        node.color = Color.BLACK;
    }

    // ==========================================================
    //                         ROTATIONS
    // ==========================================================

    /**
     * Performs a left rotation around node.
     *
     * Example:
     *
     *       node                 right
     *      /    \                /    \
     *    left  right     ->    node  farRight
     *             \
     *          farRight
     */
    private void rotateLeft(Node node) {
        Node right = node.right;

        node.right = right.left;

        if (right.left != nil) {
            right.left.parent = node;
        }

        right.parent = node.parent;

        if (node.parent == nil) {
            root = right;
        } else if (node == node.parent.left) {
            node.parent.left = right;
        } else {
            node.parent.right = right;
        }

        right.left = node;
        node.parent = right;
    }

    /**
     * Performs a right rotation around node.
     *
     * Example:
     *
     *         node              left
     *        /    \             /    \
     *      left  right  ->  farLeft  node
     *      /
     *   farLeft
     */
    private void rotateRight(Node node) {
        Node left = node.left;

        node.left = left.right;

        if (left.right != nil) {
            left.right.parent = node;
        }

        left.parent = node.parent;

        if (node.parent == nil) {
            root = left;
        } else if (node == node.parent.right) {
            node.parent.right = left;
        } else {
            node.parent.left = left;
        }

        left.right = node;
        node.parent = left;
    }

    // ==========================================================
    //                           HELPERS
    // ==========================================================

    /**
     * Replaces oldNode in its parent with newNode.
     * It also updates the new node's parent reference.
     */
    private void transplant(Node oldNode, Node newNode) {
        if (oldNode.parent == nil) {
            // oldNode was the root.
            root = newNode;
        } else if (oldNode == oldNode.parent.left) {
            oldNode.parent.left = newNode;
        } else {
            oldNode.parent.right = newNode;
        }

        newNode.parent = oldNode.parent;
    }

    /**
     * Finds and returns the node containing key.
     * Returns NIL if the key does not exist.
     */
    private Node find(int key) {
        Node node = root;

        while (node != nil) {
            if (key == node.key) {
                return node;
            }

            node = key < node.key ? node.left : node.right;
        }

        return nil;
    }

    /**
     * Returns the smallest node in node's subtree.
     * The smallest key is always found by following left children.
     */
    private Node minimum(Node node) {
        while (node.left != nil) {
            node = node.left;
        }

        return node;
    }

    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(Node node) {
        if (node == nil) {
            return;
        }

        printInOrder(node.left);
        System.out.print(node.key + "(" + node.color + ") ");
        printInOrder(node.right);
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        for (int key : new int[]{10, 5, 15, 3, 7}) {
            tree.insert(key);
        }

        System.out.print("After insertion: ");
        tree.printInOrder();

        tree.delete(5);
        System.out.print("After deleting 5: ");
        tree.printInOrder();
    }
}