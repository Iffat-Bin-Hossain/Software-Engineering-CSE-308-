public class ShakeDirector {
    ShakeBuilder shakeBuilder;

    ShakeDirector(ShakeBuilder shakeBuilder) {
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




