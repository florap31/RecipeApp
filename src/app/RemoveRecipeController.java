package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

public class RemoveRecipeController {

  @FXML
  private Label Status;
  @FXML
  private TextField recipeID;


  static final String DATABASE_URL = "jdbc:derby:/Users/florapierre/IdeaProjects/RecipeApp/lib/RecDatabase";

  public void remove_Recipe(ActionEvent event) throws Exception {
    try (Connection connection = DriverManager.getConnection(DATABASE_URL);
        Statement statement = connection.createStatement()) {
    // checks if necessary text field is entered and if not, throws error message
    if (recipeID.getText().isEmpty()) {
      Status.setText("Please enter the recipe ID.");
      return;
    }
      int recipe_id = Integer.parseInt(recipeID.getText());

      final String DELETE_RECIPES = "DELETE FROM RECIPES WHERE recipe_id = " + recipe_id;
      final String DELETE_INGREDIENTS = "DELETE FROM INGREDIENTS WHERE recipe_id = " + recipe_id;
      final String DELETE_CATEGORIES = "DELETE FROM CATEGORIES WHERE recipe_id = " + recipe_id;




      statement.executeUpdate(DELETE_CATEGORIES);
      statement.executeUpdate(DELETE_INGREDIENTS);
      statement.executeUpdate(DELETE_RECIPES);

      Status.setText("Recipe has been deleted.");
    }
    catch (NumberFormatException z) {
      Status.setText("Please enter a number.");
    }
    catch (SQLException e) {
      Status.setText("Error");
      e.printStackTrace();
    }
  }

  public void addRecipe(ActionEvent event) throws Exception {

    Parent addRecipe = FXMLLoader.load(getClass().getResource("RemoveRecipe.fxml"));
    Scene AddRecipe = new Scene(addRecipe);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(AddRecipe);
    window.show();

  }


  public void database(ActionEvent event) throws Exception {

    Parent Database = FXMLLoader.load(getClass().getResource("Database.fxml"));
    Scene database = new Scene(Database);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(database);
    window.show();

  }
  public void dash(ActionEvent event) throws Exception {

    Parent Dash = FXMLLoader.load(getClass().getResource("Dash.fxml"));
    Scene dash = new Scene(Dash);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dash);
    window.show();

  }

}

