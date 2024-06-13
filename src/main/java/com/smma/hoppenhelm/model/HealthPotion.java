package com.smma.hoppenhelm.model;
import javafx.scene.image.ImageView;
public class HealthPotion implements Consumable,IDrawable {
    private final int healthGainAmount;
    private final String imagePath = "visuals/Potion_of_Healing.webp";
    public HealthPotion(int healthGainAmount) {
        this.healthGainAmount = healthGainAmount;
    }

    public int getHealthGainAmount() {
        return healthGainAmount;
    }
    @Override
    public void Draw(Scene scene){
        
    }
}