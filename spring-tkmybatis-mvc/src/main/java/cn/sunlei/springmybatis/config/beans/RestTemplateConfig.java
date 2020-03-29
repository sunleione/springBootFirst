package cn.sunlei.springmybatis.config.beans;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by sun.lei on 11/21/2019.
 */
@Configuration
public class RestTemplateConfig {

    @ConditionalOnMissingBean(value = RestTemplate.class)
    @Bean
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        //此处绕过SSL证书
        SimpleClientHttpRequestFactory requestFactory =new SSLSimpleClientHttpRequestFactory();
        //SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 不绕过证书时使用 SimpleClientHttpRequestFactory requestFactory =new SimpleClientHttpRequestFactory()
        //可配置 Proxy 代理
        //requestFactory.setProxy(proxy);
        // timeout
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }


    private static SSLContext getSslContext() {
        final SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new X509TrustManagerImpl()}, new SecureRandom());
            return sslContext;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }

    }


    private static SimpleClientHttpRequestFactory getUnsafeClientHttpRequestFactory() {

        return new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection,
                                             @NotNull String httpMethod) throws IOException {
                super.prepareConnection(connection, httpMethod);
                if (connection instanceof HttpsURLConnection) {
                    ((HttpsURLConnection) connection).setSSLSocketFactory(
                            getSslContext().getSocketFactory());
                }
            }
        };
    }

    /**
     * 跳过证书验证封装
     */
    public class SSLSimpleClientHttpRequestFactory extends SimpleClientHttpRequestFactory {

        @Override
        protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
            super.prepareConnection(connection, httpMethod);
            if (connection instanceof HttpsURLConnection) {
                    HttpsURLConnection connection1 = (HttpsURLConnection) connection;
                    //connection.setHostnameVerifier(new SkipHostnameVerifier());
                    //通过SSL
                    connection1.setSSLSocketFactory(getSslContext().getSocketFactory());
                    //不校验域名
                    connection1.setHostnameVerifier(new HostnameVerifierImpl());
            }
        }
    }

    private static class HostnameVerifierImpl implements HostnameVerifier {
        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }
    }


    private static class X509TrustManagerImpl implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
