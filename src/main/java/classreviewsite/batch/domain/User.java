package classreviewsite.batch.domain;

import classreviewsite.batch.config.data.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(nullable = false, length = 8, unique = true)
    private int userNumber;

    @Column(nullable = false, length = 255, unique = false)
    private String password;

    @Column(nullable = false, length = 45, unique = false)
    private String userName;

    @Column(nullable = false, length = 45, unique = false)
    private String department;

    @Column(nullable = false, length = 200, unique = true)
    private String nickname;

    @ManyToMany
    @JoinTable(
            name = "userAuthority",
            joinColumns = {@JoinColumn(name = "userNumber", referencedColumnName = "userNumber")},
            inverseJoinColumns = {@JoinColumn(name = "authority", referencedColumnName = "authority")}
    )
    private Set<Authority> authorities;

    public static User toEntity(Student item, String password, String nickName,  Authority authority) {
        return User.builder()
                .userName(item.getName())
                .userNumber(item.getStudentNumber())
                .department(item.getDepartment())
                .authorities(Collections.singleton(authority))
                .nickname(nickName)
                .password(password)
                .build();
    }



}
