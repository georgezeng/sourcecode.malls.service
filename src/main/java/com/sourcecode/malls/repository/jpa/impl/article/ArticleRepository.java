package com.sourcecode.malls.repository.jpa.impl.article;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.article.Article;
import com.sourcecode.malls.domain.article.ArticleCategory;
import com.sourcecode.malls.domain.merchant.Merchant;

public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
	long countByCategory(ArticleCategory category);

	Page<Article> findAllByCategory(ArticleCategory category, Pageable pageable);
	
	Optional<Article> findFirstByMerchantAndTitleOrderByUpdateTimeDesc(Merchant merchant, String title);

}
