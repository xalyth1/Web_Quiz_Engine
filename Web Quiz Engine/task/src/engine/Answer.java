package engine;

import java.util.ArrayList;
import java.util.List;

public class Answer {

    private List<Integer> answer;

    public Answer() {
        answer = new ArrayList<>();
    }

    public Answer(List<Integer> correctAnswers) {
        this.answer = correctAnswers;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
