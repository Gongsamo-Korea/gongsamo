package org.project.gongsamo.service;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ViewCountService {

    private final RedisTemplate<String, Long> redisTemplate;

    @Autowired
    public ViewCountService(RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //조회수 늘리기
    @Transactional
    public Long hitViewCount(Long articleId) {
        String redisKey = articleId.toString();
        Long redisValue = getViewCount(articleId);

        Long viewCount;
        if (redisValue == null) {
            viewCount = 1L;
        } else {
            viewCount = redisValue + 1;
        }

        ValueOperations<String, Long> values = redisTemplate.opsForValue();
        values.set(redisKey, viewCount);
        return viewCount;
    }

    //조회수 가져오기
    public Long getViewCount(Long articleId) {
        return redisTemplate.opsForValue().get(articleId.toString());
    }
}
