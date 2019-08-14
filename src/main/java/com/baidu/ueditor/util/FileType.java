package com.baidu.ueditor.util;


import java.util.HashMap;
import java.util.Map;

/**
 * 文件类型
 * @author bingglewang
 *
 */
public enum FileType {
    /**
     * JEPG.
     */
    JPEG("FFD8FF",".jpg"),

    /**
     * PNG.
     */
    PNG("89504E47",".png"),

    /**
     * GIF.
     */
    GIF("47494638",".gif"),

    /**
     * TIFF.
     */
    TIFF("49492A00",".tif"),

    /**
     * Windows Bitmap.
     */
    BMP("424D",".bmp");





    private String value = "";
    private String extend = "";

    /**
     * Constructor.
     * @param value
     * @param extend
     */
    private FileType(String value, String extend) {
        this.value = value;
        this.extend =extend;
    }

    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public static Map toMap() {
        Map map = new HashMap();
        for (FileType fileType : FileType.values()) {
            map.put(fileType.getValue(),fileType.getExtend());
        }
        return map;
    }
}
