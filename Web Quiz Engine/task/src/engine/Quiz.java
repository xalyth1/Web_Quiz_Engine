package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Quiz {

    private int id;

    @NotNull @NotBlank
    private String title;

    @NotNull @NotBlank
    private String text;

    @NotNull @Size(min = 2)
    private List<String> options;

    private Set<Integer> correctOptionsIndices;

    private static int currentId = 1;

    public Quiz() {
        correctOptionsIndices = new HashSet<>();
        this.id = currentId;
        currentId++;
    }

    public Quiz(String title, String text, List<String> options, Set<Integer> correctOptionsIndices) {
        this.id = currentId;
        currentId++;

        this.title = title;
        this.text = text;
        this.options = options;
        this.correctOptionsIndices = correctOptionsIndices;
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
    public Set<Integer> getCorrectOptionsIndices() {
        return correctOptionsIndices;
    }

    @JsonProperty(value = "answer")
    public void setCorrectOptionsIndices(Set<Integer> correctOptionsIndices) {
        this.correctOptionsIndices = correctOptionsIndices;
    }

    public int getId() {
        return id;
    }
}