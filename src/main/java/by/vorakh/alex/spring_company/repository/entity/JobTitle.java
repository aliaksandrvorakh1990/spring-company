package by.vorakh.alex.spring_company.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "job_title")
public class JobTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "title_id", unique = true, nullable = false)
    private int id;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "title", length = 20, nullable = false)
    private String title;

    public JobTitle() {
    }

    public JobTitle(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


}
