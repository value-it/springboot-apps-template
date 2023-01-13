package example.web.domain.model.bookcatalog;

import java.util.List;

public record BookList(List<Book> list) {

    public BookList(List<Book> list){
        this.list = List.copyOf(list);
    }
}

