package by.prostrmk.controller;

import by.prostrmk.model.entity.Subscription;
import by.prostrmk.model.util.FileUtil;
import by.prostrmk.model.entity.Post;
import by.prostrmk.model.entity.User;
import by.prostrmk.model.repository.PostRepository;
import by.prostrmk.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/addUser")
    @ResponseBody
    public User add() {
        User roman = userRepository.save(new User(
                "Roman", passwordEncoder.encode("123"), new ArrayList<>(Arrays.asList(
                new Subscription(1L,3L), new Subscription(1L,2L)
        ))
        ));
        return roman;
    }

}

