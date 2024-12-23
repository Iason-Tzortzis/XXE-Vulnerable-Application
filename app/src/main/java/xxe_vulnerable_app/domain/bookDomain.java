package xxe_vulnerable_app.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class bookDomain {

    @Id

    private String title;
    private String type;
    private String price;

    @Column(length = 10000)  private String description;
}
