package example.web.application.example;

import example.web.domain.model.example.ExampleRepository;
import example.web.domain.model.example.Example;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    public Example findExample() {
        return exampleRepository.findOne();
    }

    private final ExampleRepository exampleRepository;

    public ExampleService(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }
}
