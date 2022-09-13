package MathsVis.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import MathsVis.interpreter.Lexer;
import MathsVis.interpreter.Nodes.TreeNode;
import MathsVis.interpreter.Parser;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class resultsPane_Controller {

    public ComboBox inputSelectionComboBox;
    public Pane renderedEquationPane;
    public TextArea resultsOutput;
    ArrayList<String> equationsArray = new ArrayList<String>();
    ArrayList<Double> results = new ArrayList<Double>();
    public boolean inlineToggle;
    ObservableList<String> userInputList;
    ArrayList<String> graphList = new ArrayList<>();

    //arrayList<Strings> to denote currently defined functions (important for returning null or results)
    ArrayList<String> functionList = new ArrayList<>();

    public void initialise() {
    }

    public void initialise(String equations, boolean toggle){
        equations = equations.replaceAll("\\s+","");
        System.out.println(equations);

        //populating graph equations list
        String[] splitEquations = equations.split(";");

        for(String equation : splitEquations){

            System.out.println(equation);

            if (equation.contains("\\graph")){
                //adding to graphList
                graphList.add(equation);
            }

        }


        //populating equations List
        userInputList = FXCollections.observableArrayList(equations.split(";"));
        inputSelectionComboBox.setItems(userInputList);
        inlineToggle = toggle;

        if(!toggle) {
            equations = MyCanvas.defineVariables(equations);
        }

        String[] equationsList = equations.split(";");
        equationsArray.addAll(Arrays.asList(equationsList));

        Lexer lexer = new Lexer(equations);

        Parser parse = new Parser(lexer.getTokenList(), lexer.getErrorLog());

        StringBuilder result = new StringBuilder();

        for (TreeNode node :
                parse.getResultsTrees()) {
            result.append("Expression ");
            result.append(parse.getResultsTrees().indexOf(node) + 1);
            result.append(": ");
            result.append(node.eval()).append("\n");

        }

        for (TreeNode node : parse.getResultsTrees()) {
            results.add(node.eval());
        }

        System.out.println("expressions = " + equationsArray.toString());
        System.out.println("results = " + results);

        resultsOutput.appendText(String.valueOf(result));

        //making sure that results are saved as null for variable declarations or function definition etc

        int index;
        //used to determine if the dollar sign denotes a function definition or call (calls can only be made once definition has been made)
        String functionSubstring;
        boolean functionFullyDefined = true;

        for(index = 0; index < equationsArray.size(); index++){
            String currentEquation = equationsArray.get(index);
            System.out.println(currentEquation);
            System.out.println("results ===== " + results);

            if(!functionFullyDefined){
                results.add(index, null);
                if(currentEquation.equals("#")){
                    functionFullyDefined = true;
                }
            }

            else if(currentEquation.contains("=") || currentEquation.contains("\\graph")){
                System.out.println(index);
                results.add(index, null);
            }

            //$ denotes function declaration or call
            else if(currentEquation.contains("$")){
                //getting substring
                functionSubstring = currentEquation.substring(0,4);

                if(!functionList.contains(functionSubstring)){
                    functionList.add(functionSubstring);
                    results.add(index, null);
                    //used to skip past any internal body code & delimiters
                    functionFullyDefined = false;

                    //creating new string

                }
            }

        }}





    private void processEquations(String equations){

    }

    private void processEquation(String equation){

    }

    private void selectExpression(){
        resultsOutput.clear();
        String selectedExpression = inputSelectionComboBox.getValue().toString();


        int index;

        //getting relevant result to combo box selection
        index = equationsArray.indexOf(selectedExpression);

        //displaying relevant result in results widget

        System.out.println(equationsArray.toString() + "\n" + results.toString() + "\n" + String.valueOf(results.get(index)));

        System.out.println(String.valueOf(results.get(index)));
        resultsOutput.appendText(String.valueOf(results.get(index)));

    }

    public void inputSelectionComboBoxAction(ActionEvent actionEvent) {
        try {
            String expression = inputSelectionComboBox.getValue().toString();
            renderedEquationPane.getChildren().clear();
            MyCanvas canvas = new MyCanvas(expression, inlineToggle);
            renderedEquationPane.getChildren().add(canvas);
            canvas.widthProperty().bind(renderedEquationPane.widthProperty());
            canvas.heightProperty().bind(renderedEquationPane.heightProperty());
        }catch(Exception InvocationTargetException){
            System.out.println("CUSTOM SYNTAX USED");
        }
        //JORDAN - Insert code below that sends answer to "resultsOutput" after computing the "expression" string
        //Remember to take into account user defined variables and functions that are defined outside of text.
        //If defined outside they should be set at the start of the expression, before any computation and can be
        //overwritten by expressions inside the userinput text.


        selectExpression();

        //MATT - Insert code below that generates a Graph pane if the expression selection starts with \graph
        //the variables for \graph are stored within {}, as defined by JORDAN.



    }

    public void showGraphButtonAction(ActionEvent actionEvent) throws IOException {
        String inputSelection = inputSelectionComboBox.getValue().toString();

        if(inputSelection.contains("\\graph")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/graphVisualisation.fxml"));

            Parent popUpParent = loader.load();

            //controller method called to initialise combo box with equations list input
            graphVisualisation_Controller graphVisualisationController = loader.getController();
            //used to build the initially selected graphs (will be able to overlay graphs once on the page)
            graphVisualisationController.buildGraph(inputSelection, graphList);

            Scene popUpScene = new Scene(popUpParent);

            Stage stage = new Stage();
            stage.setScene(popUpScene);

            stage.showAndWait();

        } else{
            Alert variableDefinedAlert = new Alert(Alert.AlertType.ERROR);
            variableDefinedAlert.setTitle("Error");
            variableDefinedAlert.setHeaderText("No Graph Selected!");
            variableDefinedAlert.setContentText(String.format("Incompatible Selection, please select a graph expression to visualise"));

            variableDefinedAlert.showAndWait();
        }
    }
}
