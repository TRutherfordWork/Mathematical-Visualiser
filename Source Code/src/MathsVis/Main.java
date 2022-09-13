package MathsVis;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    //keep track of inaccessible stages (Modality.APPLICATION_MODAL event)
    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/userInputPane.fxml"));
        primaryStage.setTitle("Maths Visualiser");
        primaryStage.setScene(new Scene(root, 868, 890));
        mainStage = primaryStage;
        Image image = new Image("MathsVis/resources/maths_symbols/basics/pie.png");
        mainStage.getIcons().add(image);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void expandWindow(boolean open){
        System.out.println(open);
        if(!open) {
            mainStage.setResizable(true);
            mainStage.setWidth(1160);
            mainStage.setResizable(false);
        }
        else{
            mainStage.setResizable(true);
            mainStage.setWidth(880);
            mainStage.setResizable(false);
        }
    }

    /*if you need to add navigation for whatever reason, do so by passing in the actionEvent trigger and then the page
    name you want to navigate to*/
    public static void changeMainScene(ActionEvent actionEvent, String fileName) throws IOException {
        //loading new fxml file dynamically
        System.out.println(fileName);
        Parent methodParent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fileName)));
        Scene methodScene = new Scene(methodParent);

        //get stage information from selected widget/node
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        //holding current mainStage
        mainStage = window;

        window.setScene(methodScene);
        window.show();
    }

    /*if you need to add a pop-up window for whatever reason, do so by passing in the actionEvent trigger and then the page
    name you want to pop-up*/
    public static void popUpNewScene(ActionEvent actionEvent, String fileName) throws IOException {
        Parent popUpParent = FXMLLoader.load(Main.class.getResource(fileName));

        Scene popUpScene = new Scene(popUpParent);

        Stage stage = new Stage();
        stage.setScene(popUpScene);
        //setting pop-up window to have priority over main window. It must be closed before you can interact with main
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }

}
