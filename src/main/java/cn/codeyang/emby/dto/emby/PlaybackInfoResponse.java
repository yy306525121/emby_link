/*
 * Emby Server REST API
 *
 */

package cn.codeyang.emby.dto.emby;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * PlaybackInfoResponse
 */
@Data
@NoArgsConstructor
public class PlaybackInfoResponse {

    @SerializedName("MediaSources")
    private List<MediaSourceInfo> mediaSources = null;

    @SerializedName("PlaySessionId")
    private String playSessionId = null;

    @SerializedName("ErrorCode")
    private PlaybackErrorCode errorCode = null;

}
