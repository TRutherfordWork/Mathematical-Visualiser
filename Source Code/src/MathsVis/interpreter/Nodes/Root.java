package MathsVis.interpreter.Nodes;

public class Root extends TreeNode{

    //power left
    //expression right

    public Root(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Root{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    public double eval() {
        return Math.pow(right.eval(), 1.0 / left.eval());
    }
}
