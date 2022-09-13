package MathsVis.interpreter.Nodes;

/* Tree node is used as a base class for all nodes in the tree
 *
 *
 *
 *
 */

public class TreeNode{

    protected TreeNode left = null;
    protected TreeNode right = null;

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public double eval() {
        return 0;
    }
}
