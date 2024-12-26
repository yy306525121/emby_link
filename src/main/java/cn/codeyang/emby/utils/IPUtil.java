package cn.codeyang.emby.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ArrayUtil;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class IPUtil {
    public String getClientIp(ServerHttpRequest request, String... otherHeaderNames) {
        String[] headers = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        if (ArrayUtil.isNotEmpty(otherHeaderNames)) {
            headers = ArrayUtil.addAll(headers, otherHeaderNames);
        }

        return getClientIPByHeader(request, headers);
    }

    private String getClientIPByHeader(ServerHttpRequest request, String... headerNames) {
        String ip;
        HttpHeaders headers = request.getHeaders();
        for (String header : headerNames) {
            List<String> headerValues = headers.get(header);
            if (CollUtil.isNotEmpty(headerValues)) {
                ip = headerValues.getFirst();
                if (!NetUtil.isUnknown(ip)) {
                    return NetUtil.getMultistageReverseProxyIp(ip);
                }
            }
        }

        InetSocketAddress remoteAddress = request.getRemoteAddress();
        ip = Objects.requireNonNull(remoteAddress).getAddress().getHostAddress();
        return ip;
    }
}
