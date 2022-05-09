package com.smalldogg.study.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        return new ModelAndView("response/hello")
                .addObject("data", "hello!");
    }

    /**
     * 반환 타입이 String일 경우,
     *  - View Resolver가 실행되어 반환된 String에 해당하는 View를 찾고 렌더링.
     *  - @ResponseBody 애너테이션이 있으면, 반환되는 문자열을 Response Body에 그대로 넣어 반환함.(주의)
     * @param model
     * @return
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello";
    }

    /**
     * 권장하지 않음
     * @Controller 애너테이션을 사용하고, HttpservletResponse, OutputStream(Writer) 같은 HTTP 메시지 바디를 처리하는 파라미터가 없을 경우 논리뷰 이름으로 ㅅ용
     * @param model
     */
    //
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
