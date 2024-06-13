package com.smma.hoppenhelm.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy extends GameObject implements IDrawable {
    private int x;
    private int range;
    private int health;
    private int damage;
    private static final String IMAGE_PATH = "goomba.png";
    public void attack() {
        // Implement attack logic
    }
    @Override
    public ImageView draw(){
       ImageView iv = new ImageView(new Image(IMAGE_PATH));
       iv.setFitWidth(90);
       iv.setFitHeight(90);
       return iv;
    }
}