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

class ShakeBuilder {
    private Shake shake = new Shake();

    void setName(String name) {
        shake.setName(name);

    }

    void setPrice(float Price) {
        shake.setBasePrice(Price);
    }

    void addMilk() {
        if(!shake.getIngredients().contains("Almond Milk")){
            shake.addIngredient("Regular Milk");
        }

    }

    void addSugar() {
        shake.addIngredient("Sugar");
    }

    void addAdditionalIngredients(String... ingredients) {
        for (String ingredient : ingredients) {
            shake.addIngredient(ingredient);
        }
    }

    void makeShakeLactoseFree() {
        shake.getIngredients().add("Almond Milk");
        shake.setExtraCost(shake.getExtraCost()+60);
    }
    void addCandyOnTop(){
        shake.getIngredients().add("Candy");
        shake.setExtraCost(shake.getExtraCost()+50);
    }
    void addCookiesOnTop(){
        shake.getIngredients().add("Cookies");
        shake.setExtraCost(shake.getExtraCost()+40);
    }
    Shake getShake() {
        return shake;
    }
}

class ShakeDirtector {
    ShakeBuilder shakeBuilder;

    ShakeDirtector(ShakeBuilder shakeBuilder) {
        this.shakeBuilder = shakeBuilder;
    }

    Shake ChocolateShake() {
        shakeBuilder.setName("Chocolate Shake");
        shakeBuilder.setPrice(230F);
        shakeBuilder.addMilk();
        shakeBuilder.addSugar();
        shakeBuilder.addAdditionalIngredients("Chocolate syrup", "Chocolate ice cream");
        return shakeBuilder.getShake();
    }

    Shake CoffeeShake() {
        shakeBuilder.setName("Coffee Shake");
        shakeBuilder.setPrice(250F);
        shakeBuilder.addMilk();
        shakeBuilder.addSugar();
        shakeBuilder.addAdditionalIngredients("Coffee", "Jello");
        return shakeBuilder.getShake();
    }

    Shake StrawberryShake() {
        shakeBuilder.setName("Strawberry Shake");
        shakeBuilder.setPrice(200F);
        shakeBuilder.addMilk();
        shakeBuilder.addSugar();
        shakeBuilder.addAdditionalIngredients("Strawberry syrup", "Strawberry ice cream");
        return shakeBuilder.getShake();
    }

    Shake VanillaShake() {
        shakeBuilder.setName("Vanilla Shake");
        shakeBuilder.setPrice(190F);
        shakeBuilder.addMilk();
        shakeBuilder.addSugar();
        shakeBuilder.addAdditionalIngredients("Vanilla Flavoring", "Jello");
        return shakeBuilder.getShake();
    }

    Shake ZeroShake() {
        shakeBuilder.setName("Zero Shake");
        shakeBuilder.setPrice(240F);
        shakeBuilder.addMilk();
        shakeBuilder.addAdditionalIngredients("Sweetener", "Vanilla Flavoring", "Sugar-Free Jello");
        return shakeBuilder.getShake();
    }

}




