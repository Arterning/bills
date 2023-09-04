package cn.ning.money.book.modules.test;

import cn.ning.money.book.entity.RoleEntity;
import cn.ning.money.book.mapper.RoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Slf4j
@CacheConfig(cacheNames = "CacheService")
@AllArgsConstructor
public class CacheService {


    RoleMapper roleMapper;

    /**
     * redisKey: cacheNames + key 组合而成 --> 支持SpEL
     * @return
     */
    @Cacheable(value = "cacheService", key = "#key", unless = "#result.size() == 0")
    public List<?> getCacheList(String key) {
        QueryWrapper<RoleEntity> objectQueryWrapper = new QueryWrapper<>();
        List<RoleEntity> roleEntities = roleMapper.selectList(objectQueryWrapper);
        log.info("getCacheList:{}", roleEntities);
        return roleEntities;
    }


}
