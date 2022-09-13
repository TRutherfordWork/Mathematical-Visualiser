package MathsVis.interpreter.Nodes;

public class Divide extends TreeNode {

    public Divide(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Divide{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
    public double eval() {
        if(left != null)
            return left.eval() / right.eval();
        return 0;
    }

}
