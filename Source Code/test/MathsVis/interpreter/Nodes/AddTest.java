package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Add")
public class AddTest {

    Add add;

    @Test
    @DisplayName("is instantiated with new Add(TreeNode, TreeNode)")
    void isInstantiatedWithNew() {
        new Add(new TreeNode(), new TreeNode());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewAdd() {
            add = new Add(new RealNumber(4.0), new RealNumber(5.0));
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(add.toString());
        }

        @Test
        @DisplayName("evaluates correctly")
        void eval() {
            assertEquals(9.0, add.eval());
        }
    }
}
