package org.example;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class BlogController {
    Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    ArticleManager articleManager;

    @Value("${mju.blog.articles_per_page}")
    int articlesPerPage;

    @GetMapping("/hello")
    public String hello(){
        logger.debug("hello");
        logger.info("hello");
        logger.warn("hello");
        logger.error("hello");
        return "world";
    }

    @GetMapping("/config/articles_per_page")
    public int getArticlesPerPage() {
        return articlesPerPage;
    }

    @GetMapping("/article/titles")
    public Object getArticleTitles(){
        return articleManager.getTitles();
    }

    @GetMapping("/article/{number}")
    public Object getArticle(@PathVariable ("number") int blogNumber){
        /*HashMap<String, String> h = new HashMap<>();
        h.put("title", "happy day");
        h.put("number", "1");
        return h;*/
        GetArticleResponse r = new GetArticleResponse();
        r.number = blogNumber;
        r.title = articleManager.getTitleAt(blogNumber);
        return r;
    }

    @PostMapping("/article")
    public void postArticle(@RequestBody PostArticleRequest body){
        articleManager.getTitles().add(body.title);
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
