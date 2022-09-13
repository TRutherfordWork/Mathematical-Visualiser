package MathsVis.interpreter.Nodes;

public class Add extends TreeNode {

    public Add(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Add{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public double eval() {

        if(left != null)
            return left.eval() + right.eval();

        return 0;
    }

}