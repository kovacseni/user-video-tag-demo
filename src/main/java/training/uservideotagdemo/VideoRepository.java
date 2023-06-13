package training.uservideotagdemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("select distinct v from Video v left join fetch v.tags where v in :videos")
    List<Video> findVideosWithTags(@Param("videos") List<Video> videos);
}
