package com.internet.blogsearchcommon.repositories;

import com.internet.blogsearchcommon.entities.KeywordCount;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;

public interface KeywordCountRepository extends JpaRepository<KeywordCount, Long> {
    List<KeywordCount> findTop10ByOrderByCountDesc();

    @Lock(LockModeType.PESSIMISTIC_READ)
    KeywordCount findByKeyword(String keyword);
}
