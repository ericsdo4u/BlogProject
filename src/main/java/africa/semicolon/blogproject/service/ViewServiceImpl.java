package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.model.Post;
import africa.semicolon.blogproject.data.model.model.View;
import africa.semicolon.blogproject.data.model.repository.PostRepository;
import africa.semicolon.blogproject.data.model.repository.ViewRepo;
import africa.semicolon.blogproject.dtos.ViewRequest;
import africa.semicolon.blogproject.exceptions.PostNotFoundException;
import africa.semicolon.blogproject.responses.ViewResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.blogproject.utilities.MapperClass.*;

@AllArgsConstructor
@Service
public class ViewServiceImpl implements ViewService {
    private ViewRepo viewRepo;
    private PostRepository postRepository;
    private UserService userService;

    @Override
    public ViewResponse viewPost(ViewRequest request) {
        userService.checkUser(request.getUsername());
        Optional<Post> post = postRepository.findById(request.getPostId());
        if(post.isEmpty()) throw new PostNotFoundException("Post Not Found");

        View view = mapView(request);
        viewRepo.save(view);
        return mapViewResponse(view);
    }
    @Override
    public long getListOfViewers() {
        return viewRepo.count();
    }

    public long getNumberOfView(String postId){
        List<View> views = viewRepo.findViewByPostId(postId);
        return views.size();
    }


}




