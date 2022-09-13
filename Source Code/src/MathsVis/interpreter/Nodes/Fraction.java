package MathsVis.interpreter.Nodes;

public class Fraction extends TreeNode {

    //numerator
    //denominator

    public Fraction(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    //this is where the maths happen
    public double eval() {
        if(left != null)
            return left.eval() / right.eval();
        return 0;
    }
}
