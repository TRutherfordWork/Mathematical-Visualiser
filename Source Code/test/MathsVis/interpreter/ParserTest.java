package MathsVis.interpreter;

import MathsVis.interpreter.Lexer;
import MathsVis.interpreter.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A Parser")
class ParserTest {

    Parser parser;
    Lexer legalLexer = new Lexer("54a + \\frac{b}{a}");
    Lexer graphableLexer = new Lexer("\\graph[1,2,3](x^2)");
    Lexer illegalLexer = new Lexer("5 * \\illegal_{2} + 3x");

    @Test
    @DisplayName("is instantiated with new Parser(ArrayList<Token>, ErrorLog)")
    void isInstantiatedWithNew() {
        new Parser(legalLexer.getTokenList(), legalLexer.getErrorLog());
    }

    @Nested
    @DisplayName("when new with legal lexer")
    class WhenNewLegalTest {
        @BeforeEach
        void createNewParser() {
            parser = new Parser(legalLexer.getTokenList(), legalLexer.getErrorLog());
        }

        @Test
        @DisplayName("has no error log")
        void hasNoErrorLog() {
            assertTrue(parser.getErrorLog().getMessage().isEmpty());
        }

        @Test
        @DisplayName("has graphs")
        void hasNoGraphs() {
            System.out.println(parser.getGraphs());
            assertTrue(parser.getGraphs().isEmpty());
        }
    }

    @Nested
    @DisplayName("when new with legal, graph-able lexer")
    class WhenNewLegalGraphableTest {
        @BeforeEach
        void createNewParser() {
            parser = new Parser(graphableLexer.getTokenList(), graphableLexer.getErrorLog());
        }

        @Test
        @DisplayName("has no error log")
        void hasNoErrorLog() {
            assertTrue(parser.getErrorLog().getMessage().isEmpty());
        }

        @Test
        @DisplayName("has graphs")
        void hasGraphs() {
            System.out.println(parser.getGraphs());
            assertFalse(parser.getGraphs().isEmpty());
        }
    }

    @Nested
    @DisplayName("when new with illegal lexer")
    class WhenNewIllegalTest {
        @BeforeEach
        void createNewParser() {
            parser = new Parser(illegalLexer.getTokenList(), illegalLexer.getErrorLog());
        }

        @Test
        @DisplayName("has error log")
        void hasErrorLog() {
            System.out.println(illegalLexer.getErrorLog().getMessage());
            assertFalse(parser.getErrorLog().getMessage().isEmpty());
        }

        @Test
        @DisplayName("has no graphs")
        void hasNoGraphs() {
            assertTrue(parser.getGraphs().isEmpty());
        }
    }
}