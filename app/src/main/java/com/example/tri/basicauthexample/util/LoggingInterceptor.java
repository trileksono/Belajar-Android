package com.example.tri.basicauthexample.util;

import android.content.Context;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tri on 5/18/16.
 */
public class LoggingInterceptor implements Interceptor {

    private final static Logger logger = Logger.getLogger("LOGGER");
    private Context mContext;

    public LoggingInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        SecurePreferences securePreferences = new SecurePreferences(
                mContext,Constant.prefName,Constant.prefPass,true);

        Request request = chain.request();


        Request.Builder requestBuilder = request.newBuilder()
                .header("Authorization", securePreferences.getString("basic"))
                .header("Accept", "application/json")
                .method(request.method(), request.body());

        Request newRequest = requestBuilder.build();

        Response response = chain.proceed(newRequest);

        logger.info(String.format("Sending request %s on %s%n%s",
                newRequest.url(), chain.connection(), newRequest.headers()));
        long t1 = System.nanoTime();

        long t2 = System.nanoTime();
        logger.info(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }


}
