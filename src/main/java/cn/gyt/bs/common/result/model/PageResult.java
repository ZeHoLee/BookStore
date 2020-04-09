package cn.gyt.bs.common.result.model;

/**
 * @author Administrator
 */
public class PageResult<S, T> extends BaseResult<T> {

    /**
     * 状态码
     */
    private S code;

    /**
     * 数据总条数
     */
    private Long total;

    /**
     * 每页条数
     */
    private Integer size;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 当前页
     */
    private Long current;

    /**
     * 无参构造方法
     */
    public PageResult() {
    }

    /**
     * 构造方法
     *
     * @param message 描述信息
     * @param data    数据
     * @param total   数据总条数
     * @param size    每页显示条数
     * @param pages   总页数
     * @param current 当前页
     */
    public PageResult(String message, T data, Long total, Integer size, Long pages, Long current) {
        super(message, data);
        this.total = total;
        this.size = size;
        this.pages = pages;
        this.current = current;
    }

    /**
     * 构造方法
     *
     * @param message 描述信息
     * @param data    数据
     */
    public PageResult(String message, T data) {
        super(message, data);
    }

    /**
     * 构造方法
     *
     * @param message 描述信息
     */
    public PageResult(S code,String message) {
        super(message);
        this.code = code;
    }

    public S getCode() {
        return code;
    }

    public Long getTotal() {
        return total;
    }

    public Integer getSize() {
        return size;
    }

    public Long getPages() {
        return pages;
    }

    public Long getCurrent() {
        return current;
    }

    /**
     * 重写 toString()
     *
     * @return 有值的参数拼接成的一个字符串
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PageResult {");
        // success
        stringBuilder.append("\"success\"").append(":").append(super.isSuccess())
                .append(", ");
        // message
        stringBuilder.append("\"message\"").append(":")
                .append("\"").append(super.isSuccess()).append("\"");
        // data
        if (super.getData() != null) {
            stringBuilder.append(", ").append("\"data\"").append(":")
                    .append("\"").append(super.getData()).append("\"");
        }
        // total
        if (total != null) {
            stringBuilder.append(", ").append("\"total\"").append(":").append(total);
        }
        // size
        if (size != null) {
            stringBuilder.append(", ").append("\"size\"").append(":").append(size);
        }
        // pages
        if (pages != null) {
            stringBuilder.append(", ").append("\"pages\"").append(":").append(pages);
        }
        // current
        if (current != null) {
            stringBuilder.append(", ").append("\"current\"").append(":").append(current);
            stringBuilder.append("}");
        }
        return stringBuilder.toString();
    }

}
