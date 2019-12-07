package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private Pane moving;
    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    @FXML
    private Button back2home;
    
    @FXML
    private Label label;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        dungeon.setEnemyObserver();
        dungeon.setDoorObserver();
    }
    
    @FXML
	void goBack2home(ActionEvent event) throws IOException {
		go2homeScene();
	}

    private void go2homeScene() throws IOException {
    	Stage primaryStage = (Stage) back2home.getScene().getWindow();
    	HomepageController hc = new HomepageController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
        loader.setController(hc);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
	}
    
    
    private void go2faildScene() throws IOException {
    	Stage primaryStage = (Stage) back2home.getScene().getWindow();
    	FailedpageController fc = new FailedpageController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("failedPage.fxml"));
        loader.setController(fc);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
	}
    
    private void go2winScene() throws IOException {
    	Stage primaryStage = (Stage) back2home.getScene().getWindow();
    	FailedpageController fc = new FailedpageController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WinPage.fxml"));
        loader.setController(fc);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
	}
    
    

    
	@FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

       //Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
            	ImageView g = new ImageView(ground);
            	g.setId("ground");
            	squares.add(g, x, y);
            }
        }
        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        label.setText(label.getText() + "\n" + dungeon.getGameGuide());
    }
	
	private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
	    for (Node node : gridPane.getChildren()) {
	        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	        	if(! node.getId().equals("player") && ! node.getId().equals("opend door") && ! node.getId().equals("ground"))
	        		return node;
	        }
	    }
	    return null;
	}
	
	private void doThings2node() {
		dungeon.checkPortal();
		if(dungeon.hasEnemy() && ! player.hasInvincibility()) {
			player.notifyEnemy();
		}
		Node node = getNodeFromGridPane(squares, player.getX(), player.getY());
		if(node != null) {
			if(node.getId().equals("sword") || node.getId().equals("treasure") || node.getId().equals("key") ||
			   node.getId().equals("invincibility")) {
				if(player.pickUp()) {
					squares.getChildren().remove(node);
				}
				if(node.getId().equals("invincibility")) {
					player.useInvincibility();
				}
			}
			if(node.getId().equals("enemy") && player.battleEnemy()) {
					squares.getChildren().remove(node);
			}
			if(node.getId().equals("door") && player.openDoor()) {
				squares.getChildren().remove(node);
				ImageView view = new ImageView(new Image("/open_door.png"));
		        view.setId("opend door");
				squares.add(view, player.getX(), player.getY());
			}
		}
		if(dungeon.checkgoal()) {
			try {
				go2winScene();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(! player.checkAlive()) {
			try {
				go2faildScene();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
        	if(dungeon.canGoThere(player.getX(), player.getY()-1, player.getX(), player.getY()-2)) {
        		Entity boulder = dungeon.findEntity(player.getX(), player.getY()-1);
        		if(boulder instanceof Boulder) {
        			player.pushUp(boulder);
        			player.moveUp();
        		}else {
        			player.moveUp();
        		}
        		doThings2node();
        	}
        	break;
        case DOWN:
        	if(dungeon.canGoThere(player.getX(), player.getY()+1, player.getX(), player.getY()+2)) {
        		Entity boulder = dungeon.findEntity(player.getX(), player.getY()+1);
        		if(boulder instanceof Boulder) {
        			player.pushDown(boulder);
        			player.moveDown();
        		}else {
        			player.moveDown();
        		}
        		doThings2node();
        	}
        	break;
        case LEFT:
        	if(dungeon.canGoThere(player.getX()-1, player.getY(), player.getX()-2, player.getY() )) {
        		Entity boulder = dungeon.findEntity(player.getX()-1, player.getY());
        		if(boulder instanceof Boulder) {
        			player.pushLeft(boulder);
        			player.moveLeft();
        		}else {
        			player.moveLeft();
        		}
        		doThings2node();
        	}
            break;
        case RIGHT:
        	if(dungeon.canGoThere(player.getX()+1, player.getY(), player.getX()+2, player.getY())){
        		Entity boulder = dungeon.findEntity(player.getX()+1, player.getY());
        		if(boulder instanceof Boulder) {
        			player.pushRight(boulder);
        			player.moveRight();
        		}else {
        			player.moveRight();
        		}
        		doThings2node();
        	}
            break;
        default:
            break;
        }
    }

}

