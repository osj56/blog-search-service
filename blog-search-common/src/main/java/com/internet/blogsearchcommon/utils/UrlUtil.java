package com.internet.blogsearchcommon.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlUtil {
    public static String URLEncoder(String word) throws UnsupportedEncodingException {
        return URLEncoder.encode(word, "utf-8");
    }
}
