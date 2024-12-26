/*
 * Emby Server REST API
 * 
 */

package cn.codeyang.emby.dto.emby;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * QueryResultBaseItemDto
 */
@Data
public class QueryResultBaseItemDto {
  @SerializedName("Items")
  private List<BaseItemDto> items = null;

  @SerializedName("TotalRecordCount")
  private Integer totalRecordCount = null;

  public QueryResultBaseItemDto items(List<BaseItemDto> items) {
    this.items = items;
    return this;
  }
}
