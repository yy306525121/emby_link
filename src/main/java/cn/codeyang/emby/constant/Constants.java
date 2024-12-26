package cn.codeyang.emby.constant;

/**
 * @author yangzy
 */
public interface Constants {
    /**
     * 标记是否是内网的标识
     */
    String IS_INNER = "isInner";
    String IP = "ip";

    String REDIRECT = "redirect";

    String EMBY_TOKEN_NAME = "X-Emby-Token";

    String MEDIA_SOURCE_ID = "MediaSourceId";

    // playbackInfo地址的正则
    String PLAY_BACK_INFO_PATH_PATTERN = "/emby/Items/*/PlaybackInfo";

    // emby真实播放地址的正则匹配
    public static final String STREAM_PLAY_PATH_PATTERN = "/emby/videos/*/{stream,original}";

    String API_KEY_NAME = "api_key";

}
