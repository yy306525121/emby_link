package cn.codeyang.emby.client.alist;

import cn.codeyang.emby.client.alist.dto.AlistFileInfoResponse;
import cn.codeyang.emby.client.alist.dto.FileInfoRequest;
import cn.codeyang.emby.config.YangProperties;
import cn.hutool.core.util.URLUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangzy
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AlistClient {
    private final RestTemplate restTemplate;
    private final YangProperties yangProperties;
    private ObjectMapper objectMapper = new ObjectMapper();

    public AlistFileInfoResponse getFileInfo(String path) throws JsonProcessingException {
        String url = URLUtil.completeUrl(yangProperties.getAlist().getInternalBaseUrl(), "/api/fs/get");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", yangProperties.getAlist().getApiKey());


        FileInfoRequest request = new FileInfoRequest();
        request.setPath(path);
        HttpEntity<String> requestEntity =
                new HttpEntity<>(objectMapper.writeValueAsString(request), headers);
        return restTemplate.postForObject(url, requestEntity, AlistFileInfoResponse.class);

    }
}
