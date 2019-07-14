package com.sourcecode.malls.repository.jpa.impl.client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourcecode.malls.domain.client.WechatToken;

public interface WechatTokenRepository extends JpaRepository<WechatToken, Long> {
	Optional<WechatToken> findByUserId(Long userId);
}
