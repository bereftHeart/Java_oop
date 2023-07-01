package Tai_Xiu_Deeee_Ban_eeee;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start_Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void next(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Input_Screen.fxml"));
    	root = loader.load();
    	
    	Input_Controller input_scene = loader.getController();
    	input_scene.displayLabel("Enter number of players");
    	
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.setResizable(false);
    	stage.show();
        
    }

}
