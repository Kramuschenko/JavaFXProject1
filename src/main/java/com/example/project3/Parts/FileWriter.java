package com.example.project3.Parts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static com.example.project3.Parts.Recipe.getRecipes;

public class FileWriter {

    public FileWriter(){
        ArrayList<Recipe> recipes = getRecipes();
        String path = "./src/main/java/com/example/project3/Parts/Recipes.txt";

        File file = new File(path);

        try {
            PrintWriter pw = new PrintWriter(file);

            for (Recipe recipe : recipes){
                pw.print(recipe.getName() + ";");
                ArrayList<Component> components = recipe.getComponents();
                for(Component component : components){
                    pw.print(component.getName() + "-" + component.getWeight() + "-" + component.getTaste() + "@");
                }

                pw.println();
            }

            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
