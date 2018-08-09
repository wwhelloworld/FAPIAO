package com.lcsd.fapiao.http;

import android.app.Application;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by wei on 18-4-2.
 */

public class MyApplication extends Application {
    public static MyApplication myApplication;
    private MyOkHttp myOkHttp;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        //设置开启cookie
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                 .connectTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(30, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.MINUTES)
                .sslSocketFactory(HttpUtils.createSSLSocketFactory())
                .cookieJar(cookieJar)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("http://www.baidu.com", 8080));
                okHttpClient.newBuilder().proxy(proxy);
            }
        }).start();
        myOkHttp = new MyOkHttp(okHttpClient);
    }

    public static MyApplication getInstance() {
        if (myApplication == null)
            return new MyApplication();
        return myApplication;
    }

    public MyOkHttp getMyOkHttp() {
        return myOkHttp;
    }
}
