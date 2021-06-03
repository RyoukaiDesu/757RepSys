package com.example.a757repsys;

public class Reporter {
    private String category,loc,desc;

    public Reporter(String category, String loc, String desc) {
        this.category = category;
        this.loc = loc;
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
