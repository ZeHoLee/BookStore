package cn.gyt.bs.controller;

import cn.gyt.bs.common.result.model.ApiResult;
import cn.gyt.bs.entity.Category;
import cn.gyt.bs.service.BookService;
import cn.gyt.bs.service.CategoryService;
import cn.gyt.bs.util.ApiResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @Resource
    private BookService bookService;

    @Resource
    private CategoryService categoryService;

    @GetMapping("/loadIndex")
    @ResponseBody
    public ApiResult loadIndex() {
        //查询首页推荐书籍
        List<Map<String, Object>> indexBooks = bookService.findBooksByDesc();
        //查询热搜书籍
        List<Map<String, Object>> hotBooks = bookService.findHotBooks();
        //查询所有分类
        List<Category> categories = categoryService.findCategories();

        Map<String, Object> data = new HashMap<>(16);
        data.put("indexBooks", indexBooks);
        data.put("hotBooks", hotBooks);
        data.put("categories", categories);
        return ApiResultUtils.success(data);
    }

    @GetMapping("/findBooksByDesc")
    @ResponseBody
    public ApiResult findBooksByDesc() {
        List<Map<String, Object>> books = bookService.findBooksByDesc();
        return ApiResultUtils.success(books);
    }

    @GetMapping("/findIndexCategories")
    @ResponseBody
    public ApiResult findIndexCategories() {
        List<Category> list = categoryService.findIndexCategories();
        return ApiResultUtils.success(list);
    }

    @GetMapping("/findHotBooks")
    @ResponseBody
    public ApiResult findHotBooks() {
        List<Map<String, Object>> list = bookService.findHotBooks();
        return ApiResultUtils.success(list);
    }
}
