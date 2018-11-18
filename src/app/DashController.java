package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashController {

  public void database(ActionEvent event) throws Exception {

    Parent Database = FXMLLoader.load(getClass().getResource("Database.fxml"));
    Scene database = new Scene(Database);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(database);
    window.show();


  }


  public void addRem(ActionEvent event) throws Exception {

    Parent AddRem = FXMLLoader.load(getClass().getResource("AddRecipe.fxml"));
    Scene addRem = new Scene(AddRem);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(addRem);
    window.show();

  }



}
