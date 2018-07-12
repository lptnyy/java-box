package com.wzy.com.wzy.includejar.introduce;

import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态加载JAR
 */
public class BoxUrlClassLoader extends URLClassLoader {
    private List<JarURLConnection> cacheJarFiles = new ArrayList<JarURLConnection>();

    public BoxUrlClassLoader() {
        super(new URL[]{}, findParentClassLoader());
    }

    private static ClassLoader findParentClassLoader(){
        ClassLoader parent = BoxUrlClassLoader.class.getClassLoader();
        if (parent == null) {
            parent = BoxUrlClassLoader.class.getClassLoader();
        }
        if (parent == null) {
            parent = ClassLoader.getSystemClassLoader();
        }
        return parent;
    }

    public void addURLFile(URL file){
        try{
            URLConnection uc = file.openConnection();
            if (uc instanceof JarURLConnection) {
                uc.setUseCaches(true);
                ((JarURLConnection) uc).getManifest();
                cacheJarFiles.add((JarURLConnection) uc);
            }
        }catch (Exception e) {

        }
    }

    public void unloadJarFiles(){
        List<JarURLConnection> tempDel = new ArrayList<JarURLConnection>();
        for(JarURLConnection url:cacheJarFiles) {
            try {
                tempDel.add(url);
                url.getJarFile().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cacheJarFiles.removeAll(tempDel);
        tempDel = null;
    }

    public int getCacheJarCount(){
        return cacheJarFiles.size();
    }
}
