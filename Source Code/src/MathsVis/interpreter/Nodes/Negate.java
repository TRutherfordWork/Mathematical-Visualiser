package MathsVis.interpreter.Nodes;

public class Negate extends TreeNode {

    public Negate(TreeNode left) {
        this.left = left;
    }

    @Override
    public String toString() {
        return "Negate{" +
                "argument=" + left +
                '}';
    }
    public double eval() {
        return -(left.eval());
    }
}
