package com.yiyou.repast.common.qrcode;

import java.io.Serializable;

/**
 * Image size entity
 *
 */
public class ImageEntity implements Serializable {
	
	private static final long serialVersionUID = 1058448747698504512L;
	private int height;
    private int width;
    private int margin;
    private int fgColor;
    private int bgColor;

    public ImageEntity() {
    }

    public ImageEntity(int height, int width, int margin, int fgColor, int bgColor) {
        this.height = height;
        this.width = width;
        this.margin = margin;
        this.fgColor = fgColor;
        this.bgColor = bgColor;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public int getFgColor() {
        return fgColor;
    }

    public void setFgColor(int fgColor) {
        this.fgColor = fgColor;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    @Override
    public String toString() {
        return "ImageEntity{" +
                "height=" + height +
                ", width=" + width +
                ", margin=" + margin +
                ", fgColor=" + fgColor +
                ", bgColor=" + bgColor +
                '}';
    }
}
