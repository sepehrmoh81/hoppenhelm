package com.smma.hoppenhelm.model;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Blank {
    public ImageView draw(){
        ImageView imageView = new ImageView();
        imageView.setFitWidth(90);
        imageView.setFitHeight(30);
        return imageView;
    }
}
