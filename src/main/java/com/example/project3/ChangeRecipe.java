package com.example.project3;

import com.example.project3.Parts.Component;
import com.example.project3.Parts.FileWriter;
import com.example.project3.Parts.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.example.project3.MainFrame.getCurrentRecipe;
import static com.example.project3.Parts.Component.getTastes;
import static com.example.project3.Parts.Recipe.getRecipes;
import static java.lang.Integer.parseInt;

public class ChangeRecipe {


    static Recipe currentReciepe = getCurrentRecipe();

    public static void launch(){

        VBox box = new VBox();

        Stage stage = new Stage();
        Scene scene = new Scene(box);
        stage.setScene(scene);

        HBox name = new HBox();
        HBox component = new HBox();


        Label Nazwa = new Label("Change name: ");
        TextField nazwa = new TextField(currentReciepe.getName());

        name.getChildren().addAll(Nazwa , nazwa);

        Label Component = new Label("Add component: ");
        Label weig = new Label("Weight: ");

        TextField nazwaComp = new TextField();
        TextField weight = new TextField();

        ComboBox comboBox = new ComboBox();

        String[] tastes = getTastes();

        for (String str : tastes){
            comboBox.getItems().add(str);
        }

        component.getChildren().addAll(Component , nazwaComp , weig  , weight , comboBox);

        final int[] selectedIndex = new int[1];

        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                selectedIndex[0] = comboBox.getSelectionModel().getSelectedIndex();
            }
        });



        ArrayList<Recipe> recipes = getRecipes();


        Button save = new Button("Save");

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                ArrayList<com.example.project3.Parts.Component> components = new ArrayList<>();

                ArrayList<com.example.project3.Parts.Component> componentsCurrent = currentReciepe.getComponents();

                for (Component component1 : componentsCurrent){
                    components.add(component1);
                }

                com.example.project3.Parts.Component component1= new Component(nazwaComp.getText() , selectedIndex[0] ,parseInt(weight.getText()) );

                components.add(component1);

                Recipe recipe = new Recipe(nazwa.getText() , components);

                recipes.remove(currentReciepe);
                recipes.add(recipe);
                Recipe.setRecipes(recipes);


                FileWriter fw = new FileWriter();

            }
        });

        box.getChildren().addAll(name , component , save);

        stage.show();
    }

}
