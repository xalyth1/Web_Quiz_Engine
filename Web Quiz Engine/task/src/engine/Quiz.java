package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.*;

public class Quiz {

    private long id;

    @NotNull @NotBlank
    private String title;

    @NotNull @NotBlank
    private String text;

    @NotNull @Size(min = 2)
    private List<String> options;

    @NotNull
    private Set<Integer> correctOptionsIndices;

    private static int currentId = 1;

    public Quiz() {
        correctOptionsIndices = new HashSet<>();
        this.id = currentId;
        currentId++;
    }

    public Quiz(String title, String text, ArrayList<String> options, Set<Integer> correctOptionsIndices) {
        this.id = currentId;
        currentId++;

        this.title = title;
        this.text = text;
        this.options = options;

        HashSet<Integer> corrOptInd = new HashSet<>();
        for (Integer i : correctOptionsIndices)
            corrOptInd.add(i);
        this.correctOptionsIndices = corrOptInd;
    }

    public Quiz(QuizEntity quizEntity) {
        this.id = quizEntity.getId();
        this.title = quizEntity.getTitle();
        this.text = quizEntity.getText();

        String[] opt = quizEntity.getOptions().split("\n");
        this.options = List.of(opt);


        String entityCorrect = quizEntity.getCorrectOptionsIndices();

        if (entityCorrect == "") {
            this.correctOptionsIndices = new HashSet<>();
        } else {
            String[] correctOpt = quizEntity.getCorrectOptionsIndices().trim().split(",");
            Integer[] integers = new Integer[correctOpt.length];
            for (int i = 0; i < correctOpt.length; i++) {
                integers[i] = Integer.parseInt(correctOpt[i]);
            }
            this.correctOptionsIndices = Set.of(integers);
        }
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

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    @JsonIgnore
    public Set<Integer> getCorrectOptionsIndices() {
        return correctOptionsIndices;
    }

    @JsonProperty(value = "answer")
    public void setCorrectOptionsIndices(HashSet<Integer> correctOptionsIndices) {
        this.correctOptionsIndices = correctOptionsIndices;
    }

    public long getId() {
        return id;
    }
}