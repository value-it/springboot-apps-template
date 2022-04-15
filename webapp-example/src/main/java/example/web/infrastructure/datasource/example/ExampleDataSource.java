package example.web.infrastructure.datasource.example;

import example.web.domain.model.example.ExampleRepository;
import example.web.infrastructure.model.example.Example;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleDataSource implements ExampleRepository {

    @Override
    public Example findOne() {
        return mapper.findOne();
    }

    private final ExampleMapper mapper;

    public ExampleDataSource(ExampleMapper mapper) {
        this.mapper = mapper;
    }
}
