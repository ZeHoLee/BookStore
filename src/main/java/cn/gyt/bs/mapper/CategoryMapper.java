package cn.gyt.bs.mapper;

import cn.gyt.bs.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface CategoryMapper {

    /**
     * 查询所有分类
     *
     * @return 分类集合 {@link List}
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
     * @param categoryId
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
