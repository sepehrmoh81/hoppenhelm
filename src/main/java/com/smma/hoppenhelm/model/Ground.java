package com.smma.hoppenhelm.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ground extends GameObject implements IDrawable {

    @Override
    public ImageView draw() {
        String imagePath = "ground.png";
        ImageView iv = new ImageView(new Image(imagePath));
        iv.setFitWidth(90);
        iv.setFitHeight(90);
        return iv;
    }
}