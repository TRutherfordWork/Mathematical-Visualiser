package MathsVis.interpreter.Nodes;

public class RealNumber extends TreeNode {

    private final double value;

    public double getValue() {
        return value;
    }

    public RealNumber(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RealNumber{" +
                "value='" + value + '\'' +
                '}';
    }

    public double eval() {
        return value;
    }
}
