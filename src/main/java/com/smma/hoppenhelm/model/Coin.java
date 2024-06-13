package com.smma.hoppenhelm.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Coin implements Consumable,IDrawable {
    private final int value;
    private static final String IMAGE_PATH = "coin.gif";
    public Coin(int value) {
        this.value = value;
    }
    @Override
    public ImageView draw(){
       ImageView iv = new ImageView(new Image(IMAGE_PATH));
       iv.setFitWidth(100);
       iv.setFitHeight(100);
       return iv;
    }
    public int getValue() {
        return value;
    }
}