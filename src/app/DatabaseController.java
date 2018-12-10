package app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.temporal.TemporalAmount;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;


public class DatabaseController implements Initializable {

  ObservableList <Recipe> list = FXCollections.observableArrayList();

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private TableView<Recipe> tableView;
  @FXML
  private TableColumn<Recipe, Integer> RecIdCol;
  @FXML
  private TableColumn<Recipe, String> DishCol;
  @FXML
  private TableColumn<Recipe, String> NatCol;
  @FXML
  private TableColumn<Recipe, String> CourseCol;
  @FXML
  private TableColumn<Recipe, String> StyleCol;
  @FXML
  private TableColumn<Recipe, String> IngCol;
  @FXML
  private Label Status;
  @FXML
  private Label name;
  @FXML
  private ImageView photo;
  @FXML
  private Label ingredients;
  @FXML
  private Label directions;


  static final String DATABASE_URL = "jdbc:derby:lib/RecDatabase";

  @Override
  public void initialize(URL location, ResourceBundle resources) {

      initCol();
      addData();
  }

  // method to add data into tableview
  private void addData() {
    // Outer join is performed to select from merged recipe and category tables
    final String JOIN_RECIPES = "SELECT recipes.recipe_id, recipes.dish, categories.nationality, "
        + "categories.course, categories.cookingStyle, categories.mainIngredient"
        + " FROM recipes INNER JOIN "
        + "categories ON categories.recipe_id=recipes.recipe_id";

    // Driver manager establishes connection with JDBC url
    // Components needed: connection class, driver manager class, and JDBC url
    try (Connection connection = DriverManager.getConnection(DATABASE_URL);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(JOIN_RECIPES)) {

        // Values from Resultset object are added into list
        while(resultSet.next()) {
          int recId = resultSet.getInt("recipe_id");
          String dish = resultSet.getString("dish");
          String nationality = resultSet.getString("nationality");
          String course = resultSet.getString("course");
          String style = resultSet.getString("cookingStyle");
          String mainIngredient = resultSet.getString("mainIngredient");

          list.add(new Recipe(recId, dish,nationality, course, style, mainIngredient));
        }
    }
    catch (SQLException e) {
      Status.setText("Error");
      e.printStackTrace();
    }
    // Associates tableView with list items
    tableView.getItems().setAll(list);
  }




  // function will initialize column values
  private void initCol() {
    RecIdCol.setCellValueFactory(new PropertyValueFactory<>("recId"));
    DishCol.setCellValueFactory(new PropertyValueFactory<>("dish"));
    NatCol.setCellValueFactory(new PropertyValueFactory<>("nationality"));
    CourseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
    StyleCol.setCellValueFactory(new PropertyValueFactory<>("style"));
    IngCol.setCellValueFactory(new PropertyValueFactory<>("mainIngredient"));
  }

  public static class Recipe {
    // column properties are initialized
    private final SimpleIntegerProperty recId;
    private final SimpleStringProperty dish;
    private final SimpleStringProperty nationality;
    private final SimpleStringProperty course;
    private final SimpleStringProperty style;
    private final SimpleStringProperty mainIngredient;

    // Class constructor takes in fields as parameters
    Recipe(int recId, String dish,String nationality,
        String course, String style, String mainIngredient) {
      this.recId = new SimpleIntegerProperty(recId);
      this.dish = new SimpleStringProperty(dish);
      this.nationality = new SimpleStringProperty(nationality);
      this.course = new SimpleStringProperty(course);
      this.style = new SimpleStringProperty(style);
      this.mainIngredient = new SimpleStringProperty(mainIngredient);

    }

    public Integer getRecId() {
      return recId.get();
    }

    public String getDish() {
      return dish.get();
    }


    public String getNationality() {
      return nationality.get();
    }

    public String getCourse() {
      return course.get();
    }

    public String getStyle() {
      return style.get();
    }

    public String getMainIngredient() {
      return mainIngredient.get();
    }

  }
  // method retrieves image url, ingredient list, directions and displays items
  public void display() {
    // Selected item is retrieved and placed into object
    Recipe recipe = tableView.getSelectionModel().getSelectedItem();
    // name value is displayed
    name.setText(recipe.getDish());
    String ing = null;
    String dir = null;
    String image = null;

    final String JOIN_RECIPES1 = "select recipes.recipe_id, "
        + "recipes.imageURL, Ingredients.ingredients, Ingredients.directions"
        + " from recipes inner join ingredients on ingredients.recipe_id=recipes.recipe_id"
        + " where recipes.recipe_id="+recipe.getRecId();

    try (Connection connection = DriverManager.getConnection(DATABASE_URL);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(JOIN_RECIPES1)) {

      // Values from Resultset object are added set to vakues
      while (resultSet.next()) {
        ing = resultSet.getString("ingredients");
        dir = resultSet.getString("directions");
        image = resultSet.getString("imageURL");
      }
      // Labels and images are set
      ingredients.setText(ing);
      directions.setText(dir);
      Image image1 = new Image("file:"+image);
      photo.setImage(image1);

    } 
    catch (SQLException e) {
      Status.setText("Error");
      e.printStackTrace();
    }

  }

  public void remRecipe(ActionEvent event) throws Exception {

    Parent remRecipe = FXMLLoader.load(getClass().getResource("RemoveRecipe.fxml"));
    Scene RemRecipe = new Scene(remRecipe);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(RemRecipe);
    window.show();

  }

  public void dash(ActionEvent event) throws Exception {

    Parent Dash = FXMLLoader.load(getClass().getResource("Dash.fxml"));
    Scene dash = new Scene(Dash);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dash);
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
