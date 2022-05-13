package com.example.thesaurus.adapter;

// в листитеме хранятся все данные про элемент(позиция, категория, сам текст). Каждый элемент хранит в себе свою информацию
public class ListItem {
    private String text;
    private String cat;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



}
