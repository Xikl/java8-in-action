package com.ximo.java8.refactor.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

/**
 * @author 朱文赵
 * @date 2018/4/23 10:15
 * @description cookie工具类 只能在controller中使用
 */
public class CookieUtil {

    /**
     * 私有构造
     */
    private CookieUtil() {
    }

    /**
     * 设置cookie
     *
     * @param name   cookie名字
     * @param value  cookie值
     * @param maxAge cookie生命周期  以秒为单位
     */
    public static void set(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response().addCookie(cookie);
    }


    /**
     * 获得cookie的值
     * 采用optional重构 因为获得可能为空
     *
     * @param name cookie的名字
     * @return cookie
     */
    public static Optional<Cookie> get(String name) {
        return Optional.ofNullable(readCookieMap(request()).get(name));
    }

    /**
     * 读取cookie  -> map<String, Cookie>
     * 采用toMap重构 key为cookie的名字 value为cookie
     *
     * @param request HttpServletRequest
     * @return cookieMap
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        return Arrays.stream(request.getCookies()).collect(toMap(Cookie::getName, Function.identity()));
    }

    /**
     * 删除cookie
     * 采用optional重构
     *
     * @param name 要删除的cookie名字
     */
    public static void delete(String name) {
        get(name).ifPresent(CookieUtil::expire);
    }

    /**
     * 让cookie失效
     *
     * @param cookie cookie
     */
    private static void expire(Cookie cookie) {
        cookie.setMaxAge(0);
        cookie.setValue(null);
        cookie.setPath("/");
        response().addCookie(cookie);
    }


    /**
     * 获得response
     *
     * @return response
     */
    private static HttpServletResponse response() {
        return servletRequestAttributes().getResponse();
    }

    /**
     * 获得request
     *
     * @return request
     */
    private static HttpServletRequest request() {
        return servletRequestAttributes().getRequest();
    }

    /**
     * 获得 servletRequestAttributes 对象
     *
     * @return servletRequestAttributes
     */
    private static ServletRequestAttributes servletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

}
