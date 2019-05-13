package com.kenneth.web.mvc;

import com.kenneth.controller.ServerController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.TimeoutException;

/**
 * 配合controller 中@GetMapping("/middle/say")方法使用
 * @auther qinkai
 * @data 2019/5/9 11:40
 */
@RestControllerAdvice(assignableTypes = ServerController.class)
public class CircuitBreakerControllerAdvice {
    @ExceptionHandler
    public void onTimeoutException(TimeoutException timeoutException, Writer writer) throws IOException {
        writer.write(errorContent(""));  //网络I/O
        writer.flush();
        writer.close();
    }

    private String errorContent(String message) {
        return "Fault";
    }
}
