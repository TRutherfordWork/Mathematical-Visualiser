package MathsVis.interpreter.Nodes;

public class Indeterminate extends TreeNode {
    private String key;

    public Indeterminate(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "Indeterminate{" +
                "key='" + key + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
