package cn.gyt.bs.controller;

import cn.gyt.bs.common.result.model.ApiResult;
import cn.gyt.bs.common.result.model.PageResult;
import cn.gyt.bs.entity.Book;
import cn.gyt.bs.service.BookService;
import cn.gyt.bs.util.ApiResultUtils;
import cn.gyt.bs.util.PageResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping("/insertBook")
    @ResponseBody
    public ApiResult insertBook(MultipartFile file, Book book) {
        bookService.insertBook(file, book);
        return ApiResultUtils.success();
    }

    @GetMapping("/findBooksByPage")
    @ResponseBody
    public PageResult findBooksByPage(String keyword, Integer categoryId, Integer page, Integer size) {
        List<Map<String, Object>> list = bookService.findBooksByPage(keyword, categoryId, page, size);
        int total = bookService.count(keyword, categoryId);
        return PageResultUtils.success(list, total, size, (page / size), page);
    }

    @PostMapping("/updateBookById")
    @ResponseBody
    public ApiResult updateBookById(Book book) {
        bookService.updateBookById(book);
        return ApiResultUtils.success();
    }

    @PostMapping("/updateImageById")
    @ResponseBody
    public ApiResult updateImageById(String bookId, MultipartFile file) {
        bookService.updateImageById(bookId, file);
        return ApiResultUtils.success();
    }

    @GetMapping("/deleteBookById")
    @ResponseBody
    public ApiResult deleteBookById(String bookId) {
        bookService.deleteBookById(bookId);
        return ApiResultUtils.success();
    }

}
