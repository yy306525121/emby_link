package cn.codeyang.emby.client.alist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangzy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlistFileInfoResponse {
    private String code;
    private String message;
    private ResponseData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseData {
        private String name;
        private Long size;
        private Boolean isDir;
        private String sign;
        private Integer type;
        private String rawUrl;
    }

}
