package example.web.infrastructure.datasource.example;

import example.web.domain.model.example.ExampleRepository;
import example.web.domain.model.example.Example;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("with_db")
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
