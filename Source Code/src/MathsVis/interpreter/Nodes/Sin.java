package MathsVis.interpreter.Nodes;

public class Sin extends TreeNode {

    public Sin(TreeNode value) {
        this.left = value;
    }

    @Override
    public String toString() {
        return "Sin{" +
                "left='" + this.left + '\'' +
                '}';
    }

    public double eval() {
        return Math.sin(Math.toRadians(left.eval()));
    }
}
