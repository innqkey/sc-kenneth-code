package com.kenneth.mvc.service;

import com.kenneth.mvc.annotation.TransactionalService;

/**
 * @auther qinkai
 * @data 2019/5/6 17:04
 */
@TransactionalService(value = "echoService-2019",txName = "myTxName") //@Service Bean + @Transactional
public class EchoService {
    public void echo(String message) {
        System.out.println(message);
    }
}
