package training.uservideotagdemo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserVideoTagService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    CommentRepository commentRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public void saveVideoToUser(long userId, long videoId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No user with id: " + userId));
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new IllegalArgumentException("No video with id: " + videoId));
        user.addVideo(video);
    }

    @Transactional
    public void saveTagToVideo(long videoId, long tagId) {
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new IllegalArgumentException("No video with id: " + videoId));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new IllegalArgumentException("No tag with id: " + tagId));
        video.addTag(tag);
    }

    @Transactional
    public void saveCommentToUser(long userId, long commentId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No user with id: " + userId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("No comment with id: " + commentId));
        user.addComment(comment);
    }

    @Transactional
    public User readUserWithVideosTagsAndComments(long userId) {
        User user = userRepository.findUserWithVideos(userId);
        userRepository.findUserWithComments(userId);
        List<Video> videos = user.getVideos();
        videoRepository.findVideosWithTags(videos);
        return user;
    }
}
