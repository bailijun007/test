package com.blj.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 使用SPI的机制:
 * 1、 当服务提供者提供了接口的一种具体实现后，
 * 2、在工程中的META- INF/services目录下创建一个以“接口全限定名”为命名的文件，内容为实现类的全限定名:
 * 3、接口实现类所在的工程Classpath中:
 * 4、主程序通过java，util. ServiceLoder动态装载实现模块，它通过扫描META-INF/services 目录下的配置文件找到实现类的全限定名
 * 5、SPI的实现类必领携带个不带参数的构造方法:
 *
 * @author BaiLiJun on 2020/6/12
 */
public class SpiMainClass {

    public static void main(String[] args){

        ServiceLoader<IParseFile> load = ServiceLoader.load(IParseFile.class);

        Iterator<IParseFile> iterator = load.iterator();
        while (iterator.hasNext()){
            iterator.next().Parse();
        }
    }
}
