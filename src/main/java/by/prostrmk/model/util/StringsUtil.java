package by.prostrmk.model.util;


import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class StringsUtil {

    public static String hash(String str){
        return DigestUtils.md5Hex(str);
    }

    public static String getStringFromCollection(List list){
        StringBuilder sb = new StringBuilder();
        for (Object o : list) {
            sb.append(o.toString()).append(" ");
        }
        return sb.toString();
    }

}
