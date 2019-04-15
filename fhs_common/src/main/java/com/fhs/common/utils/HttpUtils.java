package com.fhs.common.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * HTTP 请求工具类
 *
 * @author : liii
 * @version : 1.0.0
 * @date : 2015/7/21
 * @see : TODO
 */
@SuppressWarnings("deprecation")
public class HttpUtils {

    //日志输出
    private static final Logger LOG = Logger.getLogger(HttpUtils.class);

    private static PoolingHttpClientConnectionManager connMgr;

    private static RequestConfig requestConfig;

    private static final int MAX_TIMEOUT = 7000;

    private static CloseableHttpClient httpClient;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        requestConfig = configBuilder.build();
        httpClient = HttpClients.custom()
                .setSSLSocketFactory(createSSLConnSocketFactory())
                .setConnectionManager(connMgr)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, new HashMap<String, Object>());
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url, Map<String, Object> params) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        try {
            HttpGet httpPost = new HttpGet(apiUrl);
            setThreadKey(apiUrl, params);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, "UTF-8");
            }
            LOG.infoMsg("请求结果 : {}", result);
        } catch (IOException e) {
            LOG.error("请求失败，IOException ： " + e.getMessage());
        }
        return result;
    }

    /**
     * 发送 POST 请求（HTTP），不带输入数据
     *
     * @param apiUrl
     * @return
     */
    public static String doPost(String apiUrl) {
        return doPost(apiUrl, new HashMap<String, Object>());
    }

    /**
     * 发送 POST 请求（HTTP），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPost(String apiUrl, Map<String, ?> params) {

        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            setThreadKey(apiUrl, params);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            LOG.infoMsg("请求成功,请求返回状态码:{}", statusCode);
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                httpStr = EntityUtils.toString(entity, "UTF-8");
            }
            LOG.infoMsg("请求结果:{}", httpStr);
        } catch (Exception e) {
            LOG.error("请求失败, Exception : " + e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    LOG.error("请求失败, IOException : " + e.getMessage());
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     *
     * @param apiUrl
     * @param json   json对象
     * @return
     */
    public static String doPost(String apiUrl, Object json) {
        if (apiUrl.startsWith("https")) {
            return doPostSSL(apiUrl, json);
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            setThreadKey(apiUrl, json);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            LOG.infoMsg("请求成功,请求返回状态码:{}", statusCode);
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                httpStr = EntityUtils.toString(entity, "UTF-8");
            }
            LOG.infoMsg("请求结果:{}", httpStr);
        } catch (IOException e) {
            LOG.infoMsg("请求失败，{}", e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    LOG.infoMsg("请求失败，{}", e.getMessage());
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPostSSL(String apiUrl, Map<String, Object> params) {
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
            setThreadKey(apiUrl, params);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            LOG.infoMsg("请求成功,请求返回状态码:{}", statusCode);
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
            LOG.infoMsg("请求结果:{}", httpStr);
        } catch (Exception e) {
            LOG.error("请求失败, Exception : " + e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    LOG.error("请求失败,IOException : " + e.getMessage());
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 SSL POST 请求（HTTPS），JSON形式
     *
     * @param apiUrl API接口URL
     * @param json   JSON对象
     * @return
     */
    public static String doPostSSL(String apiUrl, Object json) {

        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            setThreadKey(apiUrl, json);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            LOG.infoMsg("请求成功,请求返回状态码:{}", statusCode);
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
            LOG.infoMsg("请求结果:{}", httpStr);
        } catch (Exception e) {
            LOG.error("请求失败,Exception : " + e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    LOG.error("请求失败,IOException : " + e.getMessage());
                }
            }
        }
        return httpStr;
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {

            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl)
                        throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert)
                        throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts)
                        throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }

    /**
     * 设定Logger
     */
    private static void setThreadKey(String apiUrl, Object param) {
        //拼接日志参数 begin
        if (ThreadKey.BUS_KEY.get() == null) {
            ThreadKey.BUS_KEY.set(StringUtil.getUUID());
        }
        LOG.infoMsg("开始请求:{},请求参数:{}", apiUrl, JsonUtils.object2json(param));
    }

    /**
     * 上传文件
     * @param apiUrl url
     * @param fileMap 文件map
     * @return 服务器返回结果
     */
    public static String uploadFile(String apiUrl, Map<String, File> fileMap) {
        HttpPost httpPost = new HttpPost(apiUrl);
        //Step1：创建MultipartEntityBuilder实例
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        //Step2：初始化，设置各种属性
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        if (fileMap != null && fileMap.size() > 0) {
            Set<Map.Entry<String, File>> entries = fileMap.entrySet();
            for (Map.Entry<String, File> entry : entries) {
                builder.addPart(entry.getKey(), new FileBody(entry.getValue()));
            }
        }
        //Step4：转化为消息体
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        String httpStr = null;
        CloseableHttpResponse response = null;
        try {
            setThreadKey(apiUrl, fileMap);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            LOG.infoMsg("请求成功,请求返回状态码:{}", statusCode);
            if (statusCode == 200) {
                entity = response.getEntity();
                httpStr = EntityUtils.toString(entity, "UTF-8");
            }
            LOG.infoMsg("请求结果:{}", httpStr);
        } catch (Exception e) {
            LOG.error("请求失败, Exception : " + e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    LOG.error("请求失败, IOException : " + e.getMessage());
                }
            }
        }
        return httpStr;
    }


    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args)
            throws Exception {

        Map<String,File> fileMap = new HashMap<>();
        fileMap.put("Filedata",new File("G:\\document\\停车项目\\图片\\logo.jpg"));
        String doPostSSL = uploadFile("http://file.fhs.xhb.com//upload/file", fileMap);
        System.out.println(doPostSSL);
    }
}