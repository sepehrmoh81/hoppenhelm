package com.smma.hoppenhelm.model.drawable;

import com.smma.hoppenhelm.model.GameObject;
import com.smma.hoppenhelm.model.IDrawable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Torch extends GameObject implements IDrawable {
    private static final String IMAGE_PATH = "torch.png";

    @Override
    public ImageView draw() {
        ImageView iv = new ImageView(new Image(IMAGE_PATH));
        iv.setFitWidth(90);
        iv.setFitHeight(30);
        iv.setPreserveRatio(true);
        return iv;
    }
}