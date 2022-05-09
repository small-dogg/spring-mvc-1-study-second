package com.smalldogg.study.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("okay");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("okay");
    }

    /**
     * HttpEntity : HTTP Header, body 정보를 편리하게 조회
     * RequestEntity, ResponseEntity를 사용하면, HttpEntity보다 확장된 기능을 사용할 수 있음
     * @param httpEntity
     * @return
     * @throws IOException
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("okay");
    }


    /**
     * @RequestBody를 사용하면, Http Body 정보를 바로 불러올 수 있음
     * @RequestHeader를 사용하면, Http 헤더 정보를 바로 불러올 수 있음
     * @ResponseBody를 사용하면 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달할 수 있음
     * @param messageBody
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);

        return "okay";
    }

    /**
     * 요청 파라미터 vs HTTP 메시지 바디
     *   요청 파라미터를 조회하는 기능: @RequestParam, @ModelAttribute
     *   HTTP 메시지 바디를 직접 조회하는 기능: @RequestBody
     */
}
