package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Optional<QuizEntity> findQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public QuizEntity save(Quiz quiz) {
        QuizEntity quizEntity = new QuizEntity(quiz);
        return quizRepository.save(quizEntity);
    }

    public List<Quiz> findAll() {
        Iterable<QuizEntity> quizEntities = quizRepository.findAll();
        List<Quiz> list = new ArrayList<>();

        for (QuizEntity qe : quizEntities) {
            list.add(new Quiz(qe));
        }
        return list;
    }

    public Optional<Quiz> searchById(int id) {
        Long aLong = (long) id;
        Optional<QuizEntity> opt = quizRepository.findById(aLong);
        if (opt.isEmpty())
            return Optional.empty();
        else
            return Optional.of(new Quiz(opt.get()));

    }
}
