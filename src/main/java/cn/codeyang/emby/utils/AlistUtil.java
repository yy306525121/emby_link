package cn.codeyang.emby.utils;

import cn.hutool.core.util.URLUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import lombok.experimental.UtilityClass;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class AlistUtil {
    public HttpResponse fetchAlistPath(String host, String filePath, String token) {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("path", filePath);
        requestBody.put("password", "");

        String url = URLUtil.normalize(host);
        url = URLUtil.completeUrl(url, "/api/fs/get");
        URI hostUri = URLUtil.getHost(URLUtil.url(url));
        HttpRequest response = HttpRequest.post(url)
                .header(Header.CONTENT_TYPE, "application/json;charset=utf-8")
                .header(Header.AUTHORIZATION, token)
                .header(Header.HOST, hostUri.getHost())
                .body(JSONUtil.toJsonStr(requestBody));
        return response.execute();
    }
}
