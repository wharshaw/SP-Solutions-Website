package com.news.website.service;

import com.news.website.model.Category;
import com.news.website.model.Comment;
import com.news.website.model.News;
import com.news.website.repository.CategoryRepository;
import com.news.website.repository.CommentRepository;
import com.news.website.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CommentRepository commentRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<News> getNewsByCategory(Long categoryId) {
        return newsRepository.findByCategories_Id(categoryId);
    }

    public Optional<News> getNewsById(Long newsId) {
        return newsRepository.findById(newsId);
    }

    public Comment addComment(Long newsId, Comment comment) {
        News news = newsRepository.findById(newsId).orElseThrow();
        comment.setNews(news);
        return commentRepository.save(comment);
    }
}
