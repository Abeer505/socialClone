package com.example.assignement;

public class Model {
    String description;
    String imageURL;

    public Model() {
    }

    public Model(String description, String imageURL) {
        this.description = description;
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
