package example.web.infrastructure.datasource.example;

import example.web.infrastructure.model.example.Example;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExampleMapper {
    Example findOne();
}
