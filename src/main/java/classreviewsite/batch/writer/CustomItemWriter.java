package classreviewsite.batch.writer;

import classreviewsite.batch.config.data.Student;
import classreviewsite.batch.domain.Authority;
import classreviewsite.batch.domain.User;
import classreviewsite.batch.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomItemWriter implements ItemWriter<Student> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomItemWriter(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    int i = 0;

    @Override
    public void write(Chunk<? extends Student> chunk) {
        log.info("===== writer 작동 ====");
        chunk.getItems().forEach(item -> {
            Authority authority = Authority.toEntity("STUDENT");
            userRepository.save(
                    User.toEntity(item, passwordEncoder.encode("1234"), "testUser" + i, authority)
            );
            log.info(item.getName());
            i++;
        });

    }
}
