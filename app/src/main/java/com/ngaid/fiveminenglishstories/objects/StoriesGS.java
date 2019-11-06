package com.ngaid.fiveminenglishstories.objects;

public class StoriesGS {
    private String title, author, genre, story, minutesAmount;
    private int key;

    public StoriesGS() {
        this.title = "";
        this.author = "";
        this.genre = "";
        this.minutesAmount = "";
        this.key = 0;
    }

    public StoriesGS(String title, String author, String genre, int key, String minutesAmount) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.key = key;
        this.minutesAmount = minutesAmount;
    }

    public StoriesGS(String title, String author, String genre, String story) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.story = story;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setYear(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getFBKey() {
        return key;
    }

    public void setFBKey(int key) {
        this.key = key;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getMinutesAmount() {
        return minutesAmount;
    }

    public void setMinutesAmount(String minutesAmount) {
        this.minutesAmount = minutesAmount;
    }
}
