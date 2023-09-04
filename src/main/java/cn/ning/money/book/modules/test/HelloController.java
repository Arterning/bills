package cn.ning.money.book.modules.test;

import cn.ning.money.book.entity.RoleEntity;
import cn.ning.money.book.mapper.RecordDetailMapper;
import cn.ning.money.book.mapper.RoleMapper;
import cn.ning.money.book.utils.RedisUtil;
import cn.ning.money.book.vo.PageVO;
import cn.ning.money.book.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "HelloController", tags = { "测试接口"})
@RequestMapping("/test/hello")
@AllArgsConstructor
public class HelloController {

    RedisUtil redisUtil;

    RecordDetailMapper recordDetailMapper;

    RoleMapper roleMapper;

    CacheService cacheService;


    @ApiOperation(value = "你爱我 我爱你")
    @GetMapping()
    public Result<?> hello() {
        return Result.success("你爱我，我爱你");
    }

    @GetMapping("cacheService")
    public Result<?> cacheService() {
        List<?> cacheList = cacheService.getCacheList("roleList");
        return Result.success(cacheList);
    }


    @GetMapping("redis")
    public Result<?> redis() {
        redisUtil.set("hello", "world");
        redisUtil.lSet("list", Arrays.asList("2234", "w342342","234234"));
        redisUtil.lSet("newList", Lists.newArrayList("2234", "w342342","234234"));
        TestRedisObject object = TestRedisObject.builder()
                .age("23")
                .birthday(new Date())
                .id(1)
                .money(new BigDecimal(100))
                .name("ning")
                .sex("man").build();
        redisUtil.set("obj", object);
        Object obj = redisUtil.get("obj");
        return Result.success(obj);
    }

    @PostMapping("post")
    public Result<?> post(@RequestBody TestRedisObject postObject) {
        redisUtil.set("postObject", postObject);
        Object obj = redisUtil.get("postObject");
        return Result.success(obj);
    }

    @GetMapping("list")
    public Result<?> list() {
        QueryWrapper<RoleEntity> objectQueryWrapper = new QueryWrapper<>();
        List<RoleEntity> roleEntities = roleMapper.selectList(objectQueryWrapper);
        return Result.success(roleEntities);
    }

    @GetMapping("listByCondition")
    public Result<?> listByCondition() {
        QueryWrapper<RoleEntity> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.select("name", "info");
        objectQueryWrapper.eq("name", "root");
        RoleEntity roleEntity = roleMapper.selectOne(objectQueryWrapper);
        return Result.success(roleEntity);
    }


    /**
     * 分页查询
     * 测试发现使用@GetMapping居然也可以接收到JSON请求体
     * 不过这并不是标准做法
     * @param pageVO
     * @return
     */
    @PostMapping("pageQuery")
    public Result<?> pageQuery(@RequestBody PageVO pageVO) {
        Page<RoleEntity> page = new Page<>(pageVO.getPageIndex(), pageVO.getPageSize()); // 创建分页对象
        IPage<RoleEntity> userPage = roleMapper.selectPage(page, null); // 执行分页查询
        return Result.success(userPage);
    }


    /**
     * Redis数据如果反序列化 需要实现一个空参数的构造器
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class TestRedisObject {
        String name;
        String sex;
        String age;
        Integer id;
        BigDecimal money;
        Date birthday;
    }


    @Data
    static class RecordObject {
        String name;
        String passwd;
    }


}
