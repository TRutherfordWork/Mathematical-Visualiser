package MathsVis.interpreter.Nodes;

public class Logarithm extends TreeNode{

    //base

    //argument

    public Logarithm(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Logarithm{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    public double eval() {
        return Math.log(right.eval()) / Math.log(left.eval());
    }
}
