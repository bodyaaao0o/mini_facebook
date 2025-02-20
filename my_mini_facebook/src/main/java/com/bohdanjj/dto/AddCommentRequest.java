package com.bohdanjj.dto;


import jakarta.validation.constraints.NotBlank;

public class AddCommentRequest {

    @NotBlank(message = "Cannot be empty!")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
