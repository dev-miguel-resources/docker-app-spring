package docker_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import docker_app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
