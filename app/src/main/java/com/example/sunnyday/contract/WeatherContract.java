package com.example.sunnyday.contract;

import android.content.Context;

import com.example.sunnyday.base.BasePresenter;
import com.example.sunnyday.base.BaseView;
import com.example.sunnyday.net.NetCallBack;
import com.example.sunnyday.net.ServiceGenerator;
import com.example.sunnyday.net.ApiService;
import com.example.sunnyday.bean.ForecastWeatherResponse;
import com.example.sunnyday.bean.LifestyleResponse;
import com.example.sunnyday.bean.NowWeatherResponse;

import retrofit2.Call;
import retrofit2.Response;


/*
 * 天气订阅器
 * 订阅接口服务，处理API请求返回数据
 * */
public class WeatherContract {

    public static class WeatherPresenter {
        private IWeatherView iWeatherView;

        /*
         * 当日天气
         * @param context
         * @param location 区/县
         * */

        public void setIWeatherView(IWeatherView iWeatherView) {
            this.iWeatherView = iWeatherView;
        }

        public void todayWeather(final Context context, String location) {
            //得到构建之后的网络请求服务，这里的地址已经拼接完成，只差location
            ApiService service = ServiceGenerator.createService(ApiService.class);
            //设置请求回调，NetCallBack实现了CallBack，接下来实现NetCallBack里的onSucceed和onFailed方法
            service.getTodayWeather(location).enqueue(new NetCallBack<NowWeatherResponse>() {
                @Override
                public void onSucceed(Call<NowWeatherResponse> call, Response<NowWeatherResponse> response) {
                    if (iWeatherView != null) {
                        //当视图不为空时，返回请求数据，待调用者实现
                        iWeatherView.getTodayWeatherResult(response);
                    }
                }

                @Override
                public void onFailed() {
                    if (iWeatherView != null) {
                        //当视图不为空时，返回错误信息，待调用者实现
                        iWeatherView.getDataFailed();
                    }
                }
            });
        }

        /*
         * 未来3-7天的天气
         * @param context
         * @param location 区/县
         * */
        public void forecastWeather(final Context context, String location) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            service.getForecastWeather(location).enqueue(new NetCallBack<ForecastWeatherResponse>() {
                @Override
                public void onSucceed(Call<ForecastWeatherResponse> call, Response<ForecastWeatherResponse> response) {
                    if (iWeatherView != null) {
                        iWeatherView.getForecastWeatherResult(response);
                    }
                }

                @Override
                public void onFailed() {
                    if (iWeatherView != null) {
                        iWeatherView.getDataFailed();
                    }
                }
            });
        }

        /*
         * 生活指数
         * @param context
         * @param location 区/县
         * */
        public void lifestyle(final Context context, String location) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            service.getLifestyle(location).enqueue(new NetCallBack<LifestyleResponse>() {
                @Override
                public void onSucceed(Call<LifestyleResponse> call, Response<LifestyleResponse> response) {
                    if (iWeatherView != null) {
                        iWeatherView.getLifestyleResult(response);
                    }
                }

                @Override
                public void onFailed() {
                    if (iWeatherView != null) {
                        iWeatherView.getDataFailed();
                    }
                }
            });
        }

    }


    public interface IWeatherView extends BaseView {
        //查询当日天气的数据返回
        void getTodayWeatherResult(Response<NowWeatherResponse> response);

        //查询天气预报的数据返回
        void getForecastWeatherResult(Response<ForecastWeatherResponse> response);

        //查询生活指数的数据返回
        void getLifestyleResult(Response<LifestyleResponse> response);

        //失败的返回
        void getDataFailed();
    }
}
