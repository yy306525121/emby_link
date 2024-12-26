/*
 * Emby Server REST API
 *
 */

package cn.codeyang.emby.dto.emby;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * ExternalUrl
 */


@Data
public class ExternalUrl {
    @SerializedName("Name")
    private String name = null;

    @SerializedName("Url")
    private String url = null;

    public ExternalUrl name(String name) {
        this.name = name;
        return this;
    }
}
