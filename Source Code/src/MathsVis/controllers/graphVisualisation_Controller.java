package MathsVis.controllers;

import MathsVis.interpreter.Lexer;
import MathsVis.interpreter.Nodes.Graph;
import MathsVis.interpreter.Parser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class graphVisualisation_Controller {

    public LineChart selectedGraphWidget;
    public TextField yLabelTextField;
    public TextField yArgumentsTextField;
    public Button enterButton;
    public TextField xArgumentsTextField;
    public TextField xLabelTextField;
    public ColorPicker lineColourPicker;
    public Text xAxisLabel;
    public Text yAxisLabel;
    public ArrayList<String> graphList;
    public ComboBox graphComboBox;
    boolean inlineToggle;

    public void initialise(ArrayList<Double> xCoords, ArrayList<Double> yCoords){
        //setting x and y coordinate boxes
        xArgumentsTextField.setText(xCoords.toString());
        yArgumentsTextField.setText(yCoords.toString());
    }

    public void buildGraph(String graphExpression, ArrayList<String> graphList) {

        //lexing the chosen graph expression
        Lexer lexer = new Lexer(graphExpression);

        //parsing the tokens that are a result of the lexer
        Parser parse = new Parser(lexer.getTokenList(), lexer.getErrorLog());

        //getting x and y value lists from parser token results
        Graph selectedGraph = (Graph) parse.getGraphs().get(0);

        ArrayList<Double> xCoords = selectedGraph.getX();
        ArrayList<Double> yCoords = selectedGraph.getY();

        //System.out.println(xCoords + "\n" + yCoords);

        selectedGraphWidget.setTitle(graphExpression);

        //building points for the graph using the compatible XYChart.Series object
        XYChart.Series points = new XYChart.Series();

        //iterating through the values within the coords-lists
        for(int i = 0; i < xCoords.size(); i++) {
            Double xCoordinate = xCoords.get(i);
            Double yCoordinate = yCoords.get(i);
            //adding points to the "points" object
            points.getData().add(new XYChart.Data(xCoordinate.toString(), yCoordinate));
        }

        //setting graph widget to contain data within the "points" object
        selectedGraphWidget.getData().addAll(points);

        //initialising scene's widgets
        initialise(xCoords, yCoords);

        //initialising object graphList
        this.graphList = graphList;

        buildOverlayCombo();
    }

    private void buildOverlayCombo(){
        //generating observable List
        ObservableList<String> comboList = FXCollections.observableArrayList(this.graphList);
        graphComboBox.setItems(comboList);

    }

    public ArrayList<Double> convertArgs(TextField ArgumentsTextField){

        String Arg = ArgumentsTextField.getText().substring(1, (ArgumentsTextField.getLength()-1));
        //setting string to string list
        String[] ArgList = Arg.split(",");
        List<String> ArgArray = Arrays.asList(ArgList);
        //converting string list into double array list
        ArrayList<Double> ArgArrayList = new ArrayList<>();

        double argDouble;
        for(String arg: ArgArray){
            argDouble = Double.parseDouble(arg);
            ArgArrayList.add(argDouble);
        }

        return ArgArrayList;

    }


    public void enterButtonAction(ActionEvent actionEvent) throws IOException {
        //converting user entered data
        ArrayList<Double> xArgs = convertArgs(xArgumentsTextField);
        ArrayList<Double> yArgs = convertArgs(yArgumentsTextField);
        //clearing graph
        selectedGraphWidget.getData().clear();

        //building new XY coordinates
        XYChart.Series points = new XYChart.Series();

        //iterating through the values within the coords-lists
        for(int i = 0; i < xArgs.size(); i++) {
            Double xCoordinate = xArgs.get(i);
            System.out.println(xCoordinate);
            Double yCoordinate = yArgs.get(i);
            System.out.println(yCoordinate);
            //adding points to the "points" object
            points.getData().add(new XYChart.Data(xCoordinate.toString(), yCoordinate));
        }

        //setting graph widget to contain data within the "points" object
        selectedGraphWidget.getData().addAll(points);

        //initialising scene's widgets
        initialise(xArgs, yArgs);

        if(graphComboBox.getValue().toString() != null){
            overlayGraph(graphComboBox.getValue().toString());
        }


    }

    private void overlayGraph(String graphExpression){

        //lexing and parsing the chosen graph expression
        Lexer lexer = new Lexer(graphExpression);
        Parser parse = new Parser(lexer.getTokenList(), lexer.getErrorLog());
        //getting x and y value lists from parser token results
        Graph selectedGraph = (Graph) parse.getGraphs().get(0);

        ArrayList<Double> xArgs = selectedGraph.getX();
        ArrayList<Double> yArgs = selectedGraph.getY();

        XYChart.Series points = new XYChart.Series();

        //iterating through the values within the coords-lists
        for(int i = 0; i < xArgs.size(); i++) {
            Double xCoordinate = xArgs.get(i);
            System.out.println(xCoordinate);
            Double yCoordinate = yArgs.get(i);
            System.out.println(yCoordinate);
            //adding points to the "points" object
            points.getData().add(new XYChart.Data(xCoordinate.toString(), yCoordinate));
        }

        //setting graph widget to contain data within the "points" object
        selectedGraphWidget.getData().addAll(points);

    }

    public void editGraphAction(ActionEvent actionEvent) throws IOException {
        //in order to change the graphs colour we first need to create a new style sheet (css) and then input that colour
        xAxisLabel.setText(xLabelTextField.getText());
        yAxisLabel.setText(yLabelTextField.getText());


    }
}
