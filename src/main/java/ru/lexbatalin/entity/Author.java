package ru.lexbatalin.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(of = "id")
public class Author implements Serializable {

    private static final long serialVersionUID = -4813153263848128015L;

    public Author(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @OneToMany(fetch = FetchType.LAZY,
            targetEntity = Book.class,
            mappedBy = "author")
    private List<Book> bookList = new ArrayList<>();
}
