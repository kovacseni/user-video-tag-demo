package training.uservideotagdemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select distinct u from User u left join fetch u.videos where u.id = :id")
    User findUserWithVideos(@Param("id") long userId);

    @Query("select distinct u from User u left join fetch u.comments where u.id = :id")
    User findUserWithComments(@Param("id") long userId);
}
