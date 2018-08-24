package by.prostrmk.controller;

import by.prostrmk.model.entity.Post;
import by.prostrmk.model.entity.User;
import by.prostrmk.model.repository.PostRepository;
import by.prostrmk.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/subscriptions")
public class SubscriptionsController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;



}
