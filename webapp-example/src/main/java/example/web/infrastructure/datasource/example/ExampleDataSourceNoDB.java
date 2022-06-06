package example.web.infrastructure.datasource.example;

import example.web.domain.model.example.ExampleRepository;
import example.web.domain.model.example.Example;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("without_db")
public class ExampleDataSourceNoDB implements ExampleRepository {

    @Override
    public Example findOne() {
        return new Example("DB接続無しのダミー値");
    }
}
