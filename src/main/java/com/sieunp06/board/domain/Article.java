package com.sieunp06.board.domain;

import com.sieunp06.board.config.JpaConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter // Setter를 전체 영역에 걸지 않는 이유는 jpa에서 자동으로 생성해주는 값을 변경하지 않도록 하기 위해서
@ToString
@Table(indexes = {  // 빠르게 서칭이 가능하게끔.
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) private String title;   // 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문

    @Setter private String hashtag; // 해시태그

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    // jpa auditing
    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt;    // 생성 일시

    @CreatedBy @Column(nullable = false, length = 100) private String createdBy;   // 생성자   // 누가 만들었는지 확인할 수 없음. => spring security나 spring 인증 기능이 필요함.

    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt;   // 수정 일시

    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;  // 수정자

    // 기본 생성자를 가지고 있어야 함.
    protected Article() {}

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    // 팩토리 메서드로 생성자 메서드에 접근할 수 있도록 함.
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
