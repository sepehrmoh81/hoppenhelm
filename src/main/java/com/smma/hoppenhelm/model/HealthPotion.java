package com.smma.hoppenhelm.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HealthPotion implements Consumable,IDrawable {
    private final int healthGainAmount;
    private static final String IMAGE_PATH = "Potion_of_Healing.webp";
    public HealthPotion(int healthGainAmount) {
        this.healthGainAmount = healthGainAmount;
    }

    public int getHealthGainAmount() {
        return healthGainAmount;
    }
     @Override
    public ImageView draw(){
       ImageView iv = new ImageView(new Image(IMAGE_PATH));
       iv.setFitWidth(90);
       iv.setFitHeight(90);
       return iv;
    }
}