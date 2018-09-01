package com.wzy.util;

import com.wzy.util.exception.MyExceptionUtil;

import java.io.*;

public class SerializableUtil<T> {

    /**
     * 获取一个对象的序列话信息
     * @param t
     * @return
     */
    public String getObjectSerializableSting(T t) throws IOException {

        // 返回数据
        String objBody = null;
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)){
                objectOutputStream.writeObject(t);
                byte[] bytes = byteArrayOutputStream.toByteArray();
                objBody = new String(bytes,"ISO-8859-1");
            }

        }
        return objBody;
    }

    /**
     * 反序列获得对象信息
     * @param str
     * @return
     */
    public T getStringSerializableObject(String str) throws UnsupportedEncodingException {
        byte[] bytes = str.getBytes("ISO-8859-1");
        T obj = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes))){
            obj = (T) inputStream.readObject();
        } catch (IOException e) {
            MyExceptionUtil.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            MyExceptionUtil.error(e.getMessage());
        }
        return obj;
    }
}
