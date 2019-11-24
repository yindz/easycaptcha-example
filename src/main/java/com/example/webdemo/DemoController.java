package com.example.webdemo;

import com.apifan.framework.captcha.component.CaptchaHelper;
import com.apifan.framework.captcha.vo.CaptchaInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 演示
 *
 * @author yin
 */
@RequestMapping("/demo")
@Controller
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private CaptchaHelper captchaHelper;

    /**
     * 采用随机汉字
     *
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/render")
    public String render(ModelMap modelMap) {
        //采用随机汉字
        setImageSrc(modelMap, captchaHelper.drawRandomChinese(4), "imgAddr1");

        //采用随机成语
        setImageSrc(modelMap, captchaHelper.drawRandomChineseIdiom(), "imgAddr2");

        //采用随机字符
        setImageSrc(modelMap, captchaHelper.drawRandomAlphanumeric(false, 4), "imgAddr3");

        //采用随机字符
        setImageSrc(modelMap, captchaHelper.drawRandomMathEquation(), "imgAddr4");
        return "image";
    }

    private void setImageSrc(ModelMap modelMap, CaptchaInfoVO vo, String var){
        if(modelMap == null || vo == null){
            return;
        }
        logger.info("正确的验证码为: {}", vo.getText());
        modelMap.addAttribute(var, "data:image/png;base64," + vo.getImageBase64());
    }
}
