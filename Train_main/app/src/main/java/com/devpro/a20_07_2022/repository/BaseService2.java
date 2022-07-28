//package com.devpro.a20_07_2022.repository;
//
//import com.devpro.a20_07_2022.BuildConfig;
//import com.devpro.a20_07_2022.utils.Constants;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class BaseService {
//    private static BaseService instance = null;
//    private Retrofit retrofit;
//    private OkHttpClient client;
//    private IService iService;
//
//    public BaseService() {
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
////        okHttpBuilder.addInterceptor(new TokenInterceptor());
//
//        if (BuildConfig.DEBUG) {
//            okHttpBuilder.addInterceptor(loggingInterceptor);
//        }
//
//        client = okHttpBuilder.build();
//
//        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//
//        iService = retrofit.create(IService.class);
//    }
//
//    public static BaseService getInstance() {
//        if (instance == null) {
//            instance = new BaseService();
//        }
//        return instance;
//    }
//
//    public IService getIService() {
//        return iService;
//    }
//
//}
