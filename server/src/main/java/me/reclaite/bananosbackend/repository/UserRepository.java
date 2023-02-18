package me.reclaite.bananosbackend.repository;

import me.reclaite.bananosbackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM UserInfo user WHERE user.telegramId = :telegramId")
    User getByTelegramId(long telegramId);

}
