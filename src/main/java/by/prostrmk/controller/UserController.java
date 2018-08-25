package by.prostrmk.controller;

import by.prostrmk.model.entity.Like;
import by.prostrmk.model.entity.Post;
import by.prostrmk.model.entity.Subscription;
import by.prostrmk.model.entity.User;
import by.prostrmk.model.repository.LikeRepository;
import by.prostrmk.model.repository.PostRepository;
import by.prostrmk.model.repository.UserRepository;
import by.prostrmk.model.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Secured("ROLE_USER")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;


    @GetMapping(value = "/addPost")
    public ModelAndView addPost() {
        return new ModelAndView("createPost", "post", new Post());
    }

    @PostMapping(value = "/addPost")
    public String savePost(Post post, MultipartFile file) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        post.setUsername(name);
        if (file.getOriginalFilename().length() > 1) {
            post.setPath(new FileUtil().saveFile(file, name));
        }
        postRepository.save(post);
        return "redirect:/me";
    }

    @GetMapping(value = "/me")
    public ModelAndView getMyPage() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByUsername = userRepository.findUserByUsername(name);
        List<Post> postsByUsername = postRepository.findPostsByUsername(name);
        ModelAndView modelAndView = new ModelAndView("userPage", "user", userByUsername);
        modelAndView.addObject("posts", postsByUsername);

        return modelAndView;
    }

    @GetMapping(value = "/{username}")
    public ModelAndView userPage(@PathVariable String username) {
        User userByUsername = userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userByUsername == null) {
            return new ModelAndView("error", "message", "no such user");
        }
        ModelAndView modelAndView = new ModelAndView("userPage", "user", userByUsername);
        modelAndView.addObject("posts", postRepository.findPostsByUsername(username));
        modelAndView.addObject("username", username);
        return modelAndView;
    }

//    AJAX

    @GetMapping(value = "/subscribe")
    @ResponseBody
    public String subscribe(String username) {
        User sessionUser = userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        User userByUsername = userRepository.findUserByUsername(username);
        sessionUser.addSubscription(new Subscription(sessionUser, userByUsername));
        userRepository.save(sessionUser);
        return "Unsubscribe";
    }

    @GetMapping(value = "/unsubscribe")
    @ResponseBody
    public String unsubscribe(String username) {
        User sessionUser = userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        User userByUsername = userRepository.findUserByUsername(username);
        sessionUser.getSubscriptions().remove(new Subscription(sessionUser, userByUsername));
        return "Subscribe";
    }

    @GetMapping(value = "/checkSub")
    @ResponseBody
    public String sendStatus(String username) {
        User sessionUser = userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        User userByUsername = userRepository.findUserByUsername(username);
        return sessionUser.getSubscriptions().contains(new Subscription(sessionUser, userByUsername)) + "";
    }

    @GetMapping(value = "/like")
    @ResponseBody
    public int setLike(Long id) {
        Post postById = postRepository.findPostById(id);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name);
        Like like = likeRepository.findLikeByPostIdAndAndUserId(id, user.getId());
        if (like == null) {
            postById.addLike(new Like(id, user.getId()));
        } else {
            postById.removeLike(like);
        }
        postRepository.save(postById);
        return postById.getLikes().size();
    }


}
