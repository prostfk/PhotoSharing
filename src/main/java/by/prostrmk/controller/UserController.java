package by.prostrmk.controller;

import by.prostrmk.model.entity.Post;
import by.prostrmk.model.entity.User;
import by.prostrmk.model.repository.PostRepository;
import by.prostrmk.model.repository.UserRepository;
import by.prostrmk.model.util.StringsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
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


//    Ajax requests

//    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
//    @ResponseBody
//    public String getSub(@PathParam("username")String username, HttpSession session){
//        User user = (User)session.getAttribute("user");
//        List<String> subs;
//        if (user.getSubscriptions()!=null){
//            subs = new ArrayList<>(Arrays.asList(user.getSubscriptions().split(" ")));
//        }else{
//            subs = new ArrayList<>();
//        }
//        if (!subs.contains(username)){
//            subs.add(username);
//            user.setSubscriptions(StringsUtil.getStringFromCollection(subs));
//            userRepository.updateUserSubs(user.getId(), user.getSubscriptions());
//        }
//        return "Subscribed";
//    }

//    @RequestMapping(value = "/unsubscribe", method = RequestMethod.GET)
//    @ResponseBody
//    public String getUnSub(@PathParam("username")String username, HttpSession session){
//        User user = (User) session.getAttribute("user");
//        List<String> subs;
//        if (user.getSubscriptions()!=null){
//            subs = new ArrayList<>(Arrays.asList(user.getSubscriptions().split(" ")));
//        }else{
//            subs = new ArrayList<>();
//        }
//        subs.remove(username);
//        user.setSubscriptions(StringsUtil.getStringFromCollection(subs));
//        userRepository.updateUserSubs(user.getId(), user.getSubscriptions());
//        return "Subscribe";
//    }

//    @RequestMapping(value = "/like", method = RequestMethod.GET)
//    @ResponseBody
//    public String getLikeOnPost(@PathParam("id")String id, HttpSession session){
//        User user = (User) session.getAttribute("user");
//        Long valueOfId = Long.valueOf(id);
//        List<String> listOfLikes = null;
//        Post postById = postRepository.findPostById(valueOfId);
//        try{
//            listOfLikes = new ArrayList<>(Arrays.asList(user.getLikedPosts().split(" ")));
//        }catch (NullPointerException e){
//            postRepository.setLikesToPost(valueOfId, postById.getLikes() + 1);
//            user.setLikedPosts(id + " ");
//            userRepository.updateUserLikes(user.getUsername(),user.getLikedPosts());
//            return postById.getLikes() + 1 + "";
//        }
//        if (listOfLikes.contains(id)){
//            postById.setLikes(postById.getLikes() - 1);
//            postRepository.setLikesToPost(valueOfId, postById.getLikes());
//            listOfLikes.remove(id);
//            user.setLikedPosts(StringsUtil.getStringFromCollection(listOfLikes));
//            userRepository.updateUserLikes(user.getUsername(),user.getLikedPosts());
//            return postById.getLikes() + "";
//        }else{
//            postById.setLikes(postById.getLikes() + 1);
//            postRepository.setLikesToPost(valueOfId, postById.getLikes());
//            listOfLikes.add(id);
//            user.setLikedPosts(StringsUtil.getStringFromCollection(listOfLikes));
//            userRepository.updateUserLikes(user.getUsername(),user.getLikedPosts());
//            return postById.getLikes() + "";
//        }
//    }



}
