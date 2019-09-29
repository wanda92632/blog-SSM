package top.wanda92632.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wanda92632.blog.util.UploadUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhiFei
 */
@Controller
public class UploadController {


    /**
     * 获取七牛云上传凭证
     * @return
     */
    @RequestMapping(value = "/getUptoken",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUptoken(){
        Map<String,Object> modelMap= new HashMap<>();
        String uptoke = UploadUtil.getUpToken();
        modelMap.put("uptoke",uptoke);
        return modelMap;
    }
}
