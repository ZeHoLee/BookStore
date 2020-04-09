package cn.gyt.bs.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class Book {

    /**
     * 国际标准书号
     */
    private String isbn;

    /**
     * 图书编号
     */
    private String bookId;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String publish;

    /**
     * 出版日期
     */
    private String publishDate;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 单价
     */
    private float price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 销量
     */
    private Integer buyCount;

    /**
     * 封面图片路径
     */
    private String imageUrl;
}
