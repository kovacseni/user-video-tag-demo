package training.uservideotagdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserVideoTagServiceTest {

    @Autowired
    UserVideoTagService service;

    User user;

    @BeforeEach
    void init() {
        user = service.saveUser(new User("John Doe", LocalDate.of(2022, 10, 2)));
        Video video1 = service.saveVideo(new Video("My first video", LocalTime.of(0, 12, 30)));
        Video video2 = service.saveVideo(new Video("My second video", LocalTime.of(0, 8, 15)));
        service.saveVideoToUser(user.getId(), video1.getId());
        service.saveVideoToUser(user.getId(), video2.getId());
        Tag tag1 = service.saveTag(new Tag("myvideo"));
        Tag tag2 = service.saveTag(new Tag("firstvideo"));
        Tag tag3 = service.saveTag(new Tag("secondvideo"));
        service.saveTagToVideo(video1.getId(), tag1.getId());
        service.saveTagToVideo(video1.getId(), tag2.getId());
        service.saveTagToVideo(video2.getId(), tag1.getId());
        service.saveTagToVideo(video2.getId(), tag3.getId());
        Comment comment1 = service.saveComment(new Comment("I like this video very much"));
        Comment comment2 = service.saveComment(new Comment("This video is about my life"));
        service.saveCommentToUser(user.getId(), comment1.getId());
        service.saveCommentToUser(user.getId(), comment2.getId());
    }

    @Test
    void testSaveAndRead() {
        User expectedUser = service.readUserWithVideosTagsAndComments(user.getId());
        List<Video> videos = expectedUser.getVideos();

        assertThat(videos)
                .hasSize(2)
                .extracting(Video::getTitle)
                .containsExactlyInAnyOrder("My first video", "My second video");

        assertThat(expectedUser.getComments())
                .hasSize(2)
                .extracting(Comment::getText)
                .containsExactlyInAnyOrder("I like this video very much", "This video is about my life");

        assertThat(videos.get(0).getTags())
                .hasSize(2)
                .extracting(Tag::getText)
                .containsExactlyInAnyOrder("#myvideo", "#firstvideo");

        assertThat(videos.get(1).getTags())
                .hasSize(2)
                .extracting(Tag::getText)
                .containsExactlyInAnyOrder("#myvideo", "#secondvideo");
    }
}
