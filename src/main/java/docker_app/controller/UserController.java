package docker_app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import docker_app.model.User;
import docker_app.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController // http -> endpoints
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Rutas http
    // GET http://localhost:8080/users/1
    @GetMapping()
    public List<User> list() {
        return userService.list();
    }

    // GET http://localhost:8080/users/1
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    // POST http://localhost:8080/users/1
    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    // PUT http://localhost:8080/users/1
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {

        return userService.update(id, user);
    }

    // DELETE http://localhost:8080/users/1
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
