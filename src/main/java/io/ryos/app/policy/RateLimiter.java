package io.ryos.app.policy;

import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.redis.redisson.cas.RedissonBasedProxyManager;

public class RateLimiter {
    private final RedissonBasedProxyManager redissonBasedProxyManager;
    private final BucketConfiguration bucketConfiguration;

    public RateLimiter(RedissonBasedProxyManager redissonBasedProxyManager,
                       BucketConfiguration bucketConfiguration) {
        this.redissonBasedProxyManager = redissonBasedProxyManager;
        this.bucketConfiguration = bucketConfiguration;
    }

    public boolean tryAccess(String key) {
        return redissonBasedProxyManager
                .builder()
                .build(key, bucketConfiguration)
                .tryConsume(1);
    }
}
