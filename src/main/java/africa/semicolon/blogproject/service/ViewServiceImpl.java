package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.model.Post;
import africa.semicolon.blogproject.data.model.repository.PostRepository;
import africa.semicolon.blogproject.data.model.repository.ViewRepo;
import africa.semicolon.blogproject.dtos.ViewRequest;
import africa.semicolon.blogproject.exceptions.PostNotFoundException;
import africa.semicolon.blogproject.responses.ViewResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



import static africa.semicolon.blogproject.utilities.MapperClass.mapViewResponse;


@AllArgsConstructor
@Service
public class ViewServiceImpl implements ViewService {
    private ViewRepo viewRepo;
    private PostRepository postRepository;

    @Override
    public ViewResponse incrementPostViews(ViewRequest request) {
        Post postViews = postRepository.findByUsername(request.getUsername());
        if (postViews == null) {
            throw new PostNotFoundException("No post match with this user");
        }
        Post updateView = viewRepo.incrementView(postViews);
        return mapViewResponse(updateView);
    }
    @Override
    public ViewResponse viewPost(ViewRequest request) {
        Post post = postRepository.findByUsername(request.getUsername());
        if (post == null) {
            throw new PostNotFoundException("Post with username not found");
        }
        post.incrementViews();
        Post updatedPost = postRepository.save(post);

        return mapViewResponse(updatedPost);
    }
}




