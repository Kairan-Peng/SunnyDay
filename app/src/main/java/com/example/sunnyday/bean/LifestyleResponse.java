package com.example.sunnyday.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LifestyleResponse {
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
        "loc": "2021-10-11 13:56",
        "utc": "2021-10-11 05:56"
    },
    "status": "ok",
    "lifestyle": [
        {
            "type": "comf",
            "brf": "舒适",
            "txt": "白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"
        },
        {
            "type": "drsg",
            "brf": "较舒适",
            "txt": "建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"
        },
        {
            "type": "flu",
            "brf": "极易发",
            "txt": "将有一次强降温过程，天气寒冷，且风力较强，极易发生感冒，请特别注意增加衣服保暖防寒。"
        },
        {
            "type": "sport",
            "brf": "较不宜",
            "txt": "有降水，且风力很强，推荐您选择室内运动；若坚持户外运动，请注意保暖并携带雨具。"
        },
        {
            "type": "trav",
            "brf": "一般",
            "txt": "温度适宜，但风大，且预报有降水，可能对您的出行产生一定的影响，外出请注意防风防雨。"
        },
        {
            "type": "uv",
            "brf": "最弱",
            "txt": "属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"
        },
        {
            "type": "cw",
            "brf": "不宜",
            "txt": "不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"
        },
        {
            "type": "air",
            "brf": "优",
            "txt": "气象条件非常有利于空气污染物稀释、扩散和清除，可在室外正常活动。"
        }
    ]
}]}
*/
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
        @SerializedName("lifestyle")
        private List<LifestyleBean> lifestyle;

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

        public List<LifestyleBean> getLifestyle() {
            return lifestyle;
        }

        public void setLifestyle(List<LifestyleBean> lifestyle) {
            this.lifestyle = lifestyle;
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

        public static class LifestyleBean {
            @SerializedName("type")
            private String type;
            @SerializedName("brf")
            private String brf;
            @SerializedName("txt")
            private String txt;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }
    }
}
