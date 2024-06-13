package com.smma.hoppenhelm.model;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Torch implements IDrawable {
    private int x;
    private List<Consumable> contents;
    private final String imagePath = "visuals/torch.gif";
    public List<Consumable> getContents() {
        return contents;
    }
    @Override
    public ImageView Draw(){
       ImageView iv = new ImageView(new Image(imagePath));
       iv.setFitWidth(100);
       iv.setFitHeight(100);
       return iv;
    }
}