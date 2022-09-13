package MathsVis.interpreter.Nodes;

public class Subtract extends TreeNode {

    public Subtract(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Subtract{" +
                "left=" + left +
                ", Right=" + right +
                '}';
    }

    @Override
    public double eval() {
        return left.eval() - right.eval();
    }
}
