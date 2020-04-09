package cn.gyt.bs.service;

import cn.gyt.bs.entity.Express;

import java.util.List;

public interface ExpressService {

    /**
     * 查询所有物流公司
     *
     * @return 物流公司 {@link List}
     */
    List<Express> findExpress();
}
