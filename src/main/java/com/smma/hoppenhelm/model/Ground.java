package com.smma.hoppenhelm.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ground implements IDrawable {
    protected int x;
    private final String imagePath = "visuals/gound.png";
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    @Override
    public ImageView Draw(){
       ImageView iv = new ImageView(new Image(imagePath));
       iv.setFitWidth(90);
       iv.setFitHeight(90);
       return iv;
    }
}