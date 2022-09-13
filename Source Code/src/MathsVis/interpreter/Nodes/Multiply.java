package MathsVis.interpreter.Nodes;

public class Multiply extends TreeNode{

    public Multiply(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Multiply{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    public double eval() {
        if(left != null)
            return left.eval() * right.eval();
        return 0;
    }
}
