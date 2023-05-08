package com.linck.management.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linck.management.common.exception.BizException;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用条件构造器，通过 url param 参数设置，使用方式
 * eg: 查询 id = 1 的数据，eq[id]=1
 * eg: 模糊查询 name 包含张三的数据，like[name]=张三
 * eg: 左模糊查询 name 包含张三的数据，likeL[name]=张三
 * eg: 右模糊查询 name 包含张三的数据，likeR[name]=张三
 * eg: 模糊查询 name 不包含张三的数据，nLike[name]=张三
 * eg: 范围查询 time 在 2023-01-01 和 2023-02-02 之间的数据, ge[time]=2023-01-01&le[time]=2023-02-02
 *
 * @param <T> 创建 page 以及，queryWrapper 的泛型类
 *
 * @author linck
 * @date 2022-11-18
 */
@Data
public class QueryCondition<T> {

    /**
     * 创建 page 以及，queryWrapper 是泛型对应的具体实体类
     */
    private Class<?> entityClazz;

    /**
     * 表别名，只作用于实体类字段，不作用于 fieldNameCover 映射
     * eg: eq[name] = 2，对应到SQL时，不是 name = 2，而应该是 user.name = 2
     * 就可以设置 tableAlise = user.
     */
    private String tableAlise;

    /**
     * 字段名转换
     * eg：eq[name] = 2，对应到SQL时，不是 name = 2，而应该是 user.display_name = 2
     * 就可以配置 name: user.display_name 这样的映射关系
     */
    private Map<String, String> fieldNameCover;

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
     * 左模糊 eg: phone like %138
     */
    private Map<String, List<String>> likeL;
    /**
     * 右模糊 eg: phone like 138%
     */
    private Map<String, List<String>> likeR;
    /**
     * 页数
     */
    private Integer pageNo;
    /**
     * 每页行数
     */
    private Integer pageSize;

    /**
     * 开启分页查询，如果没有分页参数，则查询第一页，20行数据
     */
    public Page<T> page() {
        if (pageNo == null) {
            pageNo = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        return new Page<>(pageNo, pageSize);
    }

    /**
     * 设置表别名
     */
    public QueryCondition<T> tableAlise(String tableAlise) {
        this.tableAlise = tableAlise;
        return this;
    }

    /**
     * 设置字段名转换映射
     */
    public QueryCondition<T> fieldNameCover(Map<String, String> fieldNameCover) {
        this.fieldNameCover = fieldNameCover;
        return this;
    }

    /**
     * 根据 condition 内容创建 queryWrapper
     * column 会优先使用别名转换映射进行转换
     * 如果字段在别名转换映射中不存在，则会通过 clazz 获取对应实体类的字段，存在的字段，才允许作为条件
     *
     * @param clazz           list 查询的实体类
     */
    public QueryWrapper<T> dealQueryCondition(Class<?> clazz) {

        this.entityClazz = clazz;

        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        this.getEq().forEach((column, values) -> values.forEach(value -> {
            String coverColumn = fieldCover(column);
            queryWrapper.eq(coverColumn, value);
        }));

        this.getNe().forEach((column, values) -> values.forEach(value -> {
            String coverColumn = fieldCover(column);
            queryWrapper.ne(coverColumn, value);
        }));

        this.getGt().forEach((column, values) -> values.forEach(value -> {
            String coverColumn = fieldCover(column);
            queryWrapper.gt(coverColumn, value);
        }));

        this.getGe().forEach((column, values) -> values.forEach(value -> {
            String coverColumn = fieldCover(column);
            queryWrapper.ge(coverColumn, value);
        }));

        this.getLt().forEach((column, values) -> values.forEach(value -> {
            String coverColumn = fieldCover(column);
            queryWrapper.lt(coverColumn, value);
        }));

        this.getLe().forEach((column, values) -> values.forEach(value -> {
            String coverColumn = fieldCover(column);
            queryWrapper.le(coverColumn, value);
        }));

        this.getLike().forEach((column, values) -> values.forEach(value -> {
            String coverColumn = fieldCover(column);
            queryWrapper.like(coverColumn, value);
        }));

        this.getNLike().forEach((column, values) -> values.forEach(value -> {
            String coverColumn = fieldCover(column);
            queryWrapper.notLike(coverColumn, value);
        }));

        this.getLikeL().forEach((column, values) -> values.forEach(value -> {
            String coverColumn = fieldCover(column);
            queryWrapper.likeLeft(coverColumn, value);
        }));

        this.getLikeR().forEach((column, values) -> values.forEach(value -> {
            String coverColumn = fieldCover(column);
            queryWrapper.likeRight(coverColumn, value);
        }));
        return queryWrapper;
    }

    /**
     * 字段转换
     *
     * @param column 原字段
     * @return 转换后的字段
     */
    public String fieldCover(String column) {
        if (this.fieldNameCover != null && this.fieldNameCover.containsKey(column)) {
            return this.fieldNameCover.get(column);
        }
        Map<String, Field> fieldMap = this.getFields();
        if (fieldMap.containsKey(column)) {
            // 驼峰转下划线
            return (this.tableAlise == null ? "" : this.tableAlise) + StringUtils.camelToUnderline(column);
        }
        throw new BizException(String.format("不允许的搜索条件: %s", column));
    }

    /**
     * 获取字段缓存
     */
    private Map<String, Field> getFields() {
        if (!FIELD_CACHE.containsKey(this.entityClazz)) {
            FIELD_CACHE.put(this.entityClazz, getClassFields(this.entityClazz));
        }
        return FIELD_CACHE.get(this.entityClazz);
    }

    /**
     * 初始化实体类字段缓存
     *
     * @param clazz 要操作的实体类 class
     */
    private static Map<String, Field> getClassFields(Class<?> clazz) {
        Map<String, Field> fieldMap = new HashMap<>();

        // 如果父类不是 Object，获取其父类字段
        Class<?> superclass = clazz.getSuperclass();
        while (!superclass.equals(Object.class)) {
            for (Field field : superclass.getDeclaredFields()) {
                fieldMap.put(field.getName(), field);
            }
            superclass = superclass.getSuperclass();
        }

        for (Field field : clazz.getDeclaredFields()) {
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }
}
