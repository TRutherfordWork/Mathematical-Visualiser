package MathsVis.interpreter.Nodes;

public class Polynomial extends TreeNode{

        public Polynomial(TreeNode left, TreeNode right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Polynomial{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }

        public double eval() {
            return left.eval() * right.eval();
        }
    }