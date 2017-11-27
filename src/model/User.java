package model;

import java.awt.*;
import java.util.Random;

public class User {

    private String id;
    private Color color;

    public User(String id) {
        this.id = id;
        this.color = randomColor();
    }

    private Color randomColor(){
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        return new Color(r, g, b);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isGoingIn() {
        return true;
    }
}