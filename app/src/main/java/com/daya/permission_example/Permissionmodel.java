package com.daya.permission_example;

public class Permissionmodel {
    private String textButton;
    private int ImageResource;

    public Permissionmodel(String textButton, int imageResource) {
        this.textButton = textButton;
        ImageResource = imageResource;
    }

    public String getTextButton() {
        return textButton;
    }

    public void setTextButton(String textButton) {
        this.textButton = textButton;
    }

    public int getImageResource() {
        return ImageResource;
    }

    public void setImageResource(int imageResource) {
        ImageResource = imageResource;
    }
}
