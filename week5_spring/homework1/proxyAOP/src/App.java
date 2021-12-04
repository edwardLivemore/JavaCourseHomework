import factory.JdkProxyFactory;
import service.ISmsService;
import service.impl.SmsServiceImpl;

public class App {
    public static void main(String[] args) {
        ISmsService service = (ISmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        service.sendMsg();
    }
}
