package Tai_Xiu_Deeee_Ban_eeee;

import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Play_Controller{

	private static Stage stage;
	
    @FXML
    private Button btnRoll;

    @FXML
    private ImageView imageBowl, imageDice;
    Image bowl = new Image(getClass().getResourceAsStream("bowl.jpg"));
    Image image1 = new Image(getClass().getResourceAsStream("side1.png"));
    Image image2 = new Image(getClass().getResourceAsStream("side2.png"));
    Image image3 = new Image(getClass().getResourceAsStream("side3.png"));
    Image image4 = new Image(getClass().getResourceAsStream("side4.png"));
    Image image5 = new Image(getClass().getResourceAsStream("side5.png"));
    Image image6 = new Image(getClass().getResourceAsStream("side6.png"));

    @FXML
    private Label nextPlayerLabel;

    @FXML
    private Label player1, player2, player3, player4, playerScore1, playerScore2, playerScore3, playerScore4;
    static int turn, side;
    static int[] scores = {0, 0, 0, 0};
    static boolean openable = false, gameOver = false, bot = false;
    static String winner;
//    set name
    public void displayPlayerNames(Player[] players) {
    	player1.setText(players[0].getName());
    	player2.setText(players[1].getName());
    	player3.setText(players[2].getName());
    	player4.setText(players[3].getName());
    }
//    update scores
    void setScore(int player) {
    	switch (player) {
    	case 0:
    		playerScore1.setText("Score: " + Main.Players[player].getScore());
    		break;
    	case 1:
    		playerScore2.setText("Score: " + Main.Players[player].getScore());
    		break;
    	case 2:
    		playerScore3.setText("Score: " + Main.Players[player].getScore());
    		break;
    	case 3:
    		playerScore4.setText("Score: " + Main.Players[player].getScore());
    		break;
    	}
    }
	
//    take a random dice and roll it
    void randDice() {
    	Random rand = new Random();
		Dicess dice = new Dicess(rand.nextInt(4));
		side = dice.rollDice();
		switch(side) {
		case 1:
			imageDice.setImage(image1);
			break;
		case 2:
			imageDice.setImage(image2);
			break;
		case 3:
			imageDice.setImage(image3);
			break;
		case 4:
			imageDice.setImage(image4);
			break;
		case 5:
			imageDice.setImage(image5);
			break;
		case 6:
			imageDice.setImage(image6);
			break;
		}
    }

//   the shake bowl animation
	void shake_bowl() {
		TranslateTransition translate = new TranslateTransition(Duration.millis(100),imageBowl);
		translate.setInterpolator(Interpolator.LINEAR);
		translate.setCycleCount(6);
		translate.setFromY(imageDice.getY());
		translate.setToY(imageDice.getY()-25);
		translate.setAutoReverse(true);
		translate.setDelay(Duration.seconds(0.5));
		translate.play();
		
		translate.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                randDice();
                if (bot)
                	take_bowl();
                else 
                	openable = true;
            }
        });
	}
//	the take bowl animation
	void take_bowl() {
			TranslateTransition translate = new TranslateTransition(Duration.millis(300),imageBowl);
			translate.setInterpolator(Interpolator.LINEAR);
			translate.setFromY(imageDice.getY());
			translate.setToY(imageDice.getY()-500);
			translate.setAutoReverse(true);
			translate.setDelay(Duration.seconds(0.5));

			FadeTransition fade =  new FadeTransition(Duration.millis(600), imageBowl);
			fade.setInterpolator(Interpolator.LINEAR);
			fade.setCycleCount(2);
			fade.setFromValue(1);
			fade.setToValue(0);
			fade.setAutoReverse(true);
			fade.setDelay(Duration.seconds(0.5));
			
			ParallelTransition par = new ParallelTransition(translate, fade);
			par.play();
			
			translate.setOnFinished(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	imageBowl.setVisible(false);
					imageDice.setVisible(true);
					scale_dice();
	            }
	        });
	}

//	the scale dice animation
	void scale_dice() {
		ScaleTransition translate = new ScaleTransition(Duration.millis(200),imageDice);
		translate.setInterpolator(Interpolator.LINEAR);
		translate.setCycleCount(6);
		translate.setFromX(0.8);
		translate.setFromY(0.8);
		translate.setToX(1.20);
		translate.setToY(1.20);
		translate.setAutoReverse(true);
		translate.setDelay(Duration.seconds(0.5));
		translate.play();
		
		translate.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	imageDice.setVisible(false);
				imageBowl.setVisible(true);
				openable = false;
				scores[turn]+= side;
				scores[turn] = scores[turn] > 21 ? 0 : scores[turn];
				Main.Players[turn].setScore(scores[turn]);
				setScore(turn);
				
				if (scores[turn]==21) {
					gameOver = true;
					winner = Main.Players[turn].getName();
				}
				turn = (turn+1)%4;
				nextPlayerLabel.setText(Main.Players[turn].getName()+ "'s turn. Next player: " + Main.Players[(turn+1)%4].getName());
					
				Run();	
				
            }
        });
	}
//	mouse event open bowl
    @FXML
    void openBowl(MouseEvent event) {
    	if (openable) 
    		take_bowl();
    }
//	-----------------------------------------------------------------
	@FXML
	void rollDice(ActionEvent event) {
		
		if (bot)
			return;
		if (btnRoll.getText().equals("START")) {
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			
	    	Random rand = new Random();
	    	turn = rand.nextInt(4);
	    		
	   		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Information Dialog");
	    	alert.setHeaderText("The referee announces that:");
	    	alert.setContentText("Rule: there are 4 players. When it's turn, the player randomly receive a dice then roll it. Whoever gets 21 points first will be the winner. However, if the score is greater than 21, it will be back to 0.\n The firts player is "+ Main.Players[turn].getName());
	    	alert.showAndWait();
	    		
	    	btnRoll.setText("ROLL");
	    	nextPlayerLabel.setText(Main.Players[turn].getName()+ "'s turn. Next player: " + Main.Players[(turn+1)%4].getName());
	    		
	    	Run();
	    		
	    } else if (!bot)
	    	shake_bowl();    	
	}
//	 -----------------------------------------------------------------
	void Run() {
		if (gameOver) {
			gameOver();
			return;
		}
		if (Main.Players[turn] instanceof VirtualPlayer) {
			btnRoll.setDisable(true);
			bot = true;
			shake_bowl();
			
		} else {
			btnRoll.setDisable(false);
			bot = false;
		}
	}

//	-----------------------------------------------------------------
	void gameOver() {
		
		if (Main.Players[0] instanceof VirtualPlayer && (!Main.Players[0].getName().equals(winner)) )
			player1.setText("Bot 1: " + Main.Players[0].toString());
		if (Main.Players[1] instanceof VirtualPlayer && (!Main.Players[1].getName().equals(winner)) )
			player2.setText("Bot 2: " + Main.Players[1].toString());
		if (Main.Players[2] instanceof VirtualPlayer && (!Main.Players[2].getName().equals(winner)) )
			player3.setText("Bot 3: " + Main.Players[2].toString());
		if (Main.Players[3] instanceof VirtualPlayer && (!Main.Players[3].getName().equals(winner)) )
			player4.setText("Bot 4: " + Main.Players[3].toString());

		
		Platform.runLater(() -> {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Game Over");
			alert.setContentText(winner + " win. Play again?");
			
			if(alert.showAndWait().get()==ButtonType.OK) {
				resetGame();
			} else {
				Main.exit(stage);
			}
		});
		
	}
//	------------------------------------------------------------------
	void resetGame(){
		displayPlayerNames(Main.Players);
		for (int i =0; i<4 ; i++) {
			scores[i] = 0;
			Main.Players[i].setScore(0);
			setScore(i);
		}
		nextPlayerLabel.setText("Click the button below to start");
		btnRoll.setText("START");
        btnRoll.setDisable(false);
        bot = false;
        gameOver = false;
        openable = false;
        imageDice.setVisible(false);
        imageBowl.setVisible(true);
        imageBowl.setImage(bowl);
	}
	
}
