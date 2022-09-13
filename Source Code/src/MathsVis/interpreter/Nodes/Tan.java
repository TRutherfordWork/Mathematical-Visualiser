package MathsVis.interpreter.Nodes;

public class Tan extends TreeNode {

    public Tan(TreeNode value) {
        this.left = value;
    }

    @Override
    public String toString() {
        return "Tan{" +
                "left='" + this.left + '\'' +
                '}';
    }

    public double eval() {
        return Math.tan(Math.toRadians(left.eval()));
    }
}
