package cn.gyt.bs.service;

import cn.gyt.bs.entity.Category;

import java.util.List;

/**
 * 分类服务类接口
 *
 * @author Administrator
 */
public interface CategoryService {

    /**
     * 查询所有分类
     *
     * @return 分类集合
     */
    List<Category> findCategories();

    /**
     * 查询首页推荐分类
     *
     * @return 分类集合 {@link List}
     */
    List<Category> findIndexCategories();

    /**
     * 通过分类ID查询分类
     *
     * @param categoryId 分类ID
     * @return 分类实体 {@link cn.gyt.bs.entity.Category}
     */
    Category findCategoryById(int categoryId);

    /**
     * 添加分类
     *
     * @param category 分类实体
     * @return {@link int}
     */
    int insertCategory(Category category);

    /**
     * 根据ID删除分类
     *
     * @param categoryId 分类ID
     * @return {@link int}
     */
    int deleteCategoryById(int categoryId);

    /**
     * 更新分类
     *
     * @param category 分类实体
     * @return {@link int}
     */
    int updateCategoryById(Category category);
}
