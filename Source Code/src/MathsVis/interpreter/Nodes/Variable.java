package MathsVis.interpreter.Nodes;

public class Variable extends TreeNode {

    public Variable(TreeNode left) {
        this.left = left;
    }

    @Override

    public String toString() {
        return "Variable{" +
                "left=" + left +
                '}';
    }

    public double eval() {
        return left.eval();
    }

}
