package by.prostrmk.controller;

import by.prostrmk.model.entity.Post;
import by.prostrmk.model.entity.Subscription;
import by.prostrmk.model.entity.User;
import by.prostrmk.model.repository.PostRepository;
import by.prostrmk.model.repository.SubscriptionsRepository;
import by.prostrmk.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;
@Secured("ROLE_USER")
@Controller
public class SubscriptionsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionsRepository subscriptionsRepository;

    @GetMapping(value = "/feed")
    public ModelAndView getFeed(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByUsername = userRepository.findUserByUsername(name);
        return null;
    }

    @GetMapping(value = "/subs")
    @ResponseBody
    public List getSubs(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Subscription> byUserWhoSubscribed = subscriptionsRepository.findByUserWhoSubscribed(userRepository.findUserByUsername(name));
        List<Post> allPosts = new LinkedList<>();
        byUserWhoSubscribed.forEach(user-> allPosts.addAll(postRepository.findPostsByUsername(userRepository.findUserById(user.getSubscriptionUser().getId()).getUsername())));
        return allPosts;
    }



}
