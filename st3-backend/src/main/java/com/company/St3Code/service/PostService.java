package com.company.St3Code.service;

//import com.app.BlopApplication.model.Post;
//import com.app.BlopApplication.repository.PostRepository;
import com.company.St3Code.model.Post;
import com.company.St3Code.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {



    @Autowired
    private PostRepository postRepository;
    //userId added to get particular post of user
    public List<Post> getAllPosts(Integer userId){
        return postRepository.getAllPosts(userId);
    }

    public void createPost(Post newPost){
        postRepository.createPost(newPost);
    }

    public void deletePost(Integer postId){
        postRepository.deletePost(postId);
    }
}

