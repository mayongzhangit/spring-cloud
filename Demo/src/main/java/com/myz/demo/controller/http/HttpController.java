package com.myz.demo.controller.http;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/13 11:51
 * @email 2641007740@qq.com
 */
@RestController
@RequestMapping("/http")
public class HttpController {

    /**
     * 客户端设置Content-Length
     * 默认情况：param=xiaomage Content-Length = 实际请求体长度 14
     *      curl -XPOST http://localhost:6666/http/content-length-set-by-client -d "param=xiaomage"
     *      立刻返回：xiaomage
     * 场景一： 【客户端】设置Content-Length < 实际请求体长度
     *      curl -XPOST http://localhost:6666/http/content-length-set-by-client -d "param=xiaomage" -H "Content-Length:12"
     *      立刻返回：xiaoma
     *      但是第二次请求就 Request method 'gePOST' not supported"  因为默认是长连接，ge还在缓冲区中，这次请求给戴上了
     * 场景二： 【客户端】设置Content-Length > 实际请求体长度
     *      curl -XPOST http://localhost:6666/http/content-length-set-by-client -d "param=xiaomage" -H "Content-Length:16" --max-time 10
     *      客户端（在POSTman显示sending request） 60秒之后提示 status 408 Request Timed Out  The client failed to send a complete request on this NEW connection before the timeout period elapsed; 339 bytes were read from client.
     * @param param
     * @return
     */
    @PostMapping("/content-length-set-by-client")
    public ResponseEntity<String> contentLengthSetByClient(@RequestParam("param") String param){
        return ResponseEntity.ok(param);
    }

    private AtomicInteger counter = new AtomicInteger(0);
    /**
     *
     * @param param
     * @return
     */
    @PostMapping("/content-length-set-by-server")
    public ResponseEntity<?> contentLengthSetByServer(@RequestParam("param") String param){
//        if (counter.getAndIncrement() %3 == 1){
//            response.setContentLength(param.length()-2);
//        }else if (counter.getAndIncrement() %3 == 2){
//            response.setContentLength(param.length()+2);
//        }else {
//            response.setContentLength(param.length());
//        }
//        return ResponseEntity.ok(param);
        int length = 0;
        if (counter.get() %2 == 1){
            length = param.length()-2;
        }else {
            length = param.length();
        }
        counter.incrementAndGet();
        return ResponseEntity.status(HttpStatus.OK).contentLength(length).body(param);
    }

    /**
     *
     * @param param
     * @return
     */
    @PostMapping("/content-length-set-by-server-long")
    public ResponseEntity<?> contentLengthSetByServerLong(@RequestParam("param") String param){
        int length = param.length()+2;
        counter.incrementAndGet();
        return ResponseEntity.status(HttpStatus.OK).contentLength(length).body(param);
    }
}
