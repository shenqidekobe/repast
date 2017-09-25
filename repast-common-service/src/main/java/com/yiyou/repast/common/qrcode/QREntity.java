package com.yiyou.repast.common.qrcode;

import java.io.Serializable;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QREntity implements Serializable {

    private static final long serialVersionUID = -7770987988957452715L;
    private String data;
    private Integer margin;
    private String encoding;
    private ErrorCorrectionLevel ecLevel;
    private Integer width, height;
    private Integer backgroundColor, foregroundColor;
    private String format;
    private Boolean needBase64;

    public String getData() {
        return data;
    }

    public void setData(String data) {
		this.data = data;
	}

	public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        return encoding;
    }

    public ErrorCorrectionLevel getEcLevel() {
        return ecLevel;
    }

    public void setEcLevel(ErrorCorrectionLevel ecLevel) {
        this.ecLevel = ecLevel;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Integer backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Integer getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Integer foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Boolean isNeedBase64() {
		return needBase64;
	}

	public void setNeedBase64(Boolean needBase64) {
		this.needBase64 = needBase64;
	}

}
