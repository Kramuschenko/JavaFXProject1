package com.example.project3.Parts;

import java.util.ArrayList;

public class Recipe {

    private String name ;
    private ArrayList<Component> components;
    static ArrayList<Recipe> recipes = new ArrayList<>();

    public static ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", components=" + components +
                '}';
    }

    public static void setRecipes(ArrayList<Recipe> recipes) {
        Recipe.recipes = recipes;
    }

    public Recipe(String name, ArrayList<Component> components) {
        this.name = name;
        this.components = components;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    public String getComment(){

        int countSweet = 0 , countSalte = 0 , countBitter = 0 , countSour = 0 , countNeutral = 0;

        for (Component component : components){
            int tmp = component.getTaste();

            switch (tmp){
                case 0 -> countSweet++;
                case 1 -> countSalte++;
                case 2 -> countBitter++;
                case 3 -> countSour++;
                case 4 -> countNeutral++;
            }
        }

        int size  = components.size();

        countSweet = (countSweet * 100 / size);
        countSalte = (countSalte * 100 / size);
        countBitter = (countBitter * 100 / size);
        countSour = (countSour * 100 / size);
        countNeutral = (countNeutral * 100 / size);

        String comment = "";

        if (countSweet >= 50)
            comment = "To danie jest tak słodkie,\n że nadaje się na deser!";
        else if (countSalte >= 50)
            comment = "To danie jest tak słone,\n że nadaje się jako danie glowne!";
        else if (countBitter >= 50)
            comment = "To danie jest tak gorzkie,\n że nadaje się jako przekąska!";
        else if (countSour >= 50)
            comment = "To danie jest tak kwasne,\n że nadaje się jako apperetive!";
        else if (countNeutral >= 50)
            comment = "To danie jest neutralne ,\n że nadaje się jako\n danie glowne lub apperetive!";
        else if (countSour >=5 && countBitter >= 5 && countSalte >= 5 && countNeutral >= 5 && countSweet >= 5)
            comment = "To danie jest tak złe\n że aż ciężko na nie patrzeć!";


        return comment;

    }
}
