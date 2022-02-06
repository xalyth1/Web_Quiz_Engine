package engine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "quiz")
public class QuizEntity {

    @Id
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    /**
     * each new line is option
     */
    @Column(name = "options")
    private String options;

    /**
     * CSV
     */
    @Column(name = "correctOptionsIndices")
    private String correctOptionsIndices;


    public QuizEntity() {
    }

    public QuizEntity(int id, String title, String text, String options, String correctOptionsIndices) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.correctOptionsIndices = correctOptionsIndices;
    }

    public QuizEntity(Quiz quiz) {
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.text = quiz.getText();

        List<String> options = quiz.getOptions();
        String optionsAsString = "";
        for (String opt : options) {
            optionsAsString += opt + "\n";
        }

        this.options = optionsAsString;

        Set<Integer> correctIndices = quiz.getCorrectOptionsIndices();

        if (correctIndices.isEmpty()) {
            this.correctOptionsIndices = "";
        } else {
            String correctIndicesAsString = "";
            for (Integer integer : correctIndices) {
                correctIndicesAsString += integer + ",";
            }
            this.correctOptionsIndices = correctIndicesAsString.substring(0,correctIndicesAsString.length() - 1);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getCorrectOptionsIndices() {
        return correctOptionsIndices;
    }

    public void setCorrectOptionsIndices(String correctOptionsIndices) {
        this.correctOptionsIndices = correctOptionsIndices;
    }
}
