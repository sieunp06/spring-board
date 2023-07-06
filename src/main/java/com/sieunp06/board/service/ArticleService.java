package com.sieunp06.board.service;

import com.sieunp06.board.domain.type.SearchType;
import com.sieunp06.board.dto.ArticleDto;
import com.sieunp06.board.dto.ArticleUpdateDto;
import com.sieunp06.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor    // 필수 필드에 대한 생성자를 자동으로 만들어줌
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType title, String searchKeyword) {
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(long l) {
        return null;
    }

    public void saveArticle(ArticleDto dto) {
    }

    public void updateArticle(long articleId, ArticleUpdateDto of) {
    }

    public void deleteArticle(long l) {
    }
}
