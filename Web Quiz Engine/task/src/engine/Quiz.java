package engine;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

//@JsonFilter("myFilter")
public class Quiz {
    private int id;
    private String title;
    private String text;
    private List<String> options;

    //@JsonIgnore
    private int correctOptionIndex;

    //@JsonIgnore
    private static int currentId = 1;

    public Quiz() {
        this.id = currentId;
        currentId++;
    }

//    public Quiz(int id, String title, String text, List<String> options, int correctOptionIndex) {
//        this.id = id;
//        this.title = title;
//        this.text = text;
//        this.options = options;
//        this.correctOptionIndex = correctOptionIndex;
//    }

    public Quiz(String title, String text, List<String> options, int correctOptionIndex) {
        this.id = currentId;
        currentId++;

        this.title = title;
        this.text = text;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }


    @JsonIgnore
    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    @JsonProperty(value = "answer")
    public void setCorrectOptionIndex(int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }

    public int getId() {
        return id;
    }
}
