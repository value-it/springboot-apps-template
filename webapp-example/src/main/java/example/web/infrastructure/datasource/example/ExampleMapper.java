package example.web.infrastructure.datasource.example;

import example.web.domain.model.example.Example;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExampleMapper {
    Example findOne();
}
