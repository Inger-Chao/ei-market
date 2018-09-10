package com.inger.market.storage;/**
 * Created by IngerJo on 2018/9/10.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @program: market
 * @author: IngerJo
 * @create: 2018-09-10 17:12
 **/
@Component
@ConfigurationProperties(value = "storage")
public class StorageProperties {


    // 文件上传源
    private String source;

    // 上传文件存储位置
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public File getSourceFile(String fileName) {
        return new File(String.format(source, fileName));
    }

}
