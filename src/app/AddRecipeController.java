package app;



import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

public class AddRecipeController implements Initializable {

  List<String> filesList;

  @FXML
  private TextField recipe;
  @FXML
  private TextField ingredients;
  @FXML
  private TextField directions;
  @FXML
  private TextField course;
  @FXML
  private TextField region;
  @FXML
  private TextField style;
  @FXML
  private TextField mainIngredient;
  @FXML
  private Label status;
  @FXML
  private Label filePath;

  final String DATABASE_URL = "jdbc:derby:/Users/florapierre/IdeaProjects/RecipeApp/lib/RecDatabase";
  String imageUrl;
  // Data from recipes database is retrieved
  public void addRecipe(ActionEvent event) throws Exception {
    int recipe_id = 0;
    // Selects total number of rows from recipes table in database
    final String GET_ROWS = "SELECT COUNT(*) FROM recipes";
    try(Connection conn = DriverManager.getConnection(DATABASE_URL);
    Statement stmt = conn.createStatement()) {
      ResultSet resultSet = stmt.executeQuery(GET_ROWS);
      // Retrieves number of rows in table so recipe id is set
      resultSet.next();
      recipe_id = resultSet.getInt(1) + 1;
    }
    catch (SQLException e) {
      status.setText("Error");
      e.printStackTrace();
    }
    // assigns values to strings if certain values are entered
    String recipeName = recipe.getText();
    String recipeIngredients = ingredients.getText();
    String recipeDirections = directions.getText();
    String recipeCourse = course.getText();
    String recipeRegion = region.getText();
    String recipeStyle = style.getText();
    String recipeMainIngredient = mainIngredient.getText();

    // checks if necessary text fields are entered and if not, throws error message
    if (recipeName.isEmpty() || recipeIngredients.isEmpty() || recipeDirections.isEmpty()
        || recipeCourse.isEmpty()) {
      status.setText("Please enter values for required fields.");
      return;
    }



    // values are inserted into recipes table
    final String INSERT_RECIPES = "INSERT INTO RECIPES VALUES(" +
        recipe_id + "," +
        "'" + recipeName + "'," +
        "'" + imageUrl + "'" + ")";
    final String INSERT_INGREDIENTS = "INSERT INTO INGREDIENTS VALUES(" +
        recipe_id + "," +
        recipe_id + "," +
        "'" + recipeDirections + "'," +
        "'" + recipeIngredients + "'" + ")";
    final String INSERT_CATEGORIES = "INSERT INTO CATEGORIES VALUES(" +
        recipe_id + "," +
        recipe_id + "," +
         getRegionString(recipeRegion) +
        "'" + recipeCourse + "'," +
        getStyleString(recipeStyle) +
        getMainIngredientString(recipeMainIngredient) + ")";

    try (Connection connection = DriverManager.getConnection(DATABASE_URL);
        Statement statement = connection.createStatement()) {
      statement.executeUpdate(INSERT_RECIPES);
      statement.executeUpdate(INSERT_INGREDIENTS);
      statement.executeUpdate(INSERT_CATEGORIES);
      status.setText("Success!");
    }
    catch(DerbySQLIntegrityConstraintViolationException y){
      status.setText("Recipe already exists");
    }
    catch (SQLException e) {
      status.setText("Error");
      e.printStackTrace();
    }

  }
  // Methods if particular values are null or not and
  // returns string needed for SQL insert statement
  String getRegionString(String region) {
  if(!(region.isEmpty()))
      return "'" + region + "',";
  return null;
  }

  String getStyleString(String style) {
    if(!(style.isEmpty()))
      return "'" + style + "',";
    return null;
  }

  String getMainIngredientString(String MainIngredient) {
    if(!(MainIngredient.isEmpty()))
      return "'" + MainIngredient + "'";
    return null;
  }

  public void ImageChooser(ActionEvent event) throws Exception {
    // FileChooser object is created and extention filters are added
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new ExtensionFilter("Image files", filesList));
    // File object is created, which allows user to choose image
    File file = fileChooser.showOpenDialog(null);
    // Path of chosen file is set to imageUrl string
    this.imageUrl = file.getAbsolutePath();
    if(file != null){
        filePath.setText("Selected image:" + imageUrl);
    }
    else
      filePath.setText("Please choose an image.");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // Arraylist for file extensions specified for ExtentionFilter
    filesList = new ArrayList<>();
    filesList.add("*.jpeg");
    filesList.add("*.jpg");

  }

  public void remRecipe(ActionEvent event) throws Exception {

    Parent remRecipe = FXMLLoader.load(getClass().getResource("RemoveRecipe.fxml"));
    Scene RemRecipe = new Scene(remRecipe);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(RemRecipe);
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