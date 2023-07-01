package Tai_Xiu_Deeee_Ban_eeee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Input_Controller {

	private Stage stage;
    private Scene scene;
    private Parent root;
	
    @FXML
    private Label nameLabel;

    @FXML
    private TextField input;
    
    static int numberPlayers;
    static int j=0;
    
    @FXML
    void next(ActionEvent event) throws IOException {
    	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Input_Screen.fxml"));
    	
    	if (nameLabel.getText().equals("Enter number of players") || nameLabel.getText().equals("You have not entered anything yet") || nameLabel.getText().equals("The number of players must be an integer") || nameLabel.getText().equals("The number of players must be in range 0-4")) {
    		
    		String numPlayers = input.getText();
//    	exception handling the user has not entered anything.
    		if (numPlayers.isEmpty()) {
    			nameLabel.setText("You have not entered anything yet");
    			return ;
    		}
//    	exception handling the user has entered wrong format.
    		if (!numPlayers.matches("-?\\d?")) {
    			nameLabel.setText("The number of players must be an integer");
    			return ;
    		}
//    	exception handling the user has entered number out of allowable range.
    		numberPlayers = Integer.parseInt(numPlayers);
    		
    		if (numberPlayers < 0 || numberPlayers > 4) {
    			nameLabel.setText("The number of players must be in range 0-4");
    			return ;
    		}
    		if (numberPlayers!=0) {
    			root = loader.load();
    			Input_Controller input_scene = loader.getController();
    			input_scene.displayLabel("Enter the name of first player");
    			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    			scene = new Scene(root);
    			stage.setScene(scene);
    			stage.setResizable(false);
    			stage.show();
    		} else {
    			List<String> emotions = new ArrayList<String>();
    			emotions.add("No f*k'in way, that's unfair.");
    			emotions.add("What a frick'in disappointment.");
    			emotions.add("Oh sh!t, let me try again.");
    			emotions.add("Looks like things ain't frick'in work out today.");
    			for (int i=numberPlayers; i<4; i++) {
    				Main.Players[i] = new VirtualPlayer("Bot " + (i-numberPlayers+1), emotions.get(i));
    			}
    			
    			loader = new FXMLLoader(getClass().getResource("PlayGround.fxml"));

    			root = loader.load();
    			Play_Controller play_ground = loader.getController(); 
    			play_ground.displayPlayerNames(Main.Players);
    			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    			scene = new Scene(root);
    			stage.setScene(scene);
				stage.setResizable(true);
				stage.setMinHeight(480);
				stage.setMinWidth(640);
    			stage.show();
    		}
    		
//    	------------------------------------------------------------------
    	} else {
    			String name = input.getText();
        		
        		if (name.isEmpty()) {
        			nameLabel.setText("The name cannot be empty");
        			return ;
        		}
        		
    			Main.Players[j] = new Player(name);

    			j++;
    			
    			if (j<numberPlayers) {
    				root = loader.load();
    				Input_Controller input_scene = loader.getController();
    				input_scene.displayLabel("Enter the name of " + (j+1) +"-th player");     
    				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    				scene = new Scene(root);
    				stage.setScene(scene);
    				stage.setResizable(false);
    				stage.show();
    				
    			} else {
    				List<String> emotions = new ArrayList<String>();
    				emotions.add("No f*k'in way, that's unfair.");
    				emotions.add("What a frick'in disappointment.");
    				emotions.add("Oh sh!t, let me try again.");
    				emotions.add("Looks like things ain't frick'in work out today.");
    				for (int i=numberPlayers; i<4; i++) {
    					Main.Players[i] = new VirtualPlayer("Bot "+ (i-numberPlayers+1),emotions.get(i));
    				}
    				
    				loader = new FXMLLoader(getClass().getResource("PlayGround.fxml"));
    				root = loader.load();
    				Play_Controller play_ground = loader.getController(); 
    				play_ground.displayPlayerNames(Main.Players);
    				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    				scene = new Scene(root);
    				stage.setScene(scene);
    				stage.setResizable(true);
    				stage.setMinHeight(480);
    				stage.setMinWidth(640);
    				stage.show();
    			}
    	}
    }
//	-----------------------------------------------------------------
    public void displayLabel(String label) {
    	nameLabel.setText(label);
    }
}
