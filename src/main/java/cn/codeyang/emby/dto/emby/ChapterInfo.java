/*
 * Emby Server REST API
 *
 */

package cn.codeyang.emby.dto.emby;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Objects;

/**
 * Class ChapterInfo
 */
@Data
public class ChapterInfo {
    @SerializedName("StartPositionTicks")
    private Long startPositionTicks = null;

    @SerializedName("Name")
    private String name = null;

    @SerializedName("ImageTag")
    private String imageTag = null;

    @SerializedName("MarkerType")
    private MarkerType markerType = null;

    @SerializedName("ChapterIndex")
    private Integer chapterIndex = null;
}
