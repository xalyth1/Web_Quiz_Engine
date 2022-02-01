package engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;

@RestController
public class Controller {

//    Quiz q1 = new Quiz(1, "The Java Logo", "What is depicted on the Java logo?",
//            List.of("Robot", "Tea leaf", "Cup of coffee", "Bug"), 2);

    //List<Quiz> listOfQuizzes = List.of(q1);//new ArrayList<>();
    List<Quiz> listOfQuizzes = new ArrayList<>();


    /**
     * Get all quizzes
     *
     * @return list of quizzes
     */
    @GetMapping("/api/quizzes")
    public List<Quiz> getAllQuizzes() {
        return listOfQuizzes;
    }

    /**
     * Get a quiz by id
     *
     * @param id - specific quiz id
     * @return result - quiz without answer
     */
    @GetMapping("/api/quizzes/{id}")
    public ResponseEntity getQuizById(@PathVariable int id) {
        Optional<Quiz> optional = searchById(id);
        if (optional.isEmpty()) {
            return new ResponseEntity(Map.of("error", "there is no quiz with such id"), HttpStatus.NOT_FOUND);
        }

        Quiz quiz = optional.get();

        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept("correctOptionIndex");
        FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", theFilter);
        ObjectMapper mapper = new ObjectMapper();

        String quizAsString;
        JsonNode node;
        try {
            quizAsString = mapper.writer(filters).writeValueAsString(quiz);
            node = mapper.readTree(quizAsString);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(node, HttpStatus.OK);
    }

    public Optional<Quiz> searchById(int id) {
        for (Quiz quiz : listOfQuizzes) {
            if (quiz.getId() == id)
                return Optional.of(quiz);
        }
        return Optional.empty();
    }


    /**
     * Solving a quiz
     *
     * @param answer - quiz id
     * @return Result - success boolean and feedback
     */
    @PostMapping("/api/quizzes/{id}/solve")
    public ResponseEntity solveQuiz(@PathVariable int id, @RequestParam int answer) {
        Optional<Quiz> optional = searchById(id);
        if (optional.isEmpty()) {
            return new ResponseEntity(Map.of("error", "there is no quiz with such id!"), HttpStatus.NOT_FOUND);
        }

        Quiz quiz = optional.get();
        Result result;
        if (answer == quiz.getCorrectOptionIndex()) {
            result = new Result(true, "Congratulations, you're right!");
        } else {
            result = new Result(false, "Wrong answer! Please, try again.");
        }

        return new ResponseEntity(result, HttpStatus.OK);
    }


    /**
     * Create a new quiz
     */
    @PostMapping("/api/quizzes")
    public ResponseEntity createNewQuiz(@RequestBody Quiz quiz) {

        listOfQuizzes.add(quiz);

//        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept("correctOptionIndex");
//        FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", theFilter);
//        ObjectMapper mapper = new ObjectMapper();

        String quizAsString;
        JsonNode node;
        //try {
//            quizAsString = mapper.writer(filters).writeValueAsString(quiz);
//            node = mapper.readTree(quizAsString);
//        } catch (JsonProcessingException e) {
//            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        //return new ResponseEntity<>(quizAsString, HttpStatus.OK);

        return new ResponseEntity(quiz, HttpStatus.OK);
    }
}
