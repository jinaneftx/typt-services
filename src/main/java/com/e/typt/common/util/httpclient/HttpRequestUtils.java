package com.e.typt.common.util.httpclient;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * http请求工具类
 */
public class HttpRequestUtils {
    //定义连接对象
    public static CloseableHttpClient httpClient = null;
    public static PoolingHttpClientConnectionManager connectionManager = null;
    public static ConnectionKeepAliveStrategy myStrategy = null;


    /**
     * 发送http get请求
     *
     * @param url      服务器地址(Keys.java)
     * @param paramMap 需要向服务器传递的参数（必须包含Lk=XX，生成的LK ）
     * @return
     * @throws IOException
     */
    public static String sendHttpGet(String url) throws IOException {
        return HttpClientUtil.get(url);
    }
    
    /**
     * 发送http post请求
     *
     * @param url      服务器地址(Keys.java)
     * @param paramMap 需要向服务器传递的参数（必须包含Lk=XX，生成的LK ）
     * @return
     * @throws IOException
     */
    public static String sendHttpPost(String url, List<Map<String, String>> paramList) throws IOException {
        return HttpClientUtil.post(url, paramList);
    }


    /**
     * 构建唯一会话Id
     *
     * @return
     */
    public static String getSessionId() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }


    /**
     * 将map中的数据格式化成服务端所需的表单String(www.baidu.com/login?userName=lambdroid&password=123456的“？”以及之后的数据)
     *
     * @param head url头部字串，一般为“？”，在表单方式中分隔URL和请求参数map
     * @param map  请求参数map
     * @return 格式化完成后的表单数据
     */
    public static <K, V> String urlParamterStringer(String head, Map<K, V> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        //设置表单长度30字节*N个请求参数
        int capacity = map.size() * 30;
        //参数不为空，在URL后面添加head（“？”）
        StringBuilder buffer = new StringBuilder(capacity);
        if (!map.isEmpty()) {
            buffer.append(head);
        }
        //取出Map里面的请求参数，添加到表单String中。每个参数之间键值对之间用“=”连接，参数与参数之间用“&”连接
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> entry = it.next();
            Object key = entry.getKey();
            buffer.append(key);
            buffer.append('=');
            Object value = entry.getValue();
            buffer.append(value);
            if (it.hasNext()) {
                buffer.append("&");
            }
        }
        return buffer.toString();
    }

    /**
     * 创建httpClient 的单例对象
     *
     * @return
     */
    public static CloseableHttpClient createHttpClient() {
        if (null == httpClient) {
            httpClient = HttpClients.custom()
                    .setConnectionManager(getConnectionManager())
                    .setKeepAliveStrategy(getKeepAliveStrategy())
                    .setDefaultRequestConfig(RequestConfig.custom().setStaleConnectionCheckEnabled(true).build())
                    .build();
        }
        return httpClient;
    }

    public static HttpClientConnectionManager getConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        //例如默认每路由最高50并发，具体依据业务来定
        connectionManager.setDefaultMaxPerRoute(10);
        return connectionManager;
    }

    /**
     * 设置请求保活连接
     *
     * @return
     */
    public static ConnectionKeepAliveStrategy getKeepAliveStrategy() {
        if (null == myStrategy) {
            myStrategy = new ConnectionKeepAliveStrategy() {
                @Override
                public long getKeepAliveDuration(HttpResponse response,
                                                 HttpContext context) {
                    HeaderElementIterator it = new BasicHeaderElementIterator(
                            response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                    while (it.hasNext()) {
                        HeaderElement he = it.nextElement();
                        String param = he.getName();
                        String value = he.getValue();
                        if (value != null && param.equalsIgnoreCase("timeout")) {
                            return Long.parseLong(value) * 1000;
                        }
                    }
                    // 如果没有约定，则默认定义时长为60s
                    return 60L * 1000L;
                }
            };
        }
        return myStrategy;
    }
}

