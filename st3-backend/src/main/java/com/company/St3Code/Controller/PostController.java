package com.company.St3Code.Controller;

import com.company.St3Code.model.Post;
import com.company.St3Code.model.User;
import com.company.St3Code.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {

    //IOC-> Inversion of control | Dependency injection

    @Autowired
    private PostService postService;

    @RequestMapping("/posts")
    public String getUserPost(Model model, HttpSession session) {

        //Getting post of particular user
        User user=(User)session.getAttribute("LoggedUser");
        List<Post> posts=postService.getAllPosts(user.getId());
        //PostService postService=new PostService();
        //List<Post> posts=postService.getAllPosts();
        model.addAttribute("posts",posts);
        return "posts";
    }

    @RequestMapping(method= RequestMethod.GET,value="/posts/newpost")
    public String newPost(){
        return "posts/create";
    }

    @RequestMapping(method = RequestMethod.POST, value="/posts/create")
    public String createNewPost(Post newPost, HttpSession session){

        //Pick the user
        User user=(User)session.getAttribute("LoggedUser");
        newPost.setUser(user);

        //PostService postService=new PostService();
        //newPost.setDate(new Date());
        postService.createPost(newPost);
        return "redirect:/posts";
    }

    @RequestMapping(method=RequestMethod.DELETE,value = "/deletePost")
    public String deletePost(@RequestParam(name="postId") Integer postId){
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}

