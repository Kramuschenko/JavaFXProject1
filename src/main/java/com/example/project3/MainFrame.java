package com.example.project3;

import com.example.project3.Parts.Component;
import com.example.project3.Parts.FileReader;
import com.example.project3.Parts.FileWriter;
import com.example.project3.Parts.Recipe;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.project3.Parts.Recipe.getRecipes;

public class MainFrame extends Application {

    static Recipe currentRecipe;
    static BorderPane bp = new BorderPane();
    ArrayList<Recipe> recipes = getRecipes();

    public void setRecipesMenu(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public static Recipe getCurrentRecipe() {
        return currentRecipe;
    }

    public static void setCurrentRecipe(Recipe currentRecipe) {
        MainFrame.currentRecipe = currentRecipe;
    }

    @Override
    public void start(Stage stage) throws IOException {



        HBox main = new HBox();

        Menu menu = new Menu("Options");

        MenuItem menu1 = new MenuItem("Create recipe");
        MenuItem menu2 = new MenuItem("Edit recipe");
        MenuItem menu3 = new MenuItem("Refresh");

        MenuBar menuBar = new MenuBar();

        menu.getItems().addAll(menu1 , menu2 , menu3);



        menu1.setOnAction(e -> {
            AddRecipe.launch();
        });

        menu2.setOnAction(e -> {
            ChangeRecipe.launch();
        });



        menuBar.getMenus().add(menu);

        ListView listView = new ListView();

        Label Nazwa = new Label();
        Nazwa.setFont(new Font("Arial", 40));

        Label components = new Label();
        components.setFont(new Font("Arial", 24));
        Label comment = new Label();
        comment.setFont(new Font("Arial", 20));



        for (Recipe recipe : recipes){
            listView.getItems().add(recipe.getName());
        }

        listView.setOnMouseClicked(new ListViewHandler(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                currentRecipe = recipes.get(listView.getSelectionModel().getSelectedIndex());

                Nazwa.setText(currentRecipe.getName() + '\n' + '\n');

                StringBuilder ingridients = new StringBuilder();

                ArrayList<Component> componentArrayList = currentRecipe.getComponents();

                for (Component comp : componentArrayList){
                    ingridients.append(comp.getName()).append(": ").append(comp.getWeight()).append('\n');
                }

                components.setText(ingridients.toString() + '\n' + '\n');

                comment.setText(currentRecipe.getComment());
            }
        });

        VBox list = new VBox();
        list.getChildren().add(Nazwa);
        list.getChildren().add(components);
        list.getChildren().add(comment);

        bp.setTop(menuBar);





//        bp.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, new CornerRadii(0), Insets.EMPTY)));



        stage.setTitle("Recipes");

        ScrollPane pane = new ScrollPane();
        ScrollPane pane2 = new ScrollPane();
        pane.setContent(listView);
        pane2.setContent(list);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

//        pane2.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, new CornerRadii(0), Insets.EMPTY)));
//        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, new CornerRadii(0), Insets.EMPTY)));
//
//        list.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, new CornerRadii(0), Insets.EMPTY)));

        bp.setLeft(pane);
        bp.setRight(pane2);

        Scene scene = new Scene(bp, 700, 400);
        stage.setScene(scene);
        stage.show();


        menu3.setOnAction(e -> {
            FileReader fileReader = new FileReader();
            stage.close();
            stage.setScene(scene);
            stage.show();

        });

    }

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        FileWriter fileWriter = new FileWriter();
        launch();
    }


    class ListViewHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
        }
    }
}