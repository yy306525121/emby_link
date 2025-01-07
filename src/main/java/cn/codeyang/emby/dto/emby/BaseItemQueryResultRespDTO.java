package cn.codeyang.emby.dto.emby;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yangzy
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class BaseItemQueryResultRespDTO {

    @JsonProperty("Items")
    private List<Item> items;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    public static class Item {
        @JsonProperty("MediaSources")
        private List<MediaSourceInfo> mediaSources;

        @JsonProperty("Path")
        private String path;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    public static class MediaSourceInfo {
        @JsonProperty("Id")
        private String id;
        @JsonProperty("Path")
        private String path;
    }
}
