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

public class AddRecipe {

    public static void launch(){

        VBox box = new VBox();

        Stage stage = new Stage();
        Scene scene = new Scene(box);
        stage.setScene(scene);

        HBox name = new HBox();
        HBox component = new HBox();
        HBox component11 = new HBox();

        Label Nazwa = new Label("Wprowadz nazwe: ");
        TextField nazwa = new TextField();

        name.getChildren().addAll(Nazwa , nazwa);

        Label Component = new Label("Add component: ");
        Label weig = new Label("Weight: ");

        TextField nazwaComp = new TextField();
        TextField weight = new TextField();
        ComboBox comboBox = new ComboBox();

        //================================

        Label Component1 = new Label("Add component: ");
        Label weig1 = new Label("Weight: ");

        TextField nazwaComp1 = new TextField();
        TextField weight1 = new TextField();

        ComboBox comboBox1 = new ComboBox();

        String[] tastes = getTastes();

        for (String str : tastes){
            comboBox.getItems().add(str);
            comboBox1.getItems().add(str);
        }

        component.getChildren().addAll(Component , nazwaComp , weig  , weight , comboBox);
        component11.getChildren().addAll(Component1 , nazwaComp1 , weig  , weight1 , comboBox1);

        final int[] selectedIndex = new int[1];
        final int[] selectedIndex1 = new int[1];

        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                selectedIndex[0] = comboBox.getSelectionModel().getSelectedIndex();
            }
        });

        comboBox1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                selectedIndex1[0] = comboBox.getSelectionModel().getSelectedIndex();
            }
        });



        ArrayList<Recipe> recipes = getRecipes();


        Button save = new Button("Save");

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                ArrayList<com.example.project3.Parts.Component> components = new ArrayList<>();


                com.example.project3.Parts.Component component1= new Component(nazwaComp.getText() , selectedIndex[0] ,parseInt(weight.getText()) );
                com.example.project3.Parts.Component component11= new Component(nazwaComp1.getText() , selectedIndex1[0] ,parseInt(weight1.getText()) );


                components.add(component1);
                components.add(component11);

                Recipe recipe = new Recipe(nazwa.getText() , components);

                recipes.add(recipe);
                Recipe.setRecipes(recipes);


                FileWriter fw = new FileWriter();

            }
        });

        box.getChildren().addAll(name , component , component11 , save);

        stage.show();
    }

}
