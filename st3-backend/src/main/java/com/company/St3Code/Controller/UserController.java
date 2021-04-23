package com.company.St3Code.Controller;

import com.company.St3Code.service.UserService;
import com.company.St3Code.service.PostService;
import com.company.St3Code.model.User;
//import com.company.St3Code.model.Post;
import com.company.St3Code.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.AuthProvider;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    //GET request to "/users/login"
    @RequestMapping(method= RequestMethod.GET,value="/users/login")
    public String login(Model model){
        model.addAttribute("user",new User());
        return "users/login";
    }

    //POST request to "/users/login"
    @RequestMapping(method= RequestMethod.POST,value="/users/login")
    public String loginUser(User user, HttpSession session){
        User existingUser=userService.login(user);
        if(existingUser == null) {
            System.out.println("USER DOES NOT EXIST");
            return "users/login";
        }
        else {
            // Maintain the Session
            session.setAttribute("LoggedUser", existingUser);

            System.out.println("USER FOUND!");
            return "redirect:/posts";
        }

    }

    @RequestMapping(method=RequestMethod.GET,value="/users/registration")
    public String registration(Model model){

        User user = new User();
        UserProfile userProfile = new UserProfile();
        user.setUserProfile(userProfile);
        model.addAttribute("user", user);

        return "users/registration";
    }

    @RequestMapping(method=RequestMethod.POST,value="/users/registration")
    public String userRegistration(User user){
        //Business logic to save the cred of user to the given data base.
        userService.registerUser(user);
        return "redirect:/users/login";
    }

    @RequestMapping("/users/logout")
    public String userLogout(Model model, HttpSession session){
        //Kill Session
        session.invalidate();
        /*List<Post> posts=postService.getAllPosts();
        model.addAttribute("posts",posts);*/
        return "redirect:/";
    }
}
