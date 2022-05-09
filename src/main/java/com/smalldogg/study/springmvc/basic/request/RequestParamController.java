package com.smalldogg.study.springmvc.basic.request;

import com.smalldogg.study.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("okay");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * required, primitive type variable (500 ERROR)
     *
     * @param username
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * defaultValue, 빈문자로 파라미터 값이 넘어올 경우 defaultValue로 치환
     *
     * @param username
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * Map으로 요청파라미터 전체를 전달 받아.
     *
     * @return
     * @Param paramMap
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /**
     * MultiValueMap으로 한개의 파라미터에 여러값을 전달 받아.
     * 보통, 파라미터의 값은 하나만 전달받게 함.
     * 실무에서 잘 사용하지 않는 케이스
     *
     * @return
     * @Param paramMap
     */
    @ResponseBody
    @RequestMapping("/request-param-multimap")
    public String requestParamMap(
            @RequestParam MultiValueMap<String, Object> multiValueMap) {
        log.info("username={}, age={}", multiValueMap.get("username"), multiValueMap.get("age"));
        return "ok";
    }

    /**
     * @ModelAttribute 사용
     * 요청 파라미터의 이름으로 HelloData 객체 프로퍼티를 찾고,
     * setter를 호출하여 파라미터의 값을 입력(바인딩)한다.
     * 예시에서 setUsername(), setAge()
     * 옳바르지 않는 데이터 타입이 전달될 경우, BindException이 발생된다. 검증 로직 추가 필요!
     * @param helloData
     * @return
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData ={}",helloData);
        return "ok";
    }

    /**
     * @ModelAttribute 애너테이션 생락
     * Argument resolver로 지정해둔 타입이 아니며, 일반 데이터 타입이 아닌 경우에 생략하였을 때, ModelAttribute로 처리됨
     * 내가 정의한 클래스들은 전부 ModelAttribute 정도로 이해하면 됨.
     * @param helloData
     * @return
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData ={}",helloData);
        return "ok";
    }
}
