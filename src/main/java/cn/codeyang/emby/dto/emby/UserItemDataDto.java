/*
 * Emby Server REST API
 * 
 */

package cn.codeyang.emby.dto.emby;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Class UserItemDataDto  
 */
@Data
public class UserItemDataDto {
  @SerializedName("Rating")
  private Double rating = null;

  @SerializedName("PlayedPercentage")
  private Double playedPercentage = null;

  @SerializedName("UnplayedItemCount")
  private Integer unplayedItemCount = null;

  @SerializedName("PlaybackPositionTicks")
  private Long playbackPositionTicks = null;

  @SerializedName("PlayCount")
  private Integer playCount = null;

  @SerializedName("IsFavorite")
  private Boolean isFavorite = null;

  @SerializedName("LastPlayedDate")
  private OffsetDateTime lastPlayedDate = null;

  @SerializedName("Played")
  private Boolean played = null;

  @SerializedName("Key")
  private String key = null;

  @SerializedName("ItemId")
  private String itemId = null;

  @SerializedName("ServerId")
  private String serverId = null;
}
