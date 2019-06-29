package com.standard.oauthCommon.utils;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class SerializationUtils {

    // 序列化对象并转为base64字符串
    public static String serialize(Object state) {
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(state);
            oos.flush();
            String serializeStr=bos.toString("ISO-8859-1");
            serializeStr=URLEncoder.encode(serializeStr,"UTF-8");
            return serializeStr;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 将base64反序列化为对象
    public static <T> T deserialize(String serializeStrEncoder) {
        ObjectInputStream oip = null;
        try {
            String serializeStr = URLDecoder.decode(serializeStrEncoder,"UTF-8");
            byte[] bytes =serializeStr.getBytes("ISO-8859-1");
            oip = new ObjectInputStream(new ByteArrayInputStream(bytes));
            T obj =(T) oip.readObject();
            return obj;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (oip != null) {
                try {
                    oip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
