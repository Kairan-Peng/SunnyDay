package com.example.sunnyday.bean;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
{"HeWeather6":[{
        "basic":{
        "cid":"CN101210101",
        "location":"杭州",
        "parent_city":"杭州",
        "admin_area":"浙江省",
        "cnty":"中国",
        "lat":"30.287458",
        "lon":"120.153580",
        "tz":"+8.00"
        },
        "update":{
        "loc":"2021-10-09 20:27",
        "utc":"2021-10-09 12:27"
        },
        "status":"ok",
        "now":{
        "cloud":"91",
        "cond_code":"100",
        "cond_txt":"晴",
        "fl":"27",
        "hum":"75",
        "pcpn":"0.0",
        "pres":"1010",
        "tmp":"27",
        "vis":"15",
        "wind_deg":"45",
        "wind_dir":"东北风",
        "wind_sc":"4",
        "wind_spd":"22"
        }
        }]}
*/

public class NowWeatherResponse {

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
        @SerializedName("now")
        private NowBean now;

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

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
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

        public static class NowBean {
            @SerializedName("cloud")
            private String cloud;
            @SerializedName("cond_code")
            private String condCode;
            @SerializedName("cond_txt")
            private String condTxt;
            @SerializedName("fl")
            private String fl;
            @SerializedName("hum")
            private String hum;
            @SerializedName("pcpn")
            private String pcpn;
            @SerializedName("pres")
            private String pres;
            @SerializedName("tmp")
            private String tmp;
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

            public String getCloud() {
                return cloud;
            }

            public void setCloud(String cloud) {
                this.cloud = cloud;
            }

            public String getCondCode() {
                return condCode;
            }

            public void setCondCode(String condCode) {
                this.condCode = condCode;
            }

            public String getCondTxt() {
                return condTxt;
            }

            public void setCondTxt(String condTxt) {
                this.condTxt = condTxt;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
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
