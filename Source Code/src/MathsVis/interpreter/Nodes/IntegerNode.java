package MathsVis.interpreter.Nodes;

public class IntegerNode extends TreeNode {

    private final double value;

    public double getValue() {
        return value;
    }

    public IntegerNode(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "IntegerNode{" +
                "value='" + value + '\'' +
                '}';
    }

    public double eval() {
        return value;
    }
}
