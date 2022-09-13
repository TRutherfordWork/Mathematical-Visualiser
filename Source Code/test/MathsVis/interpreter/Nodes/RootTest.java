package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("A Root")
class RootTest {

    Root root;

    @Test
    @DisplayName("is instantiated with new Root(TreeNode, TreeNode)")
    void isInstantiatedWithNew() {
        new Root(new TreeNode(), new TreeNode());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewRoot() {
            root = new Root(new RealNumber(2.0), new RealNumber(2.0));
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(root.toString());
        }

        @Test
        @DisplayName("evaluates correctly")
        void eval() {
            assertEquals(Math.pow(2.0, 1.0 / 2.0), root.eval());
        }
    }
}