package cn.codeyang.emby.client.alist;

import cn.codeyang.emby.client.alist.dto.AlistFileInfoResponse;
import cn.codeyang.emby.client.alist.dto.FileInfoRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yangzy
 */
@SpringBootTest
class AlistClientTest {
    @Autowired
    private AlistClient alistClient;

    @Test
    public void test() {
        String path = "/115/video/三级电影/一路向西(2012){tmdb-134764}/一路向西 (2012) {tmdb-134764} [Bluray-1080p][AC3 5.1][x264]-CMCT.mkv";
        AlistFileInfoResponse response = null;
        try {
            response = alistClient.getFileInfo(path);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
    }

}