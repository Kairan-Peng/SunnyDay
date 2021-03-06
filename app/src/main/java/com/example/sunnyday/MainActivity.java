package com.example.sunnyday;

import static com.example.sunnyday.utils.RecyclerViewAnimation.runLayoutAnimationRight;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.sunnyday.adapter.AreaAdapter;
import com.example.sunnyday.adapter.CityAdapter;
import com.example.sunnyday.adapter.ForecastWeatherAdapter;
import com.example.sunnyday.adapter.ProvinceAdapter;
import com.example.sunnyday.bean.CityResponse;
import com.example.sunnyday.bean.ForecastWeatherResponse;
import com.example.sunnyday.bean.LifestyleResponse;
import com.example.sunnyday.bean.NowWeatherResponse;
import com.example.sunnyday.contract.WeatherActivity;
import com.example.sunnyday.contract.WeatherContract;
import com.example.sunnyday.utils.BlurUtil;
import com.example.sunnyday.utils.MyWindows;
import com.example.sunnyday.utils.StatusBarUtils;
import com.example.sunnyday.utils.ToastUtils;
import com.example.sunnyday.view.WhiteWindmills;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MainActivity extends WeatherActivity implements WeatherContract.IWeatherView {

    private static final String TAG = "pkr";
    private String district;
    private RxPermissions rxPermissions = null;
    public LocationClient mLocationClient = null;
    // today:???????????????????????????????????????????????????????????????
    private TextView tvInfo, tvTemperature, tvLowHeight, tvCity, tvOldTime;
    // today: ????????????????????????????????????????????????
    private TextView tvWindDirection, tvWindPower, tvWindSpeed;
    private WhiteWindmills wwBig, wwSmall;

    private RecyclerView rvForecast;
    List<ForecastWeatherResponse.HeWeather6Bean.DailyForecastBean> forecastList;
    ForecastWeatherAdapter forecastWeatherAdapter;
    // forecast:????????????, ??????, ???????????????
//    private TextView tvForecastInfo, tvForecastDate, tvForecastLowHeight;
    // lifestyle:???????????????????????????????????????????????????????????????????????????
    private TextView tvComfBrf, tvComfTxt, tvDrsgBrf, tvDrsgTxt, tvFluBrf, tvFluTxt, tvSportBrf, tvSportTxt, tvTravBrf, tvTravTxt, tvUvBrf, tvUvTxt, tvCwBrf, tvCwTxt, tvAirBrf, tvAirTxt;
    // ????????????
    private RecyclerView rvCity;
    private ImageView ivCitySelect, ivCityBack, ivAreaBack;
    private TextView tvCitySelectTitle;
    private List<String> list;//???????????????
    private List<CityResponse> provinceList;//???????????????
    private List<CityResponse.CityBean> cityList;//???????????????
    private List<CityResponse.CityBean.AreaBean> areaList;//???/???????????????
    ProvinceAdapter provinceAdapter;//??????????????????
    CityAdapter cityAdapter;//??????????????????
    AreaAdapter areaAdapter;//???/??????????????????
    String provinceTitle;//??????
    MyWindows myWindows;//???????????????
    JSONArray Data;
    private Dialog mDialog;//????????????
    private SmartRefreshLayout refresh;//??????????????????
    //?????????????????????
    private ImageView ivLocation;
    private LinearLayout llMain;


    private void activityBindViews() {
        // ???????????????
        llMain = findViewById(R.id.ll_main);
        // ????????????
        rvCity = findViewById(R.id.rv_city);
        ivCitySelect = findViewById(R.id.iv_city_select);
        ivCityBack = findViewById(R.id.iv_back_city);
        ivAreaBack = findViewById(R.id.iv_back_area);
        tvCitySelectTitle = findViewById(R.id.tv_city_select_title);
        ivCitySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCityWindow();
            }
        });

        // ????????????
        ivLocation = findViewById(R.id.iv_location);
        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtils.showShortToast(MainActivity.this, "Locating...");
                mLocationClient.start();
                dismissLoadingDialog();
            }
        });

        // ????????????
        tvInfo = findViewById(R.id.tv_info);
        tvTemperature = findViewById(R.id.tv_temperature);
        tvLowHeight = findViewById(R.id.tv_low_height);
        tvCity = findViewById(R.id.tv_city);
        tvOldTime = findViewById(R.id.tv_old_time);
        tvWindDirection = findViewById(R.id.tv_wind_direction);
        tvWindPower = findViewById(R.id.tv_wind_power);
        tvWindSpeed = findViewById(R.id.tv_wind_speed);
        wwBig = findViewById(R.id.ww_big);
        wwSmall = findViewById(R.id.ww_small);

        // ????????????
        rvForecast = findViewById(R.id.rv_forecast);
//        tvForecastInfo = findViewById(R.id.tv_info_rv);
//        tvForecastDate = findViewById(R.id.tv_date_rv);
//        tvForecastLowHeight = findViewById(R.id.tv_low_and_height_rv);

        // ????????????
        tvComfBrf = findViewById(R.id.tv_comf_brf);
        tvComfTxt = findViewById(R.id.tv_comf_txt);
        tvDrsgBrf = findViewById(R.id.tv_drsg_brf);
        tvDrsgTxt = findViewById(R.id.tv_drsg_txt);
        tvFluBrf = findViewById(R.id.tv_flu_brf);
        tvFluTxt = findViewById(R.id.tv_flu_txt);
        tvSportBrf = findViewById(R.id.tv_sport_brf);
        tvSportTxt = findViewById(R.id.tv_sport_txt);
        tvTravBrf = findViewById(R.id.tv_trav_brf);
        tvTravTxt = findViewById(R.id.tv_trav_txt);
        tvUvBrf = findViewById(R.id.tv_uv_brf);
        tvUvTxt = findViewById(R.id.tv_uv_txt);
        tvCwBrf = findViewById(R.id.tv_cw_brf);
        tvCwTxt = findViewById(R.id.tv_cw_txt);
        tvAirBrf = findViewById(R.id.tv_air_brf);
        tvAirTxt = findViewById(R.id.tv_air_txt);

        //??????????????????
        refresh = findViewById(R.id.refresh);
        refresh.setOnRefreshListener(refreshlayout -> {
//            ToastUtils.showShortToast(this, "Refresh");
            setBackgroundPic();
            mPresenter.todayWeather(this, district);
            mPresenter.forecastWeather(this, district);
            mPresenter.lifestyle(this, district);
            refresh.finishRefresh();
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        // bind views
        activityBindViews();
        // ?????????????????????recyclelist
        initForecastList();
        //???????????????
        StatusBarUtils.transparencyBar(this);
        //????????????
        rxPermissions = new RxPermissions(this);
        permissionVersion();
        // ????????????
        mPresenter.setIWeatherView(this);
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void setBackgroundPic() {
        long totalMilliSeconds = System.currentTimeMillis();
        DateFormat dateFormatterChina = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);//???????????????
        long totalSeconds = totalMilliSeconds / 1000;
        long totalMinutes = totalSeconds / 60;
        long totalHour = totalMinutes / 60;
        int currentHour = (int) totalHour % 24 + 8;
        if (currentHour >= 3 && currentHour < 6) {
            llMain.setBackground(getDrawable(R.drawable.bg_3_6));
        } else if (currentHour >= 6 && currentHour < 8) {
            llMain.setBackground(getDrawable(R.drawable.bg_6_8));
        } else if (currentHour >= 8 && currentHour < 10) {
            llMain.setBackground(getDrawable(R.drawable.bg_8_10));
        } else if (currentHour >= 10 && currentHour < 12) {
            llMain.setBackground(getDrawable(R.drawable.bg_10_12));
        } else if (currentHour >= 12 && currentHour < 14) {
            llMain.setBackground(getDrawable(R.drawable.bg_12_14));
        } else if (currentHour >= 14 && currentHour < 16) {
            llMain.setBackground(getDrawable(R.drawable.bg_14_16));
        } else if (currentHour >= 16 && currentHour < 18) {
            llMain.setBackground(getDrawable(R.drawable.bg_16_18));
        } else if (currentHour >= 18 && currentHour < 20) {
            llMain.setBackground(getDrawable(R.drawable.bg_18_20));
        } else if (currentHour >= 20 && currentHour < 22) {
            llMain.setBackground(getDrawable(R.drawable.bg_20_22));
        } else {
            llMain.setBackground(getDrawable(R.drawable.pic_bg));
        }
        Bitmap bitmap = BlurUtil.getBlurBackground(MainActivity.this);
    }

    @Override
    protected WeatherContract.WeatherPresenter createPresenter() {
        return new WeatherContract.WeatherPresenter();
    }

    @Override
    protected void onDestroy() {
        wwBig.stop();
        wwSmall.stop();
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityBindViews();

        rxPermissions = new RxPermissions(this);
        permissionVersion();


    }*/


    private void permissionVersion() {
        if (Build.VERSION.SDK_INT >= 23) {
            //??????????????????
            permissionRequest();
        } else {
            ToastUtils.showShortToast(this, "Android?????????6.0????????????????????????????????????");
        }
    }

    @SuppressLint("CheckResult")
    private void permissionRequest() {
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION).subscribe(granted -> {
            if (granted) {
                //???????????????????????????????????????
                startLocation();
            } else {
                ToastUtils.showShortToast(this, "??????????????????");
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getTodayWeatherResult(Response<NowWeatherResponse> response) {
        //???????????????????????????????????????
        mLocationClient.stop();

        assert response.body() != null;
        if (response.body().getHeWeather6().get(0).getBasic() != null) {
            tvTemperature.setText(response.body().getHeWeather6().get(0).getNow().getTmp());
            tvCity.setText(response.body().getHeWeather6().get(0).getBasic().getLocation());
            tvInfo.setText(response.body().getHeWeather6().get(0).getNow().getCondTxt());
            tvOldTime.setText("?????????" + response.body().getHeWeather6().get(0).getUpdate().getLoc());
            tvWindDirection.setText("??????    " + response.body().getHeWeather6().get(0).getNow().getWindDir() + " ( " + response.body().getHeWeather6().get(0).getNow().getWindDeg() + "?? ) ");
            tvWindPower.setText("??????    " + response.body().getHeWeather6().get(0).getNow().getWindSc() + "???");
            tvWindSpeed.setText("??????    " + response.body().getHeWeather6().get(0).getNow().getWindSpd() + "??????/??????");
            wwBig.startRotate();
            wwSmall.startRotate();
            dismissLoadingDialog();

        } else {
            ToastUtils.showShortToast(this, response.body().getHeWeather6().get(0).getStatus());
            dismissLoadingDialog();

        }
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void getForecastWeatherResult(Response<ForecastWeatherResponse> response) {
        assert response.body() != null;
        if (response.body().getHeWeather6().get(0).getStatus().equals("ok")) {
            tvLowHeight.setText("?????? " + response.body().getHeWeather6().get(0).getDailyForecast().get(0).getTmpMin()
                    + "???  ,  ?????? " + response.body().getHeWeather6().get(0).getDailyForecast().get(0).getTmpMax() + "???");
            if (response.body().getHeWeather6().get(0).getDailyForecast() != null) {
                List<ForecastWeatherResponse.HeWeather6Bean.DailyForecastBean> data = response.body().getHeWeather6().get(0).getDailyForecast();
                forecastList.clear();
                forecastList.addAll(data);
                forecastWeatherAdapter.notifyDataSetChanged();
            } else {
                ToastUtils.showShortToast(this, "????????????????????????");
            }
        } else {
            ToastUtils.showShortToast(this, response.body().getHeWeather6().get(0).getStatus());
        }
    }

    @Override
    public void getLifestyleResult(Response<LifestyleResponse> response) {
        assert response.body() != null;
        if (response.body().getHeWeather6().get(0).getStatus().equals("ok")) {
            if (response.body().getHeWeather6().get(0).getLifestyle() != null) {
                for (LifestyleResponse.HeWeather6Bean.LifestyleBean lifestyleBean : response.body().getHeWeather6().get(0).getLifestyle()) {
                    if (lifestyleBean.getType().equals("comf")) {
                        tvComfBrf.setText(lifestyleBean.getBrf());
                        tvComfTxt.setText(lifestyleBean.getTxt());
//                        findViewById(R.id.ll_comf).setVisibility(View.VISIBLE);
                    } else if (lifestyleBean.getType().equals("drsg")) {
                        tvDrsgBrf.setText(lifestyleBean.getBrf());
                        tvDrsgTxt.setText(lifestyleBean.getTxt());
                    } else if (lifestyleBean.getType().equals("flu")) {
                        tvFluBrf.setText(lifestyleBean.getBrf());
                        tvFluTxt.setText(lifestyleBean.getTxt());
                    } else if (lifestyleBean.getType().equals("sport")) {
                        tvSportBrf.setText(lifestyleBean.getBrf());
                        tvSportTxt.setText(lifestyleBean.getTxt());
                    } else if (lifestyleBean.getType().equals("trav")) {
                        tvTravBrf.setText(lifestyleBean.getBrf());
                        tvTravTxt.setText(lifestyleBean.getTxt());
                    } else if (lifestyleBean.getType().equals("uv")) {
                        tvUvBrf.setText(lifestyleBean.getBrf());
                        tvUvTxt.setText(lifestyleBean.getTxt());
                    } else if (lifestyleBean.getType().equals("cw")) {
                        tvCwBrf.setText(lifestyleBean.getBrf());
                        tvCwTxt.setText(lifestyleBean.getTxt());
                    } else if (lifestyleBean.getType().equals("air")) {
                        tvAirBrf.setText(lifestyleBean.getBrf());
                        tvAirTxt.setText(lifestyleBean.getTxt());
                    }
                }
            } else {
                ToastUtils.showShortToast(this, "????????????????????????");
            }
        } else {
            ToastUtils.showShortToast(this, response.body().getHeWeather6().get(0).getStatus());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(this, "????????????");
        dismissLoadingDialog();
    }


    private class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            String locTypeDescription = bdLocation.getLocTypeDescription();
            Log.e(TAG, "locTypeDescription: " + locTypeDescription);
            district = bdLocation.getDistrict();    //????????????

            Log.e(TAG, "district: " + district);
            if (district == null) {
                district = "??????";
            }

            setBackgroundPic();

            showLoadingDialog();
            mPresenter.todayWeather(MainActivity.this, district);
            mPresenter.forecastWeather(MainActivity.this, district);
            mPresenter.lifestyle(MainActivity.this, district);
        }
    }

    private void startLocation() {
        mLocationClient = new LocationClient(this);
        mLocationClient.registerLocationListener(new MyLocationListener());

        LocationClientOption option = new LocationClientOption();
        //?????????????????????????????????????????????????????????????????????true
        option.setIsNeedAddress(true);
        //???????????????????????????????????????????????????????????????????????????????????????false
        option.setNeedNewVersionRgc(true);

        mLocationClient.setLocOption(option);

        mLocationClient.start();
    }

    //?????????????????????recyclelist
    private void initForecastList() {
        forecastList = new ArrayList<>();
        forecastWeatherAdapter = new ForecastWeatherAdapter(R.layout.item_forecast_weather_recyclerlist, forecastList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvForecast.setLayoutManager(manager);
        rvForecast.setAdapter(forecastWeatherAdapter);
//        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.rv_divider));
//        rv.addItemDecoration(divider);
    }

/*    private void getTodayWeather(String district){
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://free-api.heweather.net/s6/weather/now?key=8e2f81a371ff4dafb699819c40d98523&location=" + district)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: ????????????????????????");
                    Log.d(TAG, "response.code(): "+response.code());
                    Log.d(TAG, "response.body().String(): "+response.body().string());
                }
            }
        });
    }*/

    private void showCityWindow() {
        provinceList = new ArrayList<>();
        cityList = new ArrayList<>();
        areaList = new ArrayList<>();
        list = new ArrayList<>();
        myWindows = new MyWindows(this);
        final View view = LayoutInflater.from(this).inflate(R.layout.window_city_list, null);
        ImageView areaBack = view.findViewById(R.id.iv_back_area);
        ImageView cityBack = view.findViewById(R.id.iv_back_city);
        TextView windowTitle = view.findViewById(R.id.tv_city_select_title);
        RecyclerView recyclerView = view.findViewById(R.id.rv_city);
        myWindows.showRightPopupWindow(view);
        initCityData(recyclerView, areaBack, cityBack, windowTitle, this);
    }

    /**
     * ?????????????????????
     *
     * @param recyclerView ??????
     * @param areaBack     ????????????
     * @param cityBack     ?????????
     * @param windowTitle  ????????????
     * @param context      ?????????
     */
    @SuppressLint("NotifyDataSetChanged")
    private void initCityData(RecyclerView recyclerView, ImageView areaBack, ImageView cityBack, TextView windowTitle, Context context) {
        //?????????????????? ????????????????????????????????????
        try {
            InputStream inputStream = getResources().getAssets().open("City.txt");//????????????
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String lines = bufferedReader.readLine();
            while (lines != null) {
                stringBuffer.append(lines);
                lines = bufferedReader.readLine();
            }

            final JSONArray Data = new JSONArray(stringBuffer.toString());
            //??????????????????????????????????????????????????????????????????
            for (int i = 0; i < Data.length(); i++) {
                JSONObject provinceJsonObject = Data.getJSONObject(i);
                String provinceName = provinceJsonObject.getString("name");
                CityResponse response = new CityResponse();
                response.setName(provinceName);
                provinceList.add(response);
            }

            //???????????????????????????
            provinceAdapter = new ProvinceAdapter(R.layout.item_ciyt_list, provinceList);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(provinceAdapter);
            provinceAdapter.notifyDataSetChanged();
            runLayoutAnimationRight(recyclerView);//????????????

            provinceAdapter.setOnItemClickListener(new OnItemClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                    try {
                        //?????????????????????
                        cityBack.setVisibility(View.VISIBLE);
                        cityBack.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                recyclerView.setAdapter(provinceAdapter);
                                provinceAdapter.notifyDataSetChanged();
                                cityBack.setVisibility(View.GONE);
                                windowTitle.setText("??????");
                            }
                        });

                        //????????????????????????????????????????????????????????????????????????
                        JSONObject provinceObject = Data.getJSONObject(position);
                        windowTitle.setText(provinceList.get(position).getName());
                        provinceTitle = provinceList.get(position).getName();
                        final JSONArray cityArray = provinceObject.getJSONArray("city");

                        //??????????????????
                        if (cityList != null) {
                            cityList.clear();
                        }

                        for (int i = 0; i < cityArray.length(); i++) {
                            JSONObject cityObj = cityArray.getJSONObject(i);
                            String cityName = cityObj.getString("name");
                            CityResponse.CityBean response = new CityResponse.CityBean();
                            response.setName(cityName);
                            cityList.add(response);
                        }

                        cityAdapter = new CityAdapter(R.layout.item_ciyt_list, cityList);
                        LinearLayoutManager manager1 = new LinearLayoutManager(context);
                        recyclerView.setLayoutManager(manager1);
                        recyclerView.setAdapter(cityAdapter);
                        cityAdapter.notifyDataSetChanged();
                        runLayoutAnimationRight(recyclerView);

                        cityAdapter.setOnItemClickListener(new OnItemClickListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                                try {
                                    //?????????????????????
                                    areaBack.setVisibility(View.VISIBLE);
                                    areaBack.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            recyclerView.setAdapter(cityAdapter);
                                            cityAdapter.notifyDataSetChanged();
                                            areaBack.setVisibility(View.GONE);
                                            windowTitle.setText(provinceTitle);
                                            areaList.clear();
                                        }
                                    });
                                    //?????????????????????????????? ??????????????????
                                    windowTitle.setText(cityList.get(position).getName());
                                    JSONObject cityJsonObj = cityArray.getJSONObject(position);
                                    JSONArray areaJsonArray = cityJsonObj.getJSONArray("area");
                                    if (areaList != null) {
                                        areaList.clear();
                                    }
                                    if (list != null) {
                                        list.clear();
                                    }
                                    for (int i = 0; i < areaJsonArray.length(); i++) {
                                        list.add(areaJsonArray.getString(i));
                                    }
                                    Log.i("list", list.toString());
                                    for (int j = 0; j < list.size(); j++) {
                                        CityResponse.CityBean.AreaBean response = new CityResponse.CityBean.AreaBean();
                                        response.setName(list.get(j).toString());
                                        areaList.add(response);
                                    }
                                    areaAdapter = new AreaAdapter(R.layout.item_ciyt_list, areaList);
                                    LinearLayoutManager manager2 = new LinearLayoutManager(context);

                                    recyclerView.setLayoutManager(manager2);
                                    recyclerView.setAdapter(areaAdapter);
                                    areaAdapter.notifyDataSetChanged();
                                    runLayoutAnimationRight(recyclerView);

                                    areaAdapter.setOnItemClickListener(new OnItemClickListener() {
                                        @Override
                                        public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                                            district = areaList.get(position).getName();
                                            myWindows.closePopupWindow();
                                            setBackgroundPic();
                                            showLoadingDialog();
                                            mPresenter.todayWeather(MainActivity.this, district);//????????????
                                            mPresenter.forecastWeather(MainActivity.this, district);//????????????
                                            mPresenter.lifestyle(MainActivity.this, district);//????????????
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

    //????????????
    public void showLoadingDialog() {
        if (mDialog == null) {
            mDialog = new Dialog(this, R.style.loading_dialog);
        }
        mDialog.setContentView(R.layout.dialog_loading);
        mDialog.setCancelable(false);
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mDialog.show();
    }

    //????????????
    public void dismissLoadingDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        mDialog = null;
    }


}