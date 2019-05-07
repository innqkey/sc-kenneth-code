package com.kenneth.mvc.controller;

import com.kenneth.mvc.annotation.OptionsMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther qinkai
 * @data 2019/5/6 17:59
 */
@Controller
public class CachedRestController {

    @RequestMapping
    @ResponseBody
    public String helloWorld() {
        return "Hello,World";
    }

    @RequestMapping("/cache")
    @OptionsMapping(name = "aa")
    public ResponseEntity<String> cachedHelloWorld(
            @RequestParam(required = false,defaultValue = "true")boolean cached){
        if(cached){
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }else{
            return ResponseEntity.ok("hello world");
        }

    }
}
