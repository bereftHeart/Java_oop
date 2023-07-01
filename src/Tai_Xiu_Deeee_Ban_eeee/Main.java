package Tai_Xiu_Deeee_Ban_eeee;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = FXMLLoader.load(getClass().getResource("Start_Screen.fxml"));
			
			Image bgimage = new Image(getClass().getResourceAsStream("background.jpg"));
			BackgroundImage background = new BackgroundImage(bgimage, null, null, null, null);
			Background bgObject = new Background(background);
		    root.setBackground(bgObject);
			
		    Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(event -> {
				event.consume();
				exit(primaryStage);
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Player[] Players = new Player[4];
	
	public static void exit(Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("You're about to exit");
		alert.setContentText("Quit game?");
		
		if(alert.showAndWait().get()==ButtonType.OK) {
			stage.close();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
