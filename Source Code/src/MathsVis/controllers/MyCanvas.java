package MathsVis.controllers;

import javafx.scene.canvas.Canvas;
import org.jfree.fx.FXGraphics2D;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

////the following class is used to convert the latex user-input to a visualised widget on screen in real time

public class MyCanvas extends Canvas {

    private FXGraphics2D FXG2D;
    private TeXIcon visualisedFormula;

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }


    public MyCanvas(String equation, boolean toggleInline) {
        this.FXG2D = new FXGraphics2D(getGraphicsContext2D());

        //splitting string up to find the most recently typed equation
        equation.replace("\n", "");
        String[] equationList = equation.split(";");
        equation = equationList[equationList.length - 1];

        //rewriting formula to account for user-defined variables
        if(!toggleInline) {
            equation = defineVariables(equation);
        }

        System.out.println(equation);

        //creating the latex formula object, constructed from the user-defined equation
        TeXFormula formula = new TeXFormula(equation);

        //creating a widget that is a visualised version of the latex formula object
        this.visualisedFormula = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);

        //when the size of the texFormula changes, update the canvas
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }


    public static String defineVariables(String equation) {

        HashMap<String, String> userDefinedVariables = assignVariables_Controller.variableLookUp;
        ArrayList<String> userVariables = new ArrayList<>(userDefinedVariables.keySet());
        ArrayList<String> userValues = new ArrayList<>(userDefinedVariables.values());

        int inputLength = equation.length();
        char current_char;

        for (int char_index = 0; char_index < equation.length(); char_index++) {
            current_char = equation.charAt(char_index);
            //System.out.println(current_char);


            if (current_char == '\\') {

                int operation_index;

                //iterating through until '{' is found (end of operator)
                for (operation_index = char_index; operation_index < inputLength; operation_index++) {

                    //System.out.println(operation_index);

                    //if end of operation call then break
                    if (equation.charAt(operation_index) == '{' || equation.charAt(operation_index) == '[' || equation.charAt(operation_index) == ' ') {
                        break;
                    }

                }

                char_index = operation_index - 1;

            } else if (userVariables.contains(String.valueOf(current_char))) {
                int variableIndex = userVariables.indexOf(String.valueOf(current_char));
                String userValue = userValues.get(variableIndex);
                if (!Objects.equals(userValue, "")) {
                    //System.out.println(equation);
                    String newEquation = equation.substring(0, char_index) + userValue + equation.substring(char_index + 1, equation.length());

                    equation = newEquation;

                    char_index = char_index + userValue.length() - 1;

                    //System.out.println(equation);


                }

            }

            //System.out.println("IL:   " + equation.length());
            //System.out.println("CI    " + char_index);

        }

        System.out.println(equation);
        return equation;


    }

    private void draw() {
        double width = getWidth();
        double height = getHeight();
        getGraphicsContext2D().clearRect(0, 0, width, height);

        //image created from earlier equation render
        BufferedImage image = new BufferedImage(visualisedFormula.getIconWidth(), visualisedFormula.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, visualisedFormula.getIconWidth(), visualisedFormula.getIconHeight());
        JLabel jl = new JLabel();
        jl.setForeground(new Color(0, 0, 0));
        visualisedFormula.paintIcon(jl, graphics, 0, 0);
        //image is finalised and drawn to canvas widget
        this.FXG2D.drawImage(image, 0, 0, null);
    }

}
