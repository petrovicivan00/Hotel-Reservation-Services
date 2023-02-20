package com.sk02.sk02_gui_service.restclient.dto.review;


public class ReviewUpdateDto {

    private String comment;
    private int rate;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
