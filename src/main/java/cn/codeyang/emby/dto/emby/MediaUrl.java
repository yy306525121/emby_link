/*
 * Emby Server REST API
 *
 */

package cn.codeyang.emby.dto.emby;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * MediaUrl
 */
@Data
public class MediaUrl {
    @SerializedName("Url")
    private String url = null;

    @SerializedName("Name")
    private String name = null;

}
