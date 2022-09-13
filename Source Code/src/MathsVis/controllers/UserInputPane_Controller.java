package MathsVis.controllers;

import MathsVis.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import MathsVis.interpreter.*;
import MathsVis.interpreter.Nodes.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class UserInputPane_Controller{
    
    
    public Pane renderedEquationPane;
    public TextArea mainUserInput;
    public int caretPosition;
    public CheckBox toggleInlineVariableOn;
    public TextArea errorChecker;
    public Pane twoMatrixButton;
    public Pane threeMatrixButton;
    public Pane fourMatrixButton;
    public Pane addGraphButton;
    public Button resetButton;
    public Button numpadButton;
    public Pane divisionButtonBase;
    public TextField searchExpressions;
    String mainUserInputTest;
    //used to check if toggleInlineVariable is checked or not
    boolean inlineToggle = false;

    //used to check if numpad is open or closed
    boolean open = false;
    private Object Node;


    public void initialise(){
        caretPosition = mainUserInput.getCaretPosition();
        renderedEquationPane.getChildren().clear();
        try {
            MyCanvas canvas = new MyCanvas(mainUserInput.getText(), inlineToggle);
            renderedEquationPane.getChildren().add(canvas);
            canvas.widthProperty().bind(renderedEquationPane.widthProperty());
            canvas.heightProperty().bind(renderedEquationPane.heightProperty());
        } catch(Exception InvocationTargetException){
            System.out.println("Custom Syntax Used");
        }

    }


    public void addEquation(String equation){
        caretPosition = mainUserInput.getCaretPosition();
        mainUserInput.insertText(caretPosition, equation);
        initialise();
    }


    //Basic Maths Function Button handlers:
    public void divisionButtonAction(MouseEvent mouseEvent) {
        addEquation("\\frac{a}{b}");
    }

    public void squaredButtonAction(MouseEvent mouseEvent) {
        addEquation("a^{2}");
    }

    public void powerButtonAction(MouseEvent mouseEvent) {
        addEquation("a^{b}");
    }

    public void squareRootButtonAction(MouseEvent mouseEvent) {
        addEquation("\\sqrt{a}");
    }

    public void cubedRootButtonAction(MouseEvent mouseEvent) {
        addEquation("\\sqrt[3]{a}");
    }

    public void nthRootButtonAction(MouseEvent mouseEvent) {
        addEquation("\\sqrt[n]{a}");
    }

    public void exponentialButtonAction(MouseEvent mouseEvent) {
        addEquation("\\exp^{n}");
    }

    public void naturalLogButtonAction(MouseEvent mouseEvent) {
        addEquation("\\ln{x}");
    }

    public void logNButtonAction(MouseEvent mouseEvent) {
        addEquation("\\log_(n){x}");
    }

    public void commonLogButtonAction(MouseEvent mouseEvent) {
        addEquation("\\log_(10){x}");
    }

    public void pieButtonAction(MouseEvent mouseEvent) {
        addEquation("\\pi");
    }

    public void eulersButtonAction(MouseEvent mouseEvent) {
        addEquation("\\varepsilon");
    }

    public void derivativeButtonAction(MouseEvent mouseEvent) {
        addEquation("\\frac{d}{dx} ");
    }

    public void secondOrderDerivativeButtonAction(MouseEvent mouseEvent) {
        addEquation("\\frac{d^2}{{d^2}x}");
    }

    public void integralButtonAction(MouseEvent mouseEvent) {
        addEquation("\\int");
    }

    public void definedIntegralButtonAction(MouseEvent mouseEvent) {
        addEquation("\\int_{a}^b dx");
    }

    public void summationButtonAction(MouseEvent mouseEvent) {
        addEquation("\\sum_{a}^{n}");
    }

    private void alertBox(String title, String headerText, String contentText){

        Alert variableDefinedAlert = new Alert(Alert.AlertType.INFORMATION);
        variableDefinedAlert.setTitle(title);
        variableDefinedAlert.setHeaderText(headerText);
        variableDefinedAlert.setContentText(contentText);

        variableDefinedAlert.showAndWait();

    }

    public void visualiseButtonAction(ActionEvent actionEvent) throws Exception {
        String userText = mainUserInput.getText();
        System.out.println(inlineToggle);

        //clearing error checker
        errorChecker.clear();

        //swapping instances of variables with their defined values (if defined by user)
        HashMap<String, String> userDefinedVariables = assignVariables_Controller.variableLookUp;
        ArrayList<String> userVariables = new ArrayList<>(userDefinedVariables.keySet());
        ArrayList<String> userValues = new ArrayList<>(userDefinedVariables.values());

        System.out.println(userVariables);
        System.out.println(userValues);

        //pre-defines variables only if inline definition is not checked in the UI
        if(!inlineToggle) {
            userText = MyCanvas.defineVariables(userText);

            //removing declarative statements from list
            ArrayList<String> equationsArray = new ArrayList<>();
            String[] equationsList = userText.split(";");
            for(String equation : equationsList){
                if(!equation.contains("=")){
                    equationsArray.add(equation+";");
                } else{
                    alertBox("Inline Variable Declaration Detected", ("'"+ equation +"';  has been entered despite Inline Variable Declaration being toggled off. \n This declaration will therefore be ignored.")
                            , "Predefined Variables will be used instead, as defined in the Index Variables Window.");
                }
            }
            //resetting userText to be rebuilt without declarative statements.
            userText = "";

            for(String equation : equationsArray){
                userText = userText+equation;
            }

        }

        Lexer lexer = new Lexer(userText);

        errorChecker.setText(lexer.getErrorLog().getMessage());
        //error handling
        if (Objects.equals(errorChecker.getText(), "")) {

            Parser parse = new Parser(lexer.getTokenList(), lexer.getErrorLog());

            errorChecker.setText(parse.getErrorLog().getMessage());

            if (Objects.equals(errorChecker.getText(), "")) {

                StringBuilder result = new StringBuilder();

                for (TreeNode node :
                        parse.getResultsTrees()) {
                    result.append("Expression ");
                    result.append(parse.getResultsTrees().indexOf(node) + 1);
                    result.append(": ");
                    result.append(node.eval()).append("\n");

                }

                //following method code is used to load the new pop-up scene
                //cannot use main class pop-up method due to controller method calls
                //should overload class method if same code is used again.

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/results.fxml"));

                Parent popUpParent = loader.load();

                //controller method called to initialise combo box with equations list input
                resultsPane_Controller resultsPaneController = loader.getController();
                resultsPaneController.initialise(userText, inlineToggle);

                Scene popUpScene = new Scene(popUpParent);

                Stage stage = new Stage();
                stage.setScene(popUpScene);
                //setting pop-up window to have priority over main window. It must be closed before you can interact with main
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.showAndWait();
            }
        }
    }

    public void indexVariablesButtonAction(ActionEvent actionEvent) throws IOException {
        Main.popUpNewScene(actionEvent, "resources/fxml/assignVariables.fxml");
    }

    public void resetButtonAction(ActionEvent actionEvent) {
        mainUserInput.clear();
    }

    public void indexFunctionsButtonAction(ActionEvent actionEvent) throws IOException {
        Main.popUpNewScene(actionEvent, "resources/fxml/assignFunctions.fxml");
    }

    public void numpadButtonAction(ActionEvent actionEvent) throws IOException {
        Main.expandWindow(open);
        if(open){
            open = false;
        } else {
            open = true;
        }
    }

    //following controls numpad popup

    public void percentButtonAction(ActionEvent actionEvent) throws IOException {
        addEquation("%");
    }

    public void fwdSlashButton(ActionEvent actionEvent) {
        addEquation("/");
    }

    public void multiplyButtonAction(ActionEvent actionEvent) {
        addEquation("*");
    }

    public void minusButtonAction(ActionEvent actionEvent) {
        addEquation("-");
    }

    public void sevenButtonAction(ActionEvent actionEvent) {
        addEquation("7");
    }

    public void eightButtonAction(ActionEvent actionEvent) {
        addEquation("8");
    }

    public void nineButtonAction(ActionEvent actionEvent) {
        addEquation("9");
    }

    public void fourButtonAction(ActionEvent actionEvent) {
        addEquation("4");
    }

    public void fiveButtonAction(ActionEvent actionEvent) {
        addEquation("5");
    }

    public void sixButtonAction(ActionEvent actionEvent) {
        addEquation("6");
    }

    public void zeroButtonAction(ActionEvent actionEvent) {
        addEquation("0");
    }

    public void dotButtonAction(ActionEvent actionEvent) {
        addEquation(".");
    }

    public void oneButtonAction(ActionEvent actionEvent) {
        addEquation("1");
    }

    public void twoButtonAction(ActionEvent actionEvent) {
        addEquation("2");
    }

    public void threeButtonAction(ActionEvent actionEvent) {
        addEquation("3");
    }

    public void plusButtonAction(ActionEvent actionEvent) {
        addEquation("+");
    }

    public void enterButtonAction(ActionEvent actionEvent) throws Exception { visualiseButtonAction(actionEvent); }

    public void twoMatrixButtonAction(MouseEvent actionEvent) {
        addEquation("\\left[\\begin{array}\\\\{a}{b}\\\\{x}{y}\\end{array}\\right] ");
    }

    public void threeMatrixButtonAction(MouseEvent actionEvent) {
        addEquation("\\left[\\begin{array}\\\\{a}{b}{c}\\\\{x}{y}{z}\\\\{a}{b}{c}\\end{array}\\right] ");
    }

    public void fourMatrixButtonAction(MouseEvent actionEvent) {
        addEquation("\\left[\\begin{array}\\\\{a}{b}{c}{d}\\\\{w}{x}{y}{z}\\\\{a}{b}{c}{d}\\\\{w}{x}{y}{z}\\end{array}\\right] ");
    }

    public void addGraphButtonAction(MouseEvent mouseEvent) {
        addEquation("\\graph[1,2,3](x^{2});");
    }

    public void toggleInlineVariableOnAction(ActionEvent actionEvent) {
        //changes how variables are processed.
        inlineToggle = !inlineToggle;
        initialise();
    }

    public void sinButtonAction(MouseEvent mouseEvent) {
        addEquation("\\sin{x}");
    }

    public void cosButtonAction(MouseEvent mouseEvent) {
        addEquation("\\cos{x}");
    }

    public void tanButtonAction(MouseEvent mouseEvent) {
        addEquation("\\tan{x}");
    }

    public void callFunctionAction(MouseEvent mouseEvent) {
        addEquation("$addition<1,2>;");
    }

    public void addNewFunctionAction(MouseEvent mouseEvent) {
        addEquation("$addition<x,y>#\nx+y;\n#;");
    }
}





