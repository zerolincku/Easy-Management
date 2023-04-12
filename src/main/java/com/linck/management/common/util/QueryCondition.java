package com.linck.management.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageHelper;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linck
 * @date 2022-11-18
 */
@Data
public class QueryCondition {

    /**
     * 字段缓存
     * 外层 map key 为实体类 class
     * 内存 map key 为字段名称
     */
    private static final Map<Class<?>, Map<String, Field>> FIELD_CACHE = new HashMap<>();

    public QueryCondition() {
        eq = new HashMap<>();
        gt = new HashMap<>();
        ge = new HashMap<>();
        lt = new HashMap<>();
        le = new HashMap<>();
        ne = new HashMap<>();
        like = new HashMap<>();
        nLike = new HashMap<>();
        likeL = new HashMap<>();
        likeR = new HashMap<>();
    }

    /**
     * eq ==
     */
    private Map<String, List<String>> eq;
    /**
     * neq !=
     */
    private Map<String, List<String>> ne;
    /**
     * gt >
     */
    private Map<String, List<String>> gt;
    /**
     * ge >=
     */
    private Map<String, List<String>> ge;
    /**
     * lt <
     */
    private Map<String, List<String>> lt;
    /**
     * le <=
     */
    private Map<String, List<String>> le;
    /**
     * 模糊搜索 eg: phone like %138%
     */
    private Map<String, List<String>> like;
    /**
     * 模糊搜索 eg: phone not like %138%
     */
    private Map<String, List<String>> nLike;
    /**
     * 左模糊 eg: phone like 138%
     */
    private Map<String, List<String>> likeL;
    /**
     * 右模糊 eg: phone like %138
     */
    private Map<String, List<String>> likeR;
    /**
     * 页数
     */
    private Integer pageNum;
    /**
     * 每页行数
     */
    private Integer pageSize;

    /**
     * 开启分页查询，如果没有分页参数，则查询第一页，20行数据
     */
    public void startPage() {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        PageHelper.startPage(pageNum, pageSize);
    }

    /**
     * 根据 condition 内容创建 queryWrapper
     * column 会优先使用别名转换映射进行转换
     * 如果字段在别名转换映射中不存在，则会通过 clazz 获取对应实体类的字段，存在的字段，才允许作为条件
     *
     * @param clazz           list 查询的实体类
     * @param tableAliseCover 别名转换映射
     */
    @SuppressWarnings("all")
    public QueryWrapper dealQueryCondition(Class<?> clazz, Map<String, String> tableAliseCover) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        this.getEq().forEach((column, values) -> {
            values.forEach(value -> {
                String coverColumn = fieldCover(column, clazz, tableAliseCover);
                queryWrapper.eq(coverColumn, value);
            });
        });

        this.getNe().forEach((column, values) -> {
            values.forEach(value -> {
                String coverColumn = fieldCover(column, clazz, tableAliseCover);
                queryWrapper.ne(coverColumn, value);
            });
        });

        this.getGt().forEach((column, values) -> {
            values.forEach(value -> {
                String coverColumn = fieldCover(column, clazz, tableAliseCover);
                queryWrapper.gt(coverColumn, value);
            });
        });

        this.getGe().forEach((column, values) -> {
            values.forEach(value -> {
                String coverColumn = fieldCover(column, clazz, tableAliseCover);
                queryWrapper.ge(coverColumn, value);
            });
        });

        this.getLt().forEach((column, values) -> {
            values.forEach(value -> {
                String coverColumn = fieldCover(column, clazz, tableAliseCover);
                queryWrapper.lt(coverColumn, value);
            });
        });

        this.getLe().forEach((column, values) -> {
            values.forEach(value -> {
                String coverColumn = fieldCover(column, clazz, tableAliseCover);
                queryWrapper.le(coverColumn, value);
            });
        });

        this.getLike().forEach((column, values) -> {
            values.forEach(value -> {
                String coverColumn = fieldCover(column, clazz, tableAliseCover);
                queryWrapper.like(coverColumn, value);
            });
        });

        this.getNLike().forEach((column, values) -> {
            values.forEach(value -> {
                String coverColumn = fieldCover(column, clazz, tableAliseCover);
                queryWrapper.notLike(coverColumn, value);
            });
        });

        this.getLikeL().forEach((column, values) -> {
            values.forEach(value -> {
                String coverColumn = fieldCover(column, clazz, tableAliseCover);
                queryWrapper.likeLeft(coverColumn, value);
            });
        });

        this.getLikeR().forEach((column, values) -> {
            values.forEach(value -> {
                String coverColumn = fieldCover(column, clazz, tableAliseCover);
                queryWrapper.likeRight(coverColumn, value);
            });
        });
        return queryWrapper;
    }

    /**
     * 字段转换
     *
     * @param column          原字段
     * @param clazz           搜索实体类
     * @param tableAliseCover 自定义的字段转换
     * @return 转换后的字段
     */
    public String fieldCover(String column, Class<?> clazz, Map<String, String> tableAliseCover) {
        if (tableAliseCover != null && tableAliseCover.containsKey(column)) {
            return tableAliseCover.get(column);
        }
        Map<String, Field> fieldMap = getField(clazz);
        if (fieldMap.containsKey(column)) {
            // 驼峰转下划线
            return StringUtils.camelToUnderline(column);
        }
        throw new RuntimeException(String.format("不允许的搜索条件: %s", column));
    }

    /**
     * 获取字段缓存
     */
    private Map<String, Field> getField(Class<?> clazz) {
        if (!FIELD_CACHE.containsKey(clazz)) {
            initClassFieldCache(clazz);
        }
        return FIELD_CACHE.get(clazz);
    }

    /**
     * 初始化实体类字段缓存
     *
     * @param clazz 要操作的实体类 class
     */
    private static void initClassFieldCache(Class<?> clazz) {
        Field[] fields = clazz.getFields();
        Map<String, Field> fieldMap = new HashMap<>();
        for (Field field : fields) {
            fieldMap.put(field.getName(), field);
        }
        FIELD_CACHE.put(clazz, fieldMap);
    }
}
