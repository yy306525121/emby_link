package cn.codeyang.emby.dto.alist;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AlistFsResponseData {
    @SerializedName("raw_url")
    private String rawUrl;
}
