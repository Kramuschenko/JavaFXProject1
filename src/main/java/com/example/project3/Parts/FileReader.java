package com.example.project3.Parts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.project3.Parts.Recipe.setRecipes;
import static java.lang.Integer.parseInt;

public class FileReader {

    static ArrayList<Recipe> recipes = new ArrayList<>();

    public FileReader(){

        String path = "./src/main/java/com/example/project3/Parts/Recipes.txt";

        File file = new File(path);

        try {
            Scanner scanner = new Scanner(file);
            int i =0;
            while(scanner.hasNextLine()){

                String line = scanner.nextLine();
                String[] data = line.split(";");

                String[] ingredients = data[1].split("@");

                ArrayList<Component> components = new ArrayList<>();

//                System.out.println();
//                System.out.println(data[0]);
//                System.out.println();

                for (String ingredientsTMP : ingredients){
                    String[] ingredient = ingredientsTMP.split("-");

//                    System.out.print("Ingridients: ");
//                    for (String tmp : ingredient){
//                        System.out.print(tmp + " ");
//                    }

                    Component component = new Component(ingredient[0] , parseInt(ingredient[2]) , parseInt(ingredient[1]));

                    components.add(component);

                }

                Recipe recipe = new Recipe(data[0] , components);

                recipes.add(recipe);
            }


            setRecipes(recipes);

            scanner.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
