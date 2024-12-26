/*
 * Emby Server REST API
 * 
 */

package cn.codeyang.emby.dto.emby;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Objects;

/**
 * NameIdPair
 */
@Data
public class NameIdPair {
  @SerializedName("Name")
  private String name = null;

  @SerializedName("Id")
  private String id = null;

  public NameIdPair name(String name) {
    this.name = name;
    return this;
  }

}
