package cn.gyt.bs.controller;

import cn.gyt.bs.common.result.model.ApiResult;
import cn.gyt.bs.entity.Express;
import cn.gyt.bs.service.ExpressService;
import cn.gyt.bs.util.ApiResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/express")
public class ExpressController {

    @Resource
    private ExpressService expressService;

    @GetMapping("/findExpress")
    @ResponseBody
    public ApiResult findExpress() {
        List<Express> list = expressService.findExpress();
        return ApiResultUtils.success(list);
    }
}
