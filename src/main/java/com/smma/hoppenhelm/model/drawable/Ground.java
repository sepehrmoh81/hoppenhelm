package com.smma.hoppenhelm.model.drawable;

import com.smma.hoppenhelm.model.GameObject;
import com.smma.hoppenhelm.model.IDrawable;
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