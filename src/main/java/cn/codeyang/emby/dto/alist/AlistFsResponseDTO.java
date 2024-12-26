package cn.codeyang.emby.dto.alist;

import lombok.Data;

@Data
public class AlistFsResponseDTO {
    private String code;
    private String message;

    private AlistFsResponseData data;
}

