package com.smma.hoppenhelm.model;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Torch extends GameObject implements IDrawable {
    private int x;
    private List<Consumable> contents;
    private static final String IMAGE_PATH = "torch.gif";
    public List<Consumable> getContents() {
        return contents;
    }
    @Override
    public ImageView draw(){
       ImageView iv = new ImageView(new Image(IMAGE_PATH));
       iv.setFitWidth(90);
       iv.setFitHeight(90);
       return iv;
    }
}