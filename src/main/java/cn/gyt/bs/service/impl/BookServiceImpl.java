package cn.gyt.bs.service.impl;

import cn.gyt.bs.entity.Book;
import cn.gyt.bs.mapper.BookMapper;
import cn.gyt.bs.service.BookService;
import cn.gyt.bs.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 书籍信息服务实现类
 *
 * @author Administrator
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@Slf4j
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private UploadService uploadService;

    /**
     * 查询首页推荐书籍
     *
     * @return 书籍集合
     */
    @Override
    public List<Map<String, Object>> findBooksByDesc() {
        return bookMapper.findBooksByDesc();
    }

    /**
     * 查询热销榜单
     *
     * @return 书籍集合
     */
    @Override
    public List<Map<String, Object>> findHotBooks() {
        return bookMapper.findHotBooks();
    }

    /**
     * 查询书籍列表
     *
     * @param keyword    关键字
     * @param categoryId 分类ID
     * @param page       页码
     * @param size       大小
     * @return 书籍列表 {@link List}
     */
    @Override
    public List<Map<String, Object>> findBooksByPage(String keyword, Integer categoryId, Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        return bookMapper.findBooksByPage(keyword, categoryId, page, size);
    }

    /**
     * 计算分页总数
     *
     * @param keyword    关键字
     * @param categoryId 分类ID
     * @return {@link int}
     */
    @Override
    public Integer count(String keyword, Integer categoryId) {
        return bookMapper.count(keyword, categoryId);
    }

    /**
     * 根据ID查询书籍的封面图片
     *
     * @param bookId bookId
     * @return {@link String}
     */
    @Override
    public String findImageUrl(String bookId) {
        return bookMapper.findImageUrl(bookId);
    }

    /**
     * 添加书籍信息
     *
     * @param image 图片
     * @param book  书籍
     * @return {@link int}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertBook(MultipartFile image, Book book) {
        Date now = new Date();
        String newName = UUID.randomUUID().toString();
        //上传图片，返回上传的路径
        String url = (String) uploadService.upload(image, newName);
        //设置图片路径
        book.setImageUrl(url);
        return bookMapper.insertBook(book);
    }

    /**
     * 根据ID删除书籍
     *
     * @param bookId 书籍商品ID
     * @return {@link int}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteBookById(String bookId) {
        String imageUrl = findImageUrl(bookId);
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/"));
        log.info(fileName);
        //删除图片
        uploadService.delete("/image", fileName);
        return bookMapper.deleteBookById(bookId);
    }

    /**
     * 根据ID删除书籍信息
     *
     * @param book 书籍ID
     * @return {@link int}
     */
    @Override
    public int updateBookById(Book book) {
        return bookMapper.updateBookById(book);
    }

    /**
     * 根据ID更新图片
     *
     * @param bookId 书籍ID
     * @param image  图片
     * @return {@link int}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateImageById(String bookId, MultipartFile image) {
        String imageUrl = findImageUrl(bookId);
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/"));
        log.info(fileName);
        //删除旧的图片
        uploadService.delete("/image", fileName);
//        Date now = new Date();
        String newName = UUID.randomUUID().toString();
        //上传新图片，返回上传的路径
        String url = (String) uploadService.upload(image, newName);
        return bookMapper.updateImageById(bookId, url);
    }

    /**
     * 减库存
     *
     * @param number 数量
     * @param bookId 书籍ID
     * @return {@link int}
     */
    @Override
    public int reduceStock(int number, long bookId) {
        return bookMapper.reduceStock(number, bookId);
    }
}
