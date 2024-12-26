/*
 * Emby Server REST API
 * 
 */

package cn.codeyang.emby.dto.emby;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * NameLongIdPair
 */

@Data
public class NameLongIdPair {
  @SerializedName("Name")
  private String name = null;

  @SerializedName("Id")
  private Long id = null;

}
