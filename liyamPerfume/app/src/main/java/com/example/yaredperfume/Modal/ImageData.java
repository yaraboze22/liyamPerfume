package com.example.yaredperfume.Modal;

public class ImageData {
    private String id;
    private String url;
    private String name;
    private String description;
    private String price;
    private boolean liked;

    public ImageData(String id,String url, String name, String description, String price,boolean liked) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.description = description;
        this.price = price;
        this.liked=liked;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public boolean isLiked(){
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}


