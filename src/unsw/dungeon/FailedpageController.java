package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FailedpageController {
	@FXML
	private Button gohome;
	
	@FXML
	private void go2homeScene(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) gohome.getScene().getWindow();
    	HomepageController hc = new HomepageController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
        loader.setController(hc);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}
