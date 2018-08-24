package by.prostrmk.controller;

import by.prostrmk.model.entity.Post;
import by.prostrmk.model.entity.User;
import by.prostrmk.model.repository.PostRepository;
import by.prostrmk.model.repository.UserRepository;
import by.prostrmk.model.util.FileUtil;
import by.prostrmk.model.util.StringsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Secured("ROLE_USER")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public ModelAndView getCheckUserProfile(@PathVariable String username, HttpSession session){
        List<Post> postsByUsername = postRepository.findPostsByUsername(username);
        ModelAndView modelAndView = new ModelAndView("userPage", "posts", postsByUsername);
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("user", user);
        modelAndView.addObject("username", username);
        return modelAndView;
    }


    @GetMapping(value = "/addPost")
    public ModelAndView addPost(){
        return new ModelAndView("createPost", "post", new Post());
    }

    @PostMapping(value = "/addPost")
    public String savePost(Post post, MultipartFile file){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        post.setUsername(name);
        if (file.getOriginalFilename().length() > 1){
            post.setPath(new FileUtil().saveFile(file,name));
        }
        postRepository.save(post);
        return "redirect:/me";
    }

    @GetMapping(value = "/me")
    public ModelAndView getMyPage(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByUsername = userRepository.findUserByUsername(name);
        List<Post> postsByUsername = postRepository.findPostsByUsername(name);
        ModelAndView modelAndView = new ModelAndView("userPage", "user", userByUsername);
        modelAndView.addObject("posts", postsByUsername);

        return modelAndView;
    }


}
