package com.ranran.uums.system.operate.vo;

import java.util.List;

public class SyncTsDistrictJson {

    private String adcode;
    private String name;
    private String center;
    private String level;
    private String citycode;
    private List<SyncTsDistrictJson> districts;

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public List<SyncTsDistrictJson> getDistricts() {
        return districts;
    }

    public void setDistricts(List<SyncTsDistrictJson> districts) {
        this.districts = districts;
    }
}
