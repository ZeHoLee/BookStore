package cn.gyt.bs.service.impl;

import cn.gyt.bs.entity.Category;
import cn.gyt.bs.mapper.CategoryMapper;
import cn.gyt.bs.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类服务实现类
 *
 * @author Administrator
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 查询所有分类
     *
     * @return 分类集合
     */
    @Override
    public List<Category> findCategories() {
        return categoryMapper.findCategories();
    }

    /**
     * 查询首页推荐分类
     *
     * @return 分类集合 {@link List}
     */
    @Override
    public List<Category> findIndexCategories() {
        return categoryMapper.findIndexCategories();
    }

    /**
     * 通过分类ID查询分类
     *
     * @param categoryId 分类ID
     * @return 分类实体 {@link Category}
     */
    @Override
    public Category findCategoryById(int categoryId) {
        return categoryMapper.findCategoryById(categoryId);
    }

    @Override
    public int insertCategory(Category category) {
        return categoryMapper.insertCategory(category);
    }

    @Override
    public int deleteCategoryById(int categoryId) {
        return categoryMapper.deleteCategoryById(categoryId);
    }

    @Override
    public int updateCategoryById(Category category) {
        return categoryMapper.updateCategoryById(category);
    }
}
