public class ShakeBuilder {
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