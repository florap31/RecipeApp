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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

/**
 *
 */
public class AddRecipeController implements Initializable {

  List<String> filesList;

  @FXML
  private TextField recipe;
  @FXML
  private TextArea ingredients;
  @FXML
  private TextArea directions;
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


  static final String DATABASE_URL = "jdbc:derby:lib/RecDatabase";
  String imageUrl;
  String recipeName;
  String recipeIngredients;
  String recipeDirections;
  String recipeCourse;
  String recipeRegion;
  String recipeStyle;
  String recipeMainIngredient;

  /**
   *
   * @param event
   * @throws Exception
   */
  public void imageChooser(ActionEvent event) throws Exception {
    // FileChooser object is created and extention filters are added
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new ExtensionFilter("Image files", filesList));
    // File object is created, which allows user to choose image
    File file = fileChooser.showOpenDialog(null);
    // Path of chosen file is set to imageUrl string

    if (file != null) {
      filePath.setText("Selected image:" + file.getAbsolutePath());
      this.imageUrl = file.getAbsolutePath();
    } else {
      filePath.setText("Please choose an image.");
    }
  }

  /**
   *
   * @param event
   * @throws Exception
   */
  // Data from recipes database is retrieved
  public void addRecipe(ActionEvent event) throws Exception {
    int recipeId = 0;
    // Selects total number of rows from recipes table in database
    final String GET_ROWS = "SELECT COUNT(*) FROM recipes";

    // Driver manager establishes connection with JDBC url
    // Components needed: connection class, driver manager class, and JDBC url
    try (Connection conn = DriverManager.getConnection(DATABASE_URL);
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(GET_ROWS)) {

      // Retrieves number of rows in table so recipe id is set
      resultSet.next();
      recipeId = resultSet.getInt(1) + 1;
    } catch (SQLException e) {
      status.setText("Error");
      e.printStackTrace();
    }
    // assigns values to strings if certain values are entered
    this.recipeName = recipe.getText();
    this.recipeIngredients = ingredients.getText();
    this.recipeDirections = directions.getText();
    this.recipeCourse = course.getText();
    this.recipeRegion = region.getText();
    this.recipeStyle = style.getText();
    this.recipeMainIngredient = mainIngredient.getText();

    // checks if necessary text fields are entered and if not, throws error message
    if (recipeName.isEmpty() || recipeIngredients.isEmpty() || recipeDirections.isEmpty()
        || recipeCourse.isEmpty() || imageUrl == null) {
      status.setText("Please enter values for required fields.");
      return;
    }

    // values are inserted into recipes table
    String INSERT_RECIPES = String.format("INSERT INTO RECIPES VALUES("
        + recipeId + "," +
        "'" + recipeName + "'," +
        "'" + imageUrl + "'" + ")");
    String INSERT_INGREDIENTS = String.format("INSERT INTO INGREDIENTS VALUES(" +
        recipeId + "," +
        recipeId + "," +
        "'" + recipeDirections + "'," +
        "'" + recipeIngredients + "'" + ")");
    String INSERT_CATEGORIES = String.format("INSERT INTO CATEGORIES VALUES("
        + "" + recipeId + ","
        + recipeId + "," +
        getRegionString(recipeRegion) +
        "'" + recipeCourse + "'," +
        getStyleString(recipeStyle) +
        getMainIngredientString(recipeMainIngredient) + ")");

    try (Connection connection = DriverManager.getConnection(DATABASE_URL);
        Statement statement = connection.createStatement()) {
      statement.executeUpdate(INSERT_RECIPES);
      statement.executeUpdate(INSERT_INGREDIENTS);
      statement.executeUpdate(INSERT_CATEGORIES);
      status.setText("Success!");
    } catch (DerbySQLIntegrityConstraintViolationException y) {
      status.setText("Recipe already exists");
    } catch (SQLException e) {
      status.setText("Error");
      e.printStackTrace();
    }

  }

  // Methods checks if particular values are null or not and
  // returns string needed for SQL insert statement
  String getRegionString(String region) {
    if (!(region.isEmpty())) {
      return "'" + region + "',";
    }
    return null + ",";
  }

  String getStyleString(String style) {
    if (!(style.isEmpty())) {
      return "'" + style + "',";
    }
    return null + ",";
  }

  String getMainIngredientString(String MainIngredient) {
    if (!(MainIngredient.isEmpty())) {
      return "'" + MainIngredient + "'";
    }
    return null;
  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // Arraylist for file extensions specified for ExtentionFilter
    filesList = new ArrayList<>();
    filesList.add("*.jpeg");
    filesList.add("*.jpg");
  }

  /**
   *
   * @param event
   * @throws Exception
   */
  public void remRecipe(ActionEvent event) throws Exception {
    Parent remRecipe = FXMLLoader.load(getClass().getResource("RemoveRecipe.fxml"));
    Scene RemRecipe = new Scene(remRecipe);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(RemRecipe);
    window.show();

  }

  /**
   *
   * @param event
   * @throws Exception
   */
  public void database(ActionEvent event) throws Exception {

    Parent Database = FXMLLoader.load(getClass().getResource("Database.fxml"));
    Scene database = new Scene(Database);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(database);
    window.show();

  }

  /**
   *
   * @param event
   * @throws Exception
   */
  public void dash(ActionEvent event) throws Exception {

    Parent Dash = FXMLLoader.load(getClass().getResource("Dash.fxml"));
    Scene dash = new Scene(Dash);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dash);
    window.show();

  }

}