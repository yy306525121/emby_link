/*
 * Emby Server REST API
 *
 */

package cn.codeyang.emby.dto.emby;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * MediaStream information.      MediaStream itens are typically included in a &#x60;MediaBrowser.Model.Dto.MediaSourceInfo&#x60; object.      &#x60;MediaBrowser.Model.Dto.MediaSourceInfo.MediaStreams&#x60;
 */
@Data
public class MediaStream {
    @SerializedName("Codec")
    private String codec = null;

    @SerializedName("CodecTag")
    private String codecTag = null;

    @SerializedName("Language")
    private String language = null;

    @SerializedName("ColorTransfer")
    private String colorTransfer = null;

    @SerializedName("ColorPrimaries")
    private String colorPrimaries = null;

    @SerializedName("ColorSpace")
    private String colorSpace = null;

    @SerializedName("Comment")
    private String comment = null;

    @SerializedName("StreamStartTimeTicks")
    private Long streamStartTimeTicks = null;

    @SerializedName("TimeBase")
    private String timeBase = null;

    @SerializedName("Title")
    private String title = null;

    @SerializedName("Extradata")
    private String extradata = null;

    @SerializedName("VideoRange")
    private String videoRange = null;

    @SerializedName("DisplayTitle")
    private String displayTitle = null;

    @SerializedName("DisplayLanguage")
    private String displayLanguage = null;

    @SerializedName("NalLengthSize")
    private String nalLengthSize = null;

    @SerializedName("IsInterlaced")
    private Boolean isInterlaced = null;

    @SerializedName("IsAVC")
    private Boolean isAVC = null;

    @SerializedName("ChannelLayout")
    private String channelLayout = null;

    @SerializedName("BitRate")
    private Integer bitRate = null;

    @SerializedName("BitDepth")
    private Integer bitDepth = null;

    @SerializedName("RefFrames")
    private Integer refFrames = null;

    @SerializedName("Rotation")
    private Integer rotation = null;

    @SerializedName("Channels")
    private Integer channels = null;

    @SerializedName("SampleRate")
    private Integer sampleRate = null;

    @SerializedName("IsDefault")
    private Boolean isDefault = null;

    @SerializedName("IsForced")
    private Boolean isForced = null;

    @SerializedName("IsHearingImpaired")
    private Boolean isHearingImpaired = null;

    @SerializedName("Height")
    private Integer height = null;

    @SerializedName("Width")
    private Integer width = null;

    @SerializedName("AverageFrameRate")
    private Float averageFrameRate = null;

    @SerializedName("RealFrameRate")
    private Float realFrameRate = null;

    @SerializedName("Profile")
    private String profile = null;

    @SerializedName("Type")
    private MediaStreamType type = null;

    @SerializedName("AspectRatio")
    private String aspectRatio = null;

    @SerializedName("Index")
    private Integer index = null;

    @SerializedName("IsExternal")
    private Boolean isExternal = null;

    @SerializedName("DeliveryMethod")
    private SubtitleDeliveryMethod deliveryMethod = null;

    @SerializedName("DeliveryUrl")
    private String deliveryUrl = null;

    @SerializedName("IsExternalUrl")
    private Boolean isExternalUrl = null;

    @SerializedName("IsTextSubtitleStream")
    private Boolean isTextSubtitleStream = null;

    @SerializedName("SupportsExternalStream")
    private Boolean supportsExternalStream = null;

    @SerializedName("Path")
    private String path = null;

    @SerializedName("Protocol")
    private MediaProtocol protocol = null;

    @SerializedName("PixelFormat")
    private String pixelFormat = null;

    @SerializedName("Level")
    private Double level = null;

    @SerializedName("IsAnamorphic")
    private Boolean isAnamorphic = null;

    @SerializedName("ExtendedVideoType")
    private ExtendedVideoTypes extendedVideoType = null;

    @SerializedName("ExtendedVideoSubType")
    private ExtendedVideoSubTypes extendedVideoSubType = null;

    @SerializedName("ExtendedVideoSubTypeDescription")
    private String extendedVideoSubTypeDescription = null;

    @SerializedName("ItemId")
    private String itemId = null;

    @SerializedName("ServerId")
    private String serverId = null;

    @SerializedName("AttachmentSize")
    private Integer attachmentSize = null;

    @SerializedName("MimeType")
    private String mimeType = null;

    @SerializedName("SubtitleLocationType")
    private SubtitleLocationType subtitleLocationType = null;
}
