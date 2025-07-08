package com.news.website.controller;

import com.news.website.model.Comment;
import com.news.website.model.News;
import com.news.website.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/")
    public String viewCategories(Model model) {
        model.addAttribute("categories", newsService.getAllCategories());
        return "categories";
    }

    @GetMapping("/category/{categoryId}")
    public String viewNewsByCategory(@PathVariable Long categoryId, Model model) {
        model.addAttribute("newsList", newsService.getNewsByCategory(categoryId));
        return "news_list";
    }

    @GetMapping("/news/{newsId}")
    public String viewNews(@PathVariable Long newsId, Model model) {
        News news = newsService.getNewsById(newsId).orElseThrow();
        model.addAttribute("news", news);
        model.addAttribute("comments", news.getComments());
        return "news_detail";
    }

    @PostMapping("/news/{newsId}/comment")
    public String addComment(@PathVariable Long newsId,
                             @RequestParam String commenterName,
                             @RequestParam String text) {
        Comment comment = new Comment();
        comment.setCommenterName(commenterName);
        comment.setText(text);
        newsService.addComment(newsId, comment);
        return "redirect:/news/" + newsId;
    }
}
