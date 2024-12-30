package cn.codeyang.emby.client.alist.dto;

import lombok.Data;

/**
 * @author yangzy
 */
@Data
public class AlistFileInfoResponse {
    private String code;
    private String message;
    private ResponseData data;

    @lombok.Data
    public static class ResponseData {
        private String name;
        private Long size;
        private Boolean isDir;
        private String sign;
        private Integer type;
        private String rawUrl;
    }

}
