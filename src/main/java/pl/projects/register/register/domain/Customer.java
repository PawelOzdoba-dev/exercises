package pl.projects.register.register.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pl.projects.register.jpa.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(exclude = "customers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue
//    @Getter
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private LocalDate birthday;
    @CreatedDate
    private LocalDateTime createdAt;

    public Customer(String firstname, String lastname, String email, LocalDate birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.birthday = birthday;
    }
}
