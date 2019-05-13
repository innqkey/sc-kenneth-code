package com.kenneth.service.feign;

import com.kenneth.annotation.RestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 自定义实现Feign
 * @auther qinkai
 * @data 2019/5/9 18:30
 */
@RestClient(name = "${saying.rest.service.name}")
public interface SayingRestService {

    @GetMapping("/say")
    String say(@RequestParam("message") String message);

    /*public static void main(String[] args) throws Exception{
        Method method = SayingService.class.getMethod("say", String.class);
        Parameter parameter = method.getParameters()[0];
        System.out.println(parameter.getName());
        parameter.isNamePresent();
        DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();
        Stream.of(nameDiscoverer.getParameterNames(method))
                .forEach(System.out::println);
    }*/
}
