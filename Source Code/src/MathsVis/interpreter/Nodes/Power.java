package MathsVis.interpreter.Nodes;

public class Power extends TreeNode{

    public Power(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Power{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    public double eval() {
        return Math.pow(left.eval(),  right.eval());
    }
}
