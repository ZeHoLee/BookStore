package cn.gyt.bs.mapper;

import cn.gyt.bs.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 操作数据库
 *
 * @author Administrator
 */
@Mapper
public interface BookMapper {

    /**
     * 查询首页推荐书籍
     *
     * @return 书籍列表 {@link List}
     */
    List<Map<String, Object>> findBooksByDesc();

    /**
     * 查询热销榜单
     *
     * @return 书籍列表 {@link List}
     */
    List<Map<String, Object>> findHotBooks();

    /**
     * 查询书籍列表
     *
     * @param keyword    关键字
     * @param categoryId 分类ID
     * @param page       页码
     * @param size       大小
     * @return 书籍列表 {@link List}
     */
    List<Map<String, Object>> findBooksByPage(String keyword, Integer categoryId, Integer page, Integer size);

    /**
     * 计算分页总数
     *
     * @param keyword    关键字
     * @param categoryId 分类ID
     * @return {@link int}
     */
    Integer count(String keyword, Integer categoryId);

    /**
     * 根据ID查询书籍的封面图片
     *
     * @param bookId bookId
     * @return {@link String}
     */
    String findImageUrl(String bookId);

    /**
     * 添加书籍信息
     *
     * @param book 书籍
     * @return {@link int}
     */
    int insertBook(Book book);

    /**
     * 根据ID删除书籍
     *
     * @param bookId 书籍商品ID
     * @return {@link int}
     */
    int deleteBookById(String bookId);

    /**
     * 根据ID删除书籍信息
     *
     * @param book 书籍实体
     * @return {@link int}
     */
    int updateBookById(Book book);

    /**
     * 根据ID更新图片
     *
     * @param bookId   书籍ID
     * @param imageUrl 更新后的路径
     * @return {@link int}
     */
    int updateImageById(String bookId, String imageUrl);

    /**
     * 减库存
     *
     * @param number 数量
     * @param bookId 书籍ID
     * @return {@link int}
     */
    int reduceStock(int number, long bookId);
}
