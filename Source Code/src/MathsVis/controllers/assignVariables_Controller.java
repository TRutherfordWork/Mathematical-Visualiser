package MathsVis.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.Objects;

public class assignVariables_Controller {


    public ComboBox varComboBox;
    public TextField assignTextField;
    public TextArea currentlyAssignedTextArea;
    public Button assignVariableButton;

    ObservableList<String> assignedVariables = FXCollections.observableArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i"
    , "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");

    public static HashMap<String, String> variableLookUp = new HashMap<String, String>();

    public void initialise() {
        varComboBox.setItems(assignedVariables);
        buildLookUp();
    }

    public void buildLookUp(){
        for(int i=0; i < assignedVariables.size(); i++){
            if(!variableLookUp.containsKey(assignedVariables.get(i))){
                variableLookUp.put(assignedVariables.get(i), "");
            }
        }
    }

    public void addLookUp(ActionEvent actionEvent) {
        String variableValue = assignTextField.getText();

        if(!Objects.equals(variableValue, "")) {
            String variableKey = varComboBox.getValue().toString();
            System.out.println(variableKey + " " + variableValue);
            variableLookUp.replace(variableKey, variableValue);

            //creating alert to inform the user of the new update

            Alert variableDefinedAlert = new Alert(Alert.AlertType.CONFIRMATION);
            variableDefinedAlert.setTitle("Action Confirmed");
            variableDefinedAlert.setHeaderText("Variable Defined");
            variableDefinedAlert.setContentText(String.format("Variable: %s \nNew Value: %s", variableKey, variableValue));

            variableDefinedAlert.showAndWait();

            assignTextField.clear();

            //adding newly assigned variable to the large text area

            String definedVariables = currentlyAssignedTextArea.getText();
            currentlyAssignedTextArea.setText(definedVariables + String.format("\nVariable: %s        New Value: %s", variableKey, variableValue));
        }
    }


    public void varComboBoxAction(ActionEvent actionEvent) {
        String variableKey = varComboBox.getValue().toString();
        String variableValue = variableLookUp.get(variableKey);

        assignTextField.clear();
        assignTextField.setText(variableValue);

    }
}
