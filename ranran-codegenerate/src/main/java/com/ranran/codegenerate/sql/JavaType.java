package com.ranran.codegenerate.sql;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zengrui on 2017/6/10.
 */
public class JavaType {

    public static Map<Integer, Class<?>> jdbcJavaTypes = new HashMap();
    public static Map<Integer, String> mapping = new HashMap();
    public static Map<String, Integer> jdbcTypeNames = new HashMap();
    static int majorJavaVersion = 15;
    public static final String UNKNOW = "UNKNOW";
    public static final String SPECIAL = "SPECIAL";

    public JavaType() {
    }

    public static boolean isDateType(Integer sqlType) {
        return sqlType.intValue() == 91 || sqlType.intValue() == 92 || sqlType.intValue() == 2013 || sqlType.intValue() == 93 || sqlType.intValue() == 2014;
    }

    public static boolean isInteger(Integer sqlType) {
        return sqlType.intValue() == 16 || sqlType.intValue() == -7 || sqlType.intValue() == 4 || sqlType.intValue() == -6 || sqlType.intValue() == 5;
    }

    public static String getType(Integer sqlType, Integer size, Integer digit) {
        String type = (String)mapping.get(sqlType);
        return type.equals("SPECIAL")?(digit != null && digit.intValue() != 0?"Double":(size.intValue() >= 9?"Long":"Integer")):type;
    }

    public static boolean isJavaNumberType(int jdbcType) {
        Class type = (Class)jdbcJavaTypes.get(Integer.valueOf(jdbcType));
        return type == null?false:Number.class.isAssignableFrom(type);
    }

    static {
        String fields = System.getProperty("java.version");
        if(fields.contains("1.9.")) {
            majorJavaVersion = 19;
        } else if(fields.contains("1.8.")) {
            majorJavaVersion = 18;
        } else if(fields.contains("1.7.")) {
            majorJavaVersion = 18;
        } else if(fields.contains("1.6.")) {
            majorJavaVersion = 16;
        } else {
            majorJavaVersion = 15;
        }

        jdbcJavaTypes.put(new Integer(-16), String.class);
        jdbcJavaTypes.put(new Integer(-15), String.class);
        jdbcJavaTypes.put(new Integer(-9), String.class);
        jdbcJavaTypes.put(new Integer(-8), String.class);
        jdbcJavaTypes.put(new Integer(-7), Boolean.class);
        jdbcJavaTypes.put(new Integer(-6), Integer.class);
        jdbcJavaTypes.put(new Integer(-5), Long.class);
        jdbcJavaTypes.put(new Integer(-4), byte[].class);
        jdbcJavaTypes.put(new Integer(-3), byte[].class);
        jdbcJavaTypes.put(new Integer(-2), byte[].class);
        jdbcJavaTypes.put(new Integer(-1), String.class);
        jdbcJavaTypes.put(new Integer(1), String.class);
        jdbcJavaTypes.put(new Integer(2), BigDecimal.class);
        jdbcJavaTypes.put(new Integer(3), BigDecimal.class);
        jdbcJavaTypes.put(new Integer(4), Integer.class);
        jdbcJavaTypes.put(new Integer(5), Integer.class);
        jdbcJavaTypes.put(new Integer(6), BigDecimal.class);
        jdbcJavaTypes.put(new Integer(7), BigDecimal.class);
        jdbcJavaTypes.put(new Integer(8), BigDecimal.class);
        jdbcJavaTypes.put(new Integer(12), String.class);
        jdbcJavaTypes.put(new Integer(16), Boolean.class);
        jdbcJavaTypes.put(new Integer(91), Date.class);
        jdbcJavaTypes.put(new Integer(92), Time.class);
        jdbcJavaTypes.put(new Integer(93), Timestamp.class);
        jdbcJavaTypes.put(new Integer(1111), Object.class);
        jdbcJavaTypes.put(new Integer(2004), byte[].class);
        jdbcJavaTypes.put(new Integer(2005), String.class);
        jdbcJavaTypes.put(new Integer(2009), SQLXML.class);
        jdbcJavaTypes.put(new Integer(2011), String.class);
        mapping.put(Integer.valueOf(-5), "Long");
        mapping.put(Integer.valueOf(-2), "byte[]");
        mapping.put(Integer.valueOf(-7), "Integer");
        mapping.put(Integer.valueOf(2004), "byte[]");
        mapping.put(Integer.valueOf(16), "Integer");
        mapping.put(Integer.valueOf(1), "String");
        mapping.put(Integer.valueOf(2005), "String");
        mapping.put(Integer.valueOf(70), "UNKNOW");
        mapping.put(Integer.valueOf(91), "Date");
        mapping.put(Integer.valueOf(3), "SPECIAL");
        mapping.put(Integer.valueOf(2001), "UNKNOW");
        mapping.put(Integer.valueOf(8), "Double");
        mapping.put(Integer.valueOf(6), "Float");
        mapping.put(Integer.valueOf(4), "Integer");
        mapping.put(Integer.valueOf(2000), "UNKNOW");
        mapping.put(Integer.valueOf(-16), "String");
        mapping.put(Integer.valueOf(-4), "byte[]");
        mapping.put(Integer.valueOf(-1), "String");
        mapping.put(Integer.valueOf(-15), "String");
        mapping.put(Integer.valueOf(-9), "String");
        mapping.put(Integer.valueOf(2011), "String");
        mapping.put(Integer.valueOf(0), "UNKNOW");
        mapping.put(Integer.valueOf(2), "SPECIAL");
        mapping.put(Integer.valueOf(1111), "Object");
        mapping.put(Integer.valueOf(7), "Double");
        mapping.put(Integer.valueOf(2006), "UNKNOW");
        mapping.put(Integer.valueOf(5), "Integer");
        mapping.put(Integer.valueOf(2009), "SQLXML");
        mapping.put(Integer.valueOf(2002), "UNKNOW");
        mapping.put(Integer.valueOf(92), "Date");
        mapping.put(Integer.valueOf(93), "Timestamp");
        mapping.put(Integer.valueOf(-6), "Integer");
        mapping.put(Integer.valueOf(-3), "byte[]");
        mapping.put(Integer.valueOf(12), "String");
        if(majorJavaVersion >= 18) {
            mapping.put(Integer.valueOf(2012), "UNKNOW");
            mapping.put(Integer.valueOf(2014), "Timestamp");
            mapping.put(Integer.valueOf(2013), "Timestamp");
        }

        Field[] fieldss = Types.class.getFields();
        int i = 0;

        for(int len = fieldss.length; i < len; ++i) {
            if(Modifier.isStatic(fieldss[i].getModifiers())) {
                try {
                    String e = fieldss[i].getName().toLowerCase();
                    Integer value = (Integer)fieldss[i].get(Types.class);
                    jdbcTypeNames.put(e, value);
                } catch (IllegalArgumentException var5) {
                    var5.printStackTrace();
                } catch (IllegalAccessException var6) {
                    var6.printStackTrace();
                }
            }
        }

    }
}
