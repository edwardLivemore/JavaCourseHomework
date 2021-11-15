import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class XClassLoader extends ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        XClassLoader classLoader = new XClassLoader();
        Class<?> clazz = classLoader.loadClass("Hello");
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Method method = instance.getClass().getMethod("hello");
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        // 如果有包名, 则转换成路径
        String path = name.replace(".", "/");

        // 获取文件输入流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path + ".xlass");
        if(inputStream != null){
            try {
                int size = inputStream.available();
                byte[] bytes = new byte[size];
                inputStream.read(bytes);
                // 转换
                byte[] classBytes = decode(bytes);
                return defineClass(name, classBytes, 0, classBytes.length);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ClassNotFoundException(name, e);
            }finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private byte[] decode(byte[] bytes){
        byte[] result = new byte[bytes.length];
        for(int i = 0; i < bytes.length; i++){
            // 解码 x = 255 - x
            result[i] = (byte) (255 - bytes[i]);
        }
        return result;
    }

}
