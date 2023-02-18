package me.reclaite.bananosbackend.repository;

import me.reclaite.bananosbackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
