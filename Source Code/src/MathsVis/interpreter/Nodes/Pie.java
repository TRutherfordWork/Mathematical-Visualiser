package MathsVis.interpreter.Nodes;

public class Pie extends TreeNode {

    private final double value;

    public double getValue() {
        return value;
    }

    public Pie() {
        this.value = Math.PI;
    }

    @Override
    public String toString() {
        return "Pie {" +
                "value='" + value + '\'' +
                '}';
    }

    public double eval() {
        return value;
    }
}