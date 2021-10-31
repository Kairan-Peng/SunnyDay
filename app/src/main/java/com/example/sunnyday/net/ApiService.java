package com.example.sunnyday.net;

import com.example.sunnyday.bean.ForecastWeatherResponse;
import com.example.sunnyday.bean.LifestyleResponse;
import com.example.sunnyday.bean.NowWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    //获取现在的天气状况
    @GET("/s6/weather/now?key=8e2f81a371ff4dafb699819c40d98523")
    Call<NowWeatherResponse> getTodayWeather(@Query("location") String location);

    //获取未来3-7天的天气状况预告
    @GET("/s6/weather/forecast?key=8e2f81a371ff4dafb699819c40d98523")
    Call<ForecastWeatherResponse> getForecastWeather(@Query("location") String location);

    //获取生活指数
    @GET("/s6/weather/lifestyle?key=8e2f81a371ff4dafb699819c40d98523")
    Call<LifestyleResponse> getLifestyle(@Query("location") String location);
}
