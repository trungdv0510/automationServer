package autoServer.init;

import autoServer.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import autoServer.Entity.UserEntity;

@Component
public class UserInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
         this.userRepository.deleteAll();
        // Crete users
        UserEntity superUser = new UserEntity();
        superUser.setUsername("admin");
        superUser.setPassword(passwordEncoder.encode("admin"));
        superUser.setEmail("admin@gmail.com");
        superUser.setActive(true);
        superUser.setRoles("ADMIN,USER,MANANGER");
        superUser.setPermissions("ADMIN,USER,MANANGER");
        // Save to db
        this.userRepository.save(superUser);
    }


}
