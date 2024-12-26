/*
 * Emby Server REST API
 * 
 */

package cn.codeyang.emby.dto.emby;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * This is used by the api to get information about a Person within a BaseItem  
 */
@Data
public class BaseItemPerson {
  @SerializedName("Name")
  private String name = null;

  @SerializedName("Id")
  private String id = null;

  @SerializedName("Role")
  private String role = null;

  @SerializedName("Type")
  private PersonType type = null;

  @SerializedName("PrimaryImageTag")
  private String primaryImageTag = null;
}
