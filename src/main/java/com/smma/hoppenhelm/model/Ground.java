package com.smma.hoppenhelm.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ground implements IDrawable {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final String imagePath = "ground.png";

    @Override
    public ImageView draw(){
       ImageView iv = new ImageView(new Image(imagePath));
       iv.setFitWidth(90);
       iv.setFitHeight(90);
       return iv;
    }
}