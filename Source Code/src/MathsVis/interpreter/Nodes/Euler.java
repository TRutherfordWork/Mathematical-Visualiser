package MathsVis.interpreter.Nodes;

public class Euler extends TreeNode {

    private final double value;

    public double getValue() {
        return value;
    }

    public Euler() {
        this.value = Math.E;
    }

    @Override
    public String toString() {
        return "Euler{" +
                "value='" + value + '\'' +
                '}';
    }

    public double eval() {
        return value;
    }
}