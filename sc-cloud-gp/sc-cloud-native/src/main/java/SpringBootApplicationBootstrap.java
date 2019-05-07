import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther qinkai
 * @data 2019/5/7 12:17
 */
@EnableAutoConfiguration
@RestController
public class SpringBootApplicationBootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("Kenneth");
        //在kenneth 中清册一个hello world string 类型的bean
        parentContext.registerBean("helloWorld",String.class,"qinn");
        //启动kenneth 上下文
        parentContext.refresh();

        new SpringApplicationBuilder(SpringBootApplicationBootstrap.class)
                .parent(parentContext)//显示的设置双亲上下文
                .run(args);
    }

    @Autowired
    @Qualifier("helloWorld")  //bean 名称
    private String message;

    @RequestMapping("")
    public String index() {
        return message;
    }
}
