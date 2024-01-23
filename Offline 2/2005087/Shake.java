import java.util.ArrayList;
import java.util.List;

class Shake {
    private String name;
    private float basePrice;
    private float extraCost=0F;
    private List<String> ingredients = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public float getExtraCost() {
        return extraCost;
    }

    public void setExtraCost(float extraCost) {
        this.extraCost = extraCost;
    }

    void printDetails() {
        System.out.println("Shake: " + name);
        System.out.printf("Ingredients: " );
        for(String s:ingredients){
            System.out.printf(s+" ");
        }
        System.out.println();

        System.out.println("Base Price: Tk " + basePrice);
        if(ingredients.contains("Almond Milk")){
            System.out.println("60 is added for Almond Milk");
        }
        if(ingredients.contains("Candy")){
            System.out.println("50 is added for Candy");
        }
        if(ingredients.contains("Cookies")){
            System.out.println("40 is added for Cookies");
        }
        float totalCost=basePrice+extraCost;
        System.out.println("Total Cost:"+totalCost);
    }

    void addIngredient(String newIngredient) {
        ingredients.add(newIngredient);
    }

}
