## contact me ##
chenke lingqi1818@gmail.com

## version ##
the current version is 0.1

you can download from:

http://pan.baidu.com/share/link?shareid=505711&uk=3255103779

## destination ##
easy to create a restful style java web project.

you can develop a tradition url style such as codeanywhere.org/test?a=1&b=2

## technical detail ##
just use servlet

only the velocity is the default template

in dev step you can depedent the jetty

also you can use spring

## easy to dev ##
if you want to debug in dev step.you can start java app with follow main class.
```
org.codeanywhere.easyRestful.base.main.EasyRestfulMain 7001 test.com
```

if you want to use in your java web app.please copy follow code into web.xml file.
```
 <listener>
    <listener-class>org.codeanywhere.easyRestful.base.listener.ERStartListener</listener-class>
    </listener>
<servlet>
     <servlet-name>easyRestful</servlet-name>
     <servlet-class>org.codeanywhere.easyRestful.base.servlet.ERServlet</servlet-class>
</servlet>
    
 <servlet-mapping>
    <servlet-name>easyRestful</servlet-name>
    <url-pattern>/*</url-pattern>
 </servlet-mapping>
```

## config file ##
you must put a easyRestful.properties in your java classpath
```
spring.enable=true ##use spring if true
resource.path=/Users/chenke/Documents/javawork/easy-restful/demo/src/main/webapp ##resource path the velocity template must in this path named vm
action.package=org.codeanywhere.easyRestful.demo.action ##action base package
url.match.index=1 ##if the url is www.test.com/a/b/c/d if index=1 find the action from xxx.a.b.c
##if index=2 find the action from xxx.b.c.d and so on...
```

## url specification ##
```

     * analysis  the follow 4 url type:
     * a)/xxx/xxx/xxx/resource/list/start/end list all resource start:start index end:end index
     * b)/xxx/xxx/xxx/resource/id/detail list the resource with id
     * c)/xxx/xxx/xxx/action/method/attr/a1/a2/a3/a4 execute method int the action the parameter is a1 a2 a3 a4 and so on..
     * d)/xxx/xxx/xxx/action/ execute the action method name is execute.
```

## detail example ##
our default easyRestful.properties content is:
```
spring.enable=true
resource.path=/test/
action.package=test.action
url.match.index=1
```


example:
```
package test.action.hello;
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

    @Json
    public JsonObject testJson(String s1, String s2, String s3, String s4) {
        JsonObject o = new JsonObject();
        o.setA("1");
        o.setB("2");
        return o;
    }

    @Jsonp(callbackMethodName = "callback")
    public JsonObject testJsonp(String s1, String s2, String s3, String s4) {
        JsonObject o = new JsonObject();
        o.setA("1");
        o.setB("2");
        return o;
    }

    private class JsonObject {
        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

    }

}
```

you can put velocity template in:
```
/test/vm/
```

my demo put the:

/test/vm/hello.vm

/test/vm/list.vm

/test/vm/test.vm

/test/vm/detail.vm


the current visit url is:

http://domain:port/hello/hello

http://domain:port/hello/hello/{id}/detail

http://domain:port/hello/hello/list/{start}/{end}

http://domain:port/hello/hello/test/attr/{attr1}/{attr2}/{attr3}/{attr4}

if the hello.vm is:
```
hello ${fish}
```

if you visit http://domain:port/hello/hello the result is:

hello i'm a gold fish !

### json&jsonp support ###
if you visit http://domain:port/hello/hello/testjosn/attr/1/2/3/4 the result is:

{"a":"1","b":"2"}

if you visit http://domain:port/hello/hello/testjosnp/attr/1/2/3/4 the result is:

callback=({"a":"1","b":"2"});


## notice ##
use @Request anotation on the action attribute you can get the follow object:

HttpServletRequest

HttpServletResponse

use @SpringBean you can get a spring bean from spring applicationContent.

use @Json on the action method the  result can change to json format

use@Jsonp on the action method the result can change to jsonp format.the argment callbackMethodName is the javascript callback method name.

you can look the demo project get more help:

http://easy-restful.googlecode.com/svn/trunk/demo

### TODO ###
groovy support