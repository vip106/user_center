package com.github.you.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/8 14:36
 */
public class CheckString {

    /**
     * 判断是否含有特殊字符
     *
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

}
