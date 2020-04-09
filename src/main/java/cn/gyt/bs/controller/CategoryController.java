package cn.gyt.bs.controller;

import cn.gyt.bs.common.result.model.ApiResult;
import cn.gyt.bs.entity.Category;
import cn.gyt.bs.service.CategoryService;
import cn.gyt.bs.util.ApiResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/findCategories")
    @ResponseBody
    public ApiResult findCategories() {
        List<Category> list = categoryService.findCategories();
        return ApiResultUtils.success(list);
    }

    @PostMapping("/insertCategory")
    @ResponseBody
    public ApiResult insertCategory(Category category) {
        categoryService.insertCategory(category);
        return ApiResultUtils.success();
    }

    @GetMapping("/deleteCategoryById")
    @ResponseBody
    public ApiResult deleteCategoryById(int categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ApiResultUtils.success();
    }

    @PostMapping("updateCategoryById")
    @ResponseBody
    public ApiResult updateCategoryById(Category category) {
        categoryService.updateCategoryById(category);
        return ApiResultUtils.success();
    }
}
