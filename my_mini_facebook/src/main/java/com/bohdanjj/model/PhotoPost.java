package com.bohdanjj.model;

public class PhotoPost extends Post{

    private String imageUrl;

    public PhotoPost(User author, String content, String imageUrl) {
        super(author, content);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "PhotoPost{" +
                "id=" + getId() +
                ", author=" + getAuthor().getName() +
                ", content='" + getContent() + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", likes=" + getLikes() +
                ", comments=" + getComments().size() +
                '}';
    }
}
