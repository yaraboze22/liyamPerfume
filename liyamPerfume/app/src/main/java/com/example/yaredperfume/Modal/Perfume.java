package com.example.yaredperfume.Modal;

public class Perfume {
    private String name;
    private String description;
    private String price;
    private String url;
    private boolean liked;

    // Empty constructor required for Firebase
    public Perfume() {}

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public boolean isLiked() { return liked; }
    public void setLiked(boolean liked) { this.liked = liked; }
}

