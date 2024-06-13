package com.smma.hoppenhelm.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy implements IDrawable {
    private int x;
    private int range;
    private int health;
    private int damage;
    private final String imagePath = "visuals/goomba.png";
    public void attack() {
        // Implement attack logic
    }
    @Override
    public ImageView Draw(){
       ImageView iv = new ImageView(new Image(imagePath));
       iv.setFitWidth(90);
       iv.setFitHeight(90);
       return iv;
    }
}