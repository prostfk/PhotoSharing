package by.prostrmk.controller;

import by.prostrmk.model.entity.User;
import by.prostrmk.model.repository.UserRepository;
import by.prostrmk.model.util.StringsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistration(){
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public String postRegistration(User user){
        if (user.validate()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    public ModelAndView getAuth(){
        return new ModelAndView("auth", "user", new User());
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logOutFromAccount(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/auth";
    }


    //    Ajax requests

    @RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
    @ResponseBody
    public String checkUserInBase(@PathParam("username") String username){
        User userByUsername = userRepository.findUserByUsername(username);
        if (userByUsername!=null){
            return "User with such username already exists";
        }
        return "";
    }


    @RequestMapping(value = "/checkPassword", method = RequestMethod.GET)
    public String checkPassword(@PathParam("password")String password){
        if (password.length() > 2 && password.length() < 10){
            return "";
        }
        return "Password must be between 2 and 10 characters";
    }


    @RequestMapping(value = "/checkMatching", method = RequestMethod.GET)
    @ResponseBody
    public String verifyPassword(@PathParam("password")String password, @PathParam("againPassword") String againPassword){
        if (!password.equals(againPassword)){
            return "Passwords does not match!";
        }
        return "";
    }


}
