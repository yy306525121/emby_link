/*
 * Emby Server REST API
 *
 */

package cn.codeyang.emby.dto.emby;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.*;

/**
 * MediaSourceInfo
 */

@Data
public class MediaSourceInfo {
    @SerializedName("Protocol")
    private MediaProtocol protocol = null;

    @SerializedName("Id")
    private String id = null;

    @SerializedName("Path")
    private String path = null;

    @SerializedName("EncoderPath")
    private String encoderPath = null;

    @SerializedName("EncoderProtocol")
    private MediaProtocol encoderProtocol = null;

    @SerializedName("Type")
    private MediaSourceType type = null;

    @SerializedName("ProbePath")
    private String probePath = null;

    @SerializedName("ProbeProtocol")
    private MediaProtocol probeProtocol = null;

    @SerializedName("Container")
    private String container = null;

    @SerializedName("Size")
    private Long size = null;

    @SerializedName("Name")
    private String name = null;

    @SerializedName("SortName")
    private String sortName = null;

    @SerializedName("IsRemote")
    private Boolean isRemote = null;

    @SerializedName("HasMixedProtocols")
    private Boolean hasMixedProtocols = null;

    @SerializedName("RunTimeTicks")
    private Long runTimeTicks = null;

    @SerializedName("ContainerStartTimeTicks")
    private Long containerStartTimeTicks = null;

    @SerializedName("SupportsTranscoding")
    private Boolean supportsTranscoding = null;

    @SerializedName("TrancodeLiveStartIndex")
    private Integer trancodeLiveStartIndex = null;

    @SerializedName("WallClockStart")
    private OffsetDateTime wallClockStart = null;

    @SerializedName("SupportsDirectStream")
    private Boolean supportsDirectStream = null;

    @SerializedName("SupportsDirectPlay")
    private Boolean supportsDirectPlay = null;

    @SerializedName("IsInfiniteStream")
    private Boolean isInfiniteStream = null;

    @SerializedName("RequiresOpening")
    private Boolean requiresOpening = null;

    @SerializedName("OpenToken")
    private String openToken = null;

    @SerializedName("RequiresClosing")
    private Boolean requiresClosing = null;

    @SerializedName("LiveStreamId")
    private String liveStreamId = null;

    @SerializedName("BufferMs")
    private Integer bufferMs = null;

    @SerializedName("RequiresLooping")
    private Boolean requiresLooping = null;

    @SerializedName("SupportsProbing")
    private Boolean supportsProbing = null;

    @SerializedName("Video3DFormat")
    private Video3DFormat video3DFormat = null;

    @SerializedName("MediaStreams")
    private List<MediaStream> mediaStreams = null;

    @SerializedName("Formats")
    private List<String> formats = null;

    @SerializedName("Bitrate")
    private Integer bitrate = null;

    @SerializedName("Timestamp")
    private TransportStreamTimestamp timestamp = null;

    @SerializedName("RequiredHttpHeaders")
    private Map<String, String> requiredHttpHeaders = null;

    @SerializedName("DirectStreamUrl")
    private String directStreamUrl = null;

    @SerializedName("AddApiKeyToDirectStreamUrl")
    private Boolean addApiKeyToDirectStreamUrl = null;

    @SerializedName("TranscodingUrl")
    private String transcodingUrl = null;

    @SerializedName("TranscodingSubProtocol")
    private String transcodingSubProtocol = null;

    @SerializedName("TranscodingContainer")
    private String transcodingContainer = null;

    @SerializedName("AnalyzeDurationMs")
    private Integer analyzeDurationMs = null;

    @SerializedName("ReadAtNativeFramerate")
    private Boolean readAtNativeFramerate = null;

    @SerializedName("DefaultAudioStreamIndex")
    private Integer defaultAudioStreamIndex = null;

    @SerializedName("DefaultSubtitleStreamIndex")
    private Integer defaultSubtitleStreamIndex = null;

    @SerializedName("ItemId")
    private String itemId = null;

    @SerializedName("ServerId")
    private String serverId = null;
}
