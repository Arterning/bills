package cn.ning.money.book.modules.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "contacts")
public class Contact {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    // getters, setters
}
