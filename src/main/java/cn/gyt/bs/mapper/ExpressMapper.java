package cn.gyt.bs.mapper;

import cn.gyt.bs.entity.Express;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExpressMapper {

    /**
     * 查询所有物流公司
     *
     * @return 物流公司 {@link List}
     */
    List<Express> findExpress();
}
