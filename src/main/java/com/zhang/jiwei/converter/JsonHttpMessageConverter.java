package com.zhang.jiwei.converter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;

import static com.alibaba.fastjson.util.IOUtils.UTF8;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
public class JsonHttpMessageConverter extends FastJsonHttpMessageConverter {

    // 防止ie8以下浏览器访问json变下载
    public JsonHttpMessageConverter() {
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(new MediaType("text", "html", UTF8));
        mediaTypeList.add(new MediaType("application", "json", UTF8));
        mediaTypeList.add(new MediaType("application", "*+json", UTF8));
        setSupportedMediaTypes(mediaTypeList);
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (obj instanceof byte[]) {
            super.writeInternal(obj, outputMessage);
            return;
        }
        OutputStream out = outputMessage.getBody();
        String text = JSON.toJSONString(obj);
        byte[] bytes = text.getBytes(UTF8);
        out.write(bytes);
    }
}
