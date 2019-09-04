package com.sourcecode.malls.repository.jpa.impl.article;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.article.ArticleCategory;
import com.sourcecode.malls.domain.merchant.Merchant;

public interface ArticleCategoryRepository
		extends JpaRepository<ArticleCategory, Long>, JpaSpecificationExecutor<ArticleCategory> {
	List<ArticleCategory> findAllByMerchant(Merchant merchant);
}
