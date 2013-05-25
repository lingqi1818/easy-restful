package org.codeanywhere.easyRestful.demo.action.hello;
import org.codeanywhere.easyRestful.base.Action;
import org.codeanywhere.easyRestful.base.RequestContext;
import org.codeanywhere.easyRestful.base.annotation.Request;
import org.codeanywhere.easyRestful.base.annotation.SpringBean;
import org.codeanywhere.easyRestful.demo.service.HelloService;

public class Hello implements Action {

    @Request
    private RequestContext context;
    @SpringBean
    private HelloService   helloService;

    public void execute() {
        helloService.hello();
        context.put("fish", "i'm a gold fish !");
    }

    public void list(String start, String end) {
        System.out.println("start->" + start);
        System.out.println("end->" + end);

    }

    public void detail(String id) {
        System.out.println("detail id->" + id);
    }

    public void test(String s1, String s2, String s3, String s4) {
        execute();
        System.out.println("attr list ->");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
    }

}
