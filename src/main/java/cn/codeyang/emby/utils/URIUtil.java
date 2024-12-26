package cn.codeyang.emby.utils;

import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class URIUtil {
    /**
     * 从uri地址中获取参数
     * @param uri
     * @return
     */
    public Map<String, String> getParams(URI uri) {
        String query = uri.getQuery();
        Map<String, String> params = new HashMap<>();
        if (StrUtil.isEmpty(query)) {
            return params;
        }

        List<String> pairs = StrUtil.split(query, CharPool.AMP);
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            if (idx > 0) {
                String key = pair.substring(0, idx);
                String value = pair.length() > idx + 1 ? pair.substring(idx + 1) : null;
                params.put(key, value);
            }
        }

        return params;
    }

    public String getItemIdByUri(URI uri) {
        // 获取uri中的纯数字部分
        String path = uri.getPath();
        return path.replaceAll("^.*?(\\d+).*?$", "$1");
    }
}
