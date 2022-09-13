package MathsVis.interpreter.Nodes;

public class Cos extends TreeNode {

    public Cos(TreeNode value) {
        this.left = value;
    }

    @Override
    public String toString() {
        return "Cos{" +
                "left='" + this.left + '\'' +
                '}';
    }

    public double eval() {
        return Math.cos(Math.toRadians(left.eval()));
    }

}
