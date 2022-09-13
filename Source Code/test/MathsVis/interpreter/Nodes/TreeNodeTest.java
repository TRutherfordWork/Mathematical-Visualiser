package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("A TreeNode")
class TreeNodeTest {

    TreeNode treeNode;

    @Test
    @DisplayName("is instantiated with new TreeNode()")
    void isInstantiatedWithNew() {
        new TreeNode();
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewTreeNode() {
            treeNode = new TreeNode();
        }

        @Test
        @DisplayName("has no left node")
        void hasNoLeftNode() {
            assertNull(treeNode.getLeft());
        }

        @Test
        @DisplayName("has no right node")
        void hasNoRightNode() {
            assertNull(treeNode.getRight());
        }

        @Test
        @DisplayName("can set left node")
        void canSetLeftNode() {
            treeNode.setLeft(new TreeNode());
        }

        @Test
        @DisplayName("can set right node")
        void canSetRightNode() {
            treeNode.setRight(new TreeNode());
        }

        @Nested
        @DisplayName("when nodes populated")
        class WhenPopulatedTest {
            @BeforeEach
            void createTreeNode() {
                treeNode.setLeft(new TreeNode());
                treeNode.setRight(new TreeNode());
            }

            @Test
            @DisplayName("has left node")
            void hasNoLeftNode() {
                assertNotNull(treeNode.getLeft());
            }

            @Test
            @DisplayName("has right node")
            void hasNoRightNode() {
                assertNotNull(treeNode.getRight());
            }

            @Test
            @DisplayName("can set left node")
            void canSetLeftNode() {
                treeNode.setLeft(new TreeNode());
            }

            @Test
            @DisplayName("can set right node")
            void canSetRightNode() {
                treeNode.setRight(new TreeNode());
            }
        }
    }
}