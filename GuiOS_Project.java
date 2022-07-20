/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guios_project;

/**
 *
 * @author Zyad
 */
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Zyad
 */
public class GuiOS_Project extends Application {
@Override
    public void start(Stage primaryStage) throws Exception {

        Parent root =FXMLLoader.load(getClass().getResource("GuiOS.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("OS Schedulers");
        primaryStage.show();
    }
    public static void main(String[] args) {
        //imp 
       launch(args);
    }
}