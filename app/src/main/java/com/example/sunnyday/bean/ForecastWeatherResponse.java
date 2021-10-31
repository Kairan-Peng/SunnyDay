package com.example.sunnyday.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
{"HeWeather6": [{
        "basic": {
        "cid": "CN101210101",
        "location": "杭州",
        "parent_city": "杭州",
        "admin_area": "浙江省",
        "cnty": "中国",
        "lat": "30.287458",
        "lon": "120.153580",
        "tz": "+8.00"
        },
        "update": {
        "loc": "2021-10-10 18:35",
        "utc": "2021-10-10 10:35"
        },
        "status": "ok",
        "daily_forecast": [
        {
        "cond_code_d": "305",
        "cond_code_n": "305",
        "cond_txt_d": "小雨",
        "cond_txt_n": "小雨",
        "date": "2021-10-10",
        "hum": "94",
        "mr": "09:56",
        "ms": "20:28",
        "pcpn": "3.8",
        "pop": "72",
        "pres": "1014",
        "sr": "05:58",
        "ss": "17:35",
        "tmp_max": "31",
        "tmp_min": "21",
        "uv_index": "4",
        "vis": "18",
        "wind_deg": "0",
        "wind_dir": "北风",
        "wind_sc": "4-5",
        "wind_spd": "26"
        },
        {
        "cond_code_d": "305",
        "cond_code_n": "305",
        "cond_txt_d": "小雨",
        "cond_txt_n": "小雨",
        "date": "2021-10-11",
        "hum": "80",
        "mr": "11:06",
        "ms": "21:22",
        "pcpn": "3.3",
        "pop": "69",
        "pres": "1016",
        "sr": "05:58",
        "ss": "17:34",
        "tmp_max": "20",
        "tmp_min": "19",
        "uv_index": "1",
        "vis": "24",
        "wind_deg": "0",
        "wind_dir": "北风",
        "wind_sc": "4-5",
        "wind_spd": "34"
        },
        {
        "cond_code_d": "305",
        "cond_code_n": "305",
        "cond_txt_d": "小雨",
        "cond_txt_n": "小雨",
        "date": "2021-10-12",
        "hum": "91",
        "mr": "12:12",
        "ms": "22:22",
        "pcpn": "1.0",
        "pop": "55",
        "pres": "1014",
        "sr": "05:59",
        "ss": "17:33",
        "tmp_max": "22",
        "tmp_min": "19",
        "uv_index": "1",
        "vis": "24",
        "wind_deg": "0",
        "wind_dir": "北风",
        "wind_sc": "4-5",
        "wind_spd": "34"
        }
        ]
        }]}*/

public class ForecastWeatherResponse {

    @SerializedName("HeWeather6")
    private List<HeWeather6Bean> heWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return heWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> heWeather6) {
        this.heWeather6 = heWeather6;
    }

    public static class HeWeather6Bean {
        @SerializedName("basic")
        private BasicBean basic;
        @SerializedName("update")
        private UpdateBean update;
        @SerializedName("status")
        private String status;
        @SerializedName("daily_forecast")
        private List<DailyForecastBean> dailyForecast;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<DailyForecastBean> getDailyForecast() {
            return dailyForecast;
        }

        public void setDailyForecast(List<DailyForecastBean> dailyForecast) {
            this.dailyForecast = dailyForecast;
        }

        public static class BasicBean {
            @SerializedName("cid")
            private String cid;
            @SerializedName("location")
            private String location;
            @SerializedName("parent_city")
            private String parentCity;
            @SerializedName("admin_area")
            private String adminArea;
            @SerializedName("cnty")
            private String cnty;
            @SerializedName("lat")
            private String lat;
            @SerializedName("lon")
            private String lon;
            @SerializedName("tz")
            private String tz;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getParentCity() {
                return parentCity;
            }

            public void setParentCity(String parentCity) {
                this.parentCity = parentCity;
            }

            public String getAdminArea() {
                return adminArea;
            }

            public void setAdminArea(String adminArea) {
                this.adminArea = adminArea;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }
        }

        public static class UpdateBean {
            @SerializedName("loc")
            private String loc;
            @SerializedName("utc")
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }

        public static class DailyForecastBean {
            @SerializedName("cond_code_d")
            private String condCodeD;
            @SerializedName("cond_code_n")
            private String condCodeN;
            @SerializedName("cond_txt_d")
            private String condTxtD;
            @SerializedName("cond_txt_n")
            private String condTxtN;
            @SerializedName("date")
            private String date;
            @SerializedName("hum")
            private String hum;
            @SerializedName("mr")
            private String mr;
            @SerializedName("ms")
            private String ms;
            @SerializedName("pcpn")
            private String pcpn;
            @SerializedName("pop")
            private String pop;
            @SerializedName("pres")
            private String pres;
            @SerializedName("sr")
            private String sr;
            @SerializedName("ss")
            private String ss;
            @SerializedName("tmp_max")
            private String tmpMax;
            @SerializedName("tmp_min")
            private String tmpMin;
            @SerializedName("uv_index")
            private String uvIndex;
            @SerializedName("vis")
            private String vis;
            @SerializedName("wind_deg")
            private String windDeg;
            @SerializedName("wind_dir")
            private String windDir;
            @SerializedName("wind_sc")
            private String windSc;
            @SerializedName("wind_spd")
            private String windSpd;

            public String getCondCodeD() {
                return condCodeD;
            }

            public void setCondCodeD(String condCodeD) {
                this.condCodeD = condCodeD;
            }

            public String getCondCodeN() {
                return condCodeN;
            }

            public void setCondCodeN(String condCodeN) {
                this.condCodeN = condCodeN;
            }

            public String getCondTxtD() {
                return condTxtD;
            }

            public void setCondTxtD(String condTxtD) {
                this.condTxtD = condTxtD;
            }

            public String getCondTxtN() {
                return condTxtN;
            }

            public void setCondTxtN(String condTxtN) {
                this.condTxtN = condTxtN;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getMr() {
                return mr;
            }

            public void setMr(String mr) {
                this.mr = mr;
            }

            public String getMs() {
                return ms;
            }

            public void setMs(String ms) {
                this.ms = ms;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }

            public String getTmpMax() {
                return tmpMax;
            }

            public void setTmpMax(String tmpMax) {
                this.tmpMax = tmpMax;
            }

            public String getTmpMin() {
                return tmpMin;
            }

            public void setTmpMin(String tmpMin) {
                this.tmpMin = tmpMin;
            }

            public String getUvIndex() {
                return uvIndex;
            }

            public void setUvIndex(String uvIndex) {
                this.uvIndex = uvIndex;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public String getWindDeg() {
                return windDeg;
            }

            public void setWindDeg(String windDeg) {
                this.windDeg = windDeg;
            }

            public String getWindDir() {
                return windDir;
            }

            public void setWindDir(String windDir) {
                this.windDir = windDir;
            }

            public String getWindSc() {
                return windSc;
            }

            public void setWindSc(String windSc) {
                this.windSc = windSc;
            }

            public String getWindSpd() {
                return windSpd;
            }

            public void setWindSpd(String windSpd) {
                this.windSpd = windSpd;
            }
        }
    }
}
