package docker_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import docker_app.model.User;
import docker_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    // Necesito traerme las operaciones de bdd desde el repositorio
    private final UserRepository userRepository;

    // Operaciones del CRUD
    public List<User> list() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User update(Long id, User user) {

        User userExits = userRepository.findById(id).orElse(null);

        if (userExits != null) {

            userExits.setName(user.getName());
            userExits.setEmail(user.getEmail());
            userExits.setPassword(user.getPassword());

            return userRepository.save(userExits);
        }

        return null;

    }

}
