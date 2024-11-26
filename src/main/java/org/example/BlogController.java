package org.example;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class BlogController {
    @Value("${mju.blog.articles_per_page}")
    int articlesPerPage;

    @GetMapping("/hello")
    public String hello(){
        return "world";
    }

    @GetMapping("/config/articles_per_page")
    public int getArticlesPerPage() {
        return articlesPerPage;
    }

    @GetMapping("/article/titles")
    public Object getArticleTitles(){
        ArrayList<String> a = new ArrayList<>();
        a.add("제목1");
        a.add("제목2");
        return a;
    }

    @GetMapping("/article/{number}")
    public Object getArticle(@PathVariable ("number") int blogNumber){
        /*HashMap<String, String> h = new HashMap<>();
        h.put("title", "happy day");
        h.put("number", "1");
        return h;*/
        GetArticleResponse r = new GetArticleResponse();
        r.title = "happy day";
        r.number = blogNumber;
        return r;
    }

    @PostMapping("/article")
    public void postArticle(@RequestBody PostArticleRequest body){
        System.out.println(body.title);
    }

    @GetMapping("/error1/{code}")
    public void getStatusCode1(@PathVariable int code, HttpServletResponse response){
        response.setStatus(code);
    }

    @GetMapping("/error2/{code}")
    public ResponseEntity<?> getStatusCode2(@PathVariable int code){
        HttpStatusCode code2 = HttpStatusCode.valueOf(code);
        return new ResponseEntity<>(code2);
    }

    @GetMapping("/error3/{code3}")
    public void getStatusCode3(@PathVariable int code){
        HttpStatusCode code3 = HttpStatusCode.valueOf(code);
        throw new ResponseStatusException(code3);
    }

}
