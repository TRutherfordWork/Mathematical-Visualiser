package MathsVis.interpreter;

import MathsVis.interpreter.Lexer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A Lexer")
class LexerTest {

    Lexer lexer;

    @Test
    @DisplayName("is instantiated with new Lexer(String)")
    void isInstantiatedWithNew() {
        new Lexer("A string");
    }

    @Nested
    @DisplayName("when new with empty string")
    class WhenNewEmptyTest {
        @BeforeEach
        void createNewLexer() {
            lexer = new Lexer("");
        }

        @Test
        @DisplayName("has tokens")
        void hasTokens() {
            assertFalse(lexer.getTokenList().isEmpty());
        }

        @Test
        @DisplayName("has no error log")
        void hasNoErrorLog() {
            assertTrue(lexer.getErrorLog().getMessage().isEmpty());
        }

        @Test
        @DisplayName("has a toString()")
        void hasToString() {
            assertNotNull(lexer.toString());
        }
    }

    @Nested
    @DisplayName("when new with legal expression")
    class WhenNewLegalTest {
        @BeforeEach
        void createNewLexer() {
            lexer = new Lexer("54a + \\frac{b}{a}");
        }

        @Test
        @DisplayName("has tokens")
        void hasTokens() {
            assertTrue(!lexer.getTokenList().isEmpty());
        }

        @Test
        @DisplayName("has no error log")
        void hasNoErrorLog() {
            assertTrue(lexer.getErrorLog().getMessage().isEmpty());
        }

        @Test
        @DisplayName("has a toString()")
        void hasToString() {
            assertNotNull(lexer.toString());
        }
    }

    @Nested
    @DisplayName("when new with illegal expression")
    class WhenNewIllegalTest {
        @BeforeEach
        void createNewLexer() {
            lexer = new Lexer("54a + \\illegal_{b}{a}");
        }

        @Test
        @DisplayName("has tokens")
        void hasTokens() {
            assertFalse(lexer.getTokenList().isEmpty());
        }

        @Test
        @DisplayName("has error log")
        void hasErrorLog() {
            assertFalse(lexer.getErrorLog().getMessage().isEmpty());
        }

        @Test
        @DisplayName("has a toString()")
        void hasToString() {
            assertNotNull(lexer.toString());
        }
    }
}