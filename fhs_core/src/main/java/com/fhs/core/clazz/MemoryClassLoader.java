package com.fhs.core.clazz;

import com.fhs.common.utils.Logger;
import com.fhs.core.config.EConfig;
import com.fhs.core.exception.BusinessException;

import javax.tools.*;
import javax.tools.JavaFileObject.Kind;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.CharBuffer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  把一段Java字符串变成类
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.core.clazz
 * @ClassName: MemoryClassLoader
 * @Author: JackWang
 * @CreateDate: 2018/12/5 0005 20:26
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/5 0005 20:26
 * @Version: 1.0
 */
public class MemoryClassLoader extends URLClassLoader {

    private static final Logger LOG = Logger.getLogger(MemoryClassLoader.class);

    private Map<String, byte[]> classBytes = new ConcurrentHashMap<>();

    /**
     * 单利默认的
     */
    private static final MemoryClassLoader defaultLoader = new MemoryClassLoader();

    public MemoryClassLoader(){ super(new URL[0], MemoryClassLoader.class.getClassLoader());}

    /**
     * 获取默认的类加载器
     * @return 类加载器对象
     */
    public static  MemoryClassLoader getInstrance(){
        return defaultLoader;
    }

    /**
     * 注册Java 字符串到内存类加载器中
     * @param className 类名字
     * @param javaStr Java字符串
     */
    public void registerJava(String className,String javaStr)
    {
        try
        {
            this.classBytes.putAll(compile( className,  javaStr) );
        }catch(Exception e)
        {
            LOG.error("注册Java代码错误:"+javaStr);
            LOG.error("注册Java代码错误:",e);
        }

    }

    private static Map<String, byte[]> compile(String className, String javaStr) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager stdManager = compiler.getStandardFileManager(null, null, null);
        try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
            JavaFileObject javaFileObject = manager.makeStringSource(className, javaStr);
            Iterable<String> options  = null;
            if(EConfig.getPathPropertiesValue("memoryClassLoaderClassPath")!=null && new File(EConfig.getPathPropertiesValue("memoryClassLoaderClassPath")).exists())
            {
                options = Arrays.asList( "-classpath", getJarFiles(EConfig.getPathPropertiesValue("memoryClassLoaderClassPath")) );
            }
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, options, null, Arrays.asList(javaFileObject));
            if (task.call())
            {
                return manager.getClassBytes();
            }
        } catch (IOException e) {
            LOG.error("编译java出错",e);
            throw new BusinessException("编译java出错" + javaStr);
        }
        return null;
    }

    /**
     * 根据一个jar的path获取下面所有的jar放到
     * @param jarPath jar所在的路径
     * @return 所有的jar
     * @throws Exception
     */
    private static String getJarFiles(String jarPath)  {
        if(jarPath==null)
        {
            return "";
        }
        String os = System.getProperty("os.name");
        final String split = os.toLowerCase().startsWith("win") ? ";" : ":";
        File sourceFile = new File(jarPath);
        StringBuilder jars= new StringBuilder();
        if (sourceFile.exists()) {// 文件或者目录必须存在
            if (sourceFile.isDirectory()) {// 若file对象为目录
                // 得到该目录下以.jar结尾的文件或者目录
                File[] childrenFiles = sourceFile.listFiles(new FileFilter() {
                    public boolean accept(File pathname) {
                        if (pathname.isDirectory()) {
                            return true;
                        } else {
                            String name = pathname.getName();
                            if (name.endsWith(".jar") ? true : false) {
                                jars.append(pathname.getPath() + split);
                                return true;
                            }
                            return false;
                        }
                    }
                });
            }
        }
        return jars.toString();
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] buf = classBytes.get(name);
        if (buf == null) {
            return super.findClass(name);
        }
        classBytes.remove(name);
        return defineClass(name, buf, 0, buf.length);
    }

    /**
     * 开放findClass 给外部使用
     * @param name classname
     * @return class对象
     */
    public Class<?> getClass(String name)throws ClassNotFoundException {
        return this.findClass(name);
    }
}


/**
 *  内存Java文件管理器
 */
class MemoryJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    // compiled classes in bytes:
    final Map<String, byte[]> classBytes = new HashMap<String, byte[]>();

    final Map<String, List<JavaFileObject>> classObjectPackageMap = new HashMap<>();

    MemoryJavaFileManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    public Map<String, byte[]> getClassBytes() {
        return new HashMap<String, byte[]>(this.classBytes);
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
        classBytes.clear();
    }

    @Override
    public Iterable<JavaFileObject> list(Location location,
                                         String packageName,
                                         Set<Kind> kinds,
                                         boolean recurse)
            throws IOException
    {
        Iterable<JavaFileObject> it = super.list(location, packageName, kinds, recurse);

        if (kinds.contains(Kind.CLASS)) {
            final List<JavaFileObject> javaFileObjectList = classObjectPackageMap.get(packageName);
            if (javaFileObjectList != null) {
                if (it != null) {
                    for (JavaFileObject javaFileObject : it) {
                        javaFileObjectList.add(javaFileObject);
                    }
                }
                return javaFileObjectList;
            } else {
                return it;
            }
        } else {
            return it;
        }
    }

    @Override
    public String inferBinaryName(Location location, JavaFileObject file) {
        if (file instanceof MemoryInputJavaClassObject) {
            return ((MemoryInputJavaClassObject)file).inferBinaryName();
        }
        return super.inferBinaryName(location, file);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String className, Kind kind,
                                               FileObject sibling) throws IOException {
        if (kind == Kind.CLASS) {
            return new MemoryOutputJavaClassObject(className);
        } else {
            return super.getJavaFileForOutput(location, className, kind, sibling);
        }
    }

    JavaFileObject makeStringSource(String className, final String code) {
        String classPath = className.replace('.', '/') + Kind.SOURCE.extension;

        return new SimpleJavaFileObject(URI.create("string:///" + classPath), Kind.SOURCE) {
            @Override
            public CharBuffer getCharContent(boolean ignoreEncodingErrors) {
                return CharBuffer.wrap(code);
            }
        };
    }

    void makeBinaryClass(String className, final byte[] bs) {
        JavaFileObject javaFileObject = new MemoryInputJavaClassObject(className, bs);

        String packageName = "";
        int pos = className.lastIndexOf('.');
        if (pos > 0) {
            packageName = className.substring(0, pos);
        }
        List<JavaFileObject> javaFileObjectList = classObjectPackageMap.get(packageName);
        if (javaFileObjectList == null) {
            javaFileObjectList = new LinkedList<>();
            javaFileObjectList.add(javaFileObject);

            classObjectPackageMap.put(packageName, javaFileObjectList);
        } else {
            javaFileObjectList.add(javaFileObject);
        }
    }

    class MemoryInputJavaClassObject extends SimpleJavaFileObject {
        final String className;
        final byte[] bs;

        MemoryInputJavaClassObject(String className, byte[] bs) {
            super(URI.create("string:///" + className.replace('.', '/') + Kind.CLASS.extension), Kind.CLASS);
            this.className = className;
            this.bs = bs;
        }

        @Override
        public InputStream openInputStream() {
            return new ByteArrayInputStream(bs);
        }

        public String inferBinaryName() {
            return className;
        }
    }

    class MemoryOutputJavaClassObject extends SimpleJavaFileObject {
        final String className;

        MemoryOutputJavaClassObject(String className) {
            super(URI.create("string:///" + className.replace('.', '/') + Kind.CLASS.extension), Kind.CLASS);
            this.className = className;
        }

        @Override
        public OutputStream openOutputStream() {
            return new FilterOutputStream(new ByteArrayOutputStream()) {
                @Override
                public void close() throws IOException {
                    out.close();
                    ByteArrayOutputStream bos = (ByteArrayOutputStream) out;
                    byte[] bs = bos.toByteArray();
                    classBytes.put(className, bs);
                    makeBinaryClass(className, bs);
                }
            };
        }
    }
}
