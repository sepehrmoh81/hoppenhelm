package com.smma.hoppenhelm.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Coin extends GameObject implements Consumable,IDrawable {
    private static final String IMAGE_PATH = "coin.png";

    @Override
    public ImageView draw() {
        ImageView iv = new ImageView(new Image(IMAGE_PATH));
        iv.setFitWidth(90);
        iv.setFitHeight(30);
        iv.setPreserveRatio(true);
        return iv;
    }
}