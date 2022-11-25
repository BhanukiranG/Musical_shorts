package com.example.mymusicalshorts;
public class videomodel {
    public String name,videolink;
    public videomodel(String name, String videolink) {
        this.name = name;
        this.videolink = videolink;
    }
    videomodel(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink;
    }
}
