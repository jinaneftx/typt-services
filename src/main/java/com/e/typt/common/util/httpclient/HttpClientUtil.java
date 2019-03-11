package com.e.typt.common.util.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * HttpClient工具类
 * 
 * @return
 */
public class HttpClientUtil {
	 private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
	// 设置连接超时时间，单位毫秒。
	private static int timeOut=Integer.parseInt( PropertyUtil.getPropertiesVal("CONNECTION_TIMEOUT"))*1000;
	private static int connectionRequestTimeOut=Integer.parseInt( PropertyUtil.getPropertiesVal("CONNECTION_REQUEST_TIMEOUT"))*1000;
	private static int socketTimeOut=Integer.parseInt( PropertyUtil.getPropertiesVal("SOCKET_TIMEOUT"))*1000;
	// 最大链接数
	private static int maxTotal=Integer.parseInt( PropertyUtil.getPropertiesVal("MAX_TOTAL"))*1000;
	// 路由并发数
	private static int maxPreRoute=Integer.parseInt( PropertyUtil.getPropertiesVal("MAX_PRE_ROUTE"))*1000;
	// 目标主机的最大连接数增加
	private static int hostMaxRoute=Integer.parseInt( PropertyUtil.getPropertiesVal("HOST_MAX_ROUTE"))*1000;
	
    private static CloseableHttpClient httpClient = null;

    private final static Object syncLock = new Object();

    private static void config(HttpRequestBase httpRequestBase) {
        // 设置Header等
//         httpRequestBase.setHeader("User-Agent", "Mozilla/5.0");
//         httpRequestBase
//         .setHeader("Accept",
//         "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//         httpRequestBase.setHeader("Accept-Language",
//         "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");// "en-US,en;q=0.5");
//         httpRequestBase.setHeader("Accept-Charset",
//         "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");
    	
    	//setConnectTimeout：设置连接超时时间，单位毫秒。
    	//setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
    	//setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connectionRequestTimeOut)
                .setConnectTimeout(timeOut)
                .setSocketTimeout(socketTimeOut).build();
        httpRequestBase.setConfig(requestConfig);
    }

    /**
     * 获取HttpClient对象
     * 
     * @return
     */
    public static CloseableHttpClient getHttpClient(String url) {
    	
        String hostname = url.split("/")[2];
        int port = 80;
        if (hostname.contains(":")) {
            String[] arr = hostname.split(":");
            hostname = arr[0];
            port = Integer.parseInt(arr[1]);
        }
        if (httpClient == null) {
            synchronized (syncLock) {
                	
                	 
                	/*//最大链接数
                	int maxTotal = Integer.parseInt(maxTotal);
                	//路由并发数
                	int maxPreRoute = Integer.parseInt(maxPreRoute);
                	//目标主机的最大连接数增加
                	int hostMaxRoute = Integer.parseInt(hostMaxRoute);*/
                	
                    httpClient = createHttpClient(maxTotal, maxPreRoute, hostMaxRoute,hostname ,port);
            }
        }
        return httpClient;
    }

    /**
     * 创建HttpClient对象
     * 
     * @return
     */
    public static CloseableHttpClient createHttpClient(int maxTotal,
            int maxPerRoute, int maxRoute, String hostname, int port) {
//    	https://blog.csdn.net/u011191463/article/details/78664896
    	
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory
                .getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory
                .getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory> create().register("http", plainsf)
                .register("https", sslsf).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(
                registry);
        // 设置整个连接池最大连接数 
        cm.setMaxTotal(maxTotal);
        // 是路由的默认最大连接（该值默认为2），限制数量实际使用DefaultMaxPerRoute并非MaxTotal。
        //设置过小无法支持大并发(ConnectionPoolTimeoutException: Timeout waiting for connection from pool)，路由是对maxTotal的细分。
        cm.setDefaultMaxPerRoute(maxPerRoute);
        HttpHost httpHost = new HttpHost(hostname, port);
        // 目标主机的最大连接数
        cm.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);
        
        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
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
				return 3600000;//60*60 * 1000;// 如果没有约定，则默认定义时长为60s
			}
		};
        
        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception,
                    int executionCount, HttpContext context) {
                if (executionCount >= 3) {// 如果已经重试了5次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// SSL握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext
                        .adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        return HttpClients.custom()
                .setConnectionManager(cm)
                .setKeepAliveStrategy(myStrategy)
                .setRetryHandler(httpRequestRetryHandler).build();

    }

    private static void setPostParams(HttpPost httpost,
    		List<Map<String,String>> paramList) throws UnsupportedEncodingException {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for(Map paramMap : paramList){
            Iterator<?> iterator = paramMap.entrySet().iterator();  
            while(iterator.hasNext()){  
                @SuppressWarnings("unchecked")
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }  
        }
        if(list != null && !list.isEmpty() && list.size() > 0){  
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"utf-8");
            httpost.setEntity(entity);  
        }
    }

    /**
     * GET请求URL获取内容
     * 
     * @param url
     * @return
     */
    public static String post(String url,List<Map<String,String>> paramList) throws IOException {
    	
    	boolean flag = true;
    	String resultMsg = "";
    	
    	//初始化post请求		
    	HttpPost httppost = new HttpPost(url);
        //配置post请求的连接时长
    	config(httppost);
    	//配置post的参数
        setPostParams(httppost, paramList);
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient(url).execute(httppost,HttpClientContext.create());
            
            if (null == response) {
                resultMsg="请求主应用返回的CloseableHttpResponse对象为空";
                flag=false;
            } else {
                //请求不成功
                if (200 != response.getStatusLine().getStatusCode()) {
                    resultMsg="请求失败";
                    flag=false;
                } else {
                	 HttpEntity resEntity = response.getEntity();
                    
                	 if (resEntity != null) {
                		 InputStream content = resEntity.getContent();
                		 String stringEntity = EntityUtils.toString(resEntity, "utf-8");
                         EntityUtils.consume(resEntity);
                        if(null == stringEntity || "".equals(stringEntity)){
                            resultMsg="应用的请求池已经达到最大链接，请稍后重新调用";
                            flag=false;
                        }else{
                        	resultMsg=stringEntity;
                        	flag=true;		
                        }
                    } else {
                    	resultMsg="请求主应用返回的HttpEntity对象为空";
                    	flag=false;
                        
                    }
                }
            }

        } catch (Exception e) {
          log.error(e+"");
          flag=false;
          resultMsg=e.getMessage();
        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                log.error(e+"");
            }
        }
        if(flag){
        	return resultMsg;
        }else{
        	return resultMsg;
        }
    }

    /**
     * GET请求URL获取内容
     * 
     * @param url
     * @return
     */
    public static String get(String url) {
        HttpGet httpget = new HttpGet(url);
        config(httpget);
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient(url).execute(httpget,
                    HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            return result;
        } catch (IOException e) {
            log.error(e+"");
        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                log.error(e+"");
            }
        }
        return null;
    }

    

    static class GetRunnable implements Runnable {
        private CountDownLatch countDownLatch;
        private String url;

        public GetRunnable(String url, CountDownLatch countDownLatch) {
            this.url = url;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                System.out.println(HttpClientUtil.get(url));
            } finally {
                countDownLatch.countDown();
            }
        }
    }
    static class PropertyUtil{
    	public static String getPropertiesVal(String key){
        	java.util.Properties props = new java.util.Properties();
        	try {
    			props.load(PropertyUtil.class.getClassLoader().getResourceAsStream("url.properties"));
    			String value = props.get(key)+"";
    			return value;
    		} catch (IOException e) {
    			log.error(e+"");
    		}
    		return "";
        }
    }
}