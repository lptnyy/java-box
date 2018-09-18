package com.wzy.util.annotation.factory;


import com.wzy.util.Md5Util;
import com.wzy.util.RegexUtil;
import com.wzy.util.annotation.check.*;
import com.wzy.util.annotation.set.SetStringMd5;
import com.wzy.util.exception.MyExceptionUtil;
import com.wzy.util.jsonvo.JsonVo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Verification {

    /**
     * 验证参数
     * @param header
     * @return
     */
    public static JsonVo verification(Object header) {
        JsonVo jsonVo = new JsonVo();
        try{
            jsonVo.setResult(true);
            Class entity = header.getClass();
            Field[] fields = entity.getDeclaredFields();

            // 读取带有自定义注解的属性
            List<Field> fieldList = Arrays.stream(fields)
                    .filter(field -> field.getAnnotations().length > 0)
                    .collect(Collectors.toList());

            // 遍历获取check系列注解属性
            fieldList.forEach(field -> {
                Arrays.stream(field.getAnnotations()).forEach(annotation -> {
                    String message = null;
                    try {
                        field.setAccessible(true);
                        Object value = field.get(header);
                        if (annotation.annotationType().equals(IsNotNull.class)) {
                            IsNotNull isNotNull = (IsNotNull) annotation;
                            if(!RegexUtil.isNotNull(value)) {
                                jsonVo.setResult(false);
                                jsonVo.setMsg(isNotNull.message());
                                return;
                            }
                        } else if (annotation.annotationType().equals(IsNotEmail.class)) {
                            IsNotEmail isNotEmail = (IsNotEmail) annotation;
                            if(!RegexUtil.isEmail(value)) {
                                jsonVo.setResult(false);
                                jsonVo.setMsg(isNotEmail.message());
                                return;
                            }
                        } else if (annotation.annotationType().equals(IsNotInteger.class)) {
                            IsNotInteger isNotInteger = (IsNotInteger) annotation;
                            if(!RegexUtil.isInteger(value)) {
                                jsonVo.setResult(false);
                                jsonVo.setMsg(isNotInteger.message());
                                return;
                            }
                        } else if (annotation.annotationType().equals(IsNotLong.class)) {
                            IsNotLong isNotLong = (IsNotLong) annotation;
                            if(!RegexUtil.isInteger(value)) {
                                jsonVo.setResult(false);
                                jsonVo.setMsg(isNotLong.message());
                                return;
                            }
                        } else if (annotation.annotationType().equals(IsContrastString.class)) {
                            IsContrastString isContrastString = (IsContrastString) annotation;
                            Field confiele = entity.getDeclaredField(isContrastString.contrastField());
                            confiele.setAccessible(true);
                            Object conValue = confiele.get(header);
                            if(!RegexUtil.isContrastString(value,conValue)) {
                                jsonVo.setResult(false);
                                jsonVo.setMsg(isContrastString.message());
                                return;
                            }
                        }
                    } catch (IllegalAccessException e) {
                        MyExceptionUtil.error(e.getMessage());
                    } catch (NoSuchFieldException e) {
                        MyExceptionUtil.error(e.getMessage());
                    }
                });
            });

            if (!jsonVo.isResult()) return jsonVo;

            // 遍历获取Set系列注解
            fieldList.forEach(field -> {
                Arrays.stream(field.getAnnotations()).forEach(annotation -> {
                    String message = null;
                    try {
                        field.setAccessible(true);
                        if (annotation.annotationType().equals(SetStringMd5.class)) {
                            Object conValue = field.get(header);
                            String mdfValue = Md5Util.getMD5(conValue.toString());
                            field.set(header,mdfValue);
                        }
                    } catch (IllegalAccessException e) {
                        MyExceptionUtil.error(e.getMessage());
                    }
                });
            });

        } catch (Exception e) {
            MyExceptionUtil.error(e.getMessage());
        }
        return jsonVo;
    }
}
