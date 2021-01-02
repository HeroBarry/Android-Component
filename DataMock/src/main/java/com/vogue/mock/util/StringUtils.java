package com.vogue.mock.util;

/**
 */
public class StringUtils {
    /**
     *  The String Is Empty Or Null Will Be Return False,Else Return True.
     * @param str d
     * @return boolean d
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
    /**
     *  The String Is Empty Or Null Will Be Return True,Else Return False.
     * @param str d
     * @return boolean d
     */
    public static boolean isEmpty(String str){
        return str==null || str.length()==0;
    }
}
