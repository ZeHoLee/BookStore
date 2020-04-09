package cn.gyt.bs.service.impl;

import cn.gyt.bs.entity.Express;
import cn.gyt.bs.mapper.ExpressMapper;
import cn.gyt.bs.service.ExpressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExpressServiceImpl implements ExpressService {

    @Resource
    private ExpressMapper expressMapper;

    /**
     * 查询所有物流公司
     *
     * @return 物流公司 {@link List}
     */
    @Override
    public List<Express> findExpress() {
        return expressMapper.findExpress();
    }
}
