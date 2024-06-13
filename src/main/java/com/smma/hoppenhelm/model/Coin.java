package com.smma.hoppenhelm.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Coin implements Consumable,IDrawable {
    private final int value;
    private final String imagePath = "visuals/coin.gif";
    public Coin(int value) {
        this.value = value;
    }
    @Override
    public ImageView Draw(){
       ImageView iv = new ImageView(new Image(imagePath));
       iv.setFitWidth(100);
       iv.setFitHeight(100);
       return iv;
    }
    public int getValue() {
        return value;
    }
}