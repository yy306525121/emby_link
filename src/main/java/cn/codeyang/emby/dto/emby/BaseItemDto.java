/*
 * Emby Server REST API
 * 
 */

package cn.codeyang.emby.dto.emby;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.*;

/**
 * This is strictly used as a data transfer object from the api layer. This holds information about a BaseItem in a format that is convenient for the client.  
 */
@Data
public class BaseItemDto {
  @SerializedName("Name")
  private String name = null;

  @SerializedName("OriginalTitle")
  private String originalTitle = null;

  @SerializedName("ServerId")
  private String serverId = null;

  @SerializedName("Id")
  private String id = null;

  @SerializedName("Guid")
  private String guid = null;

  @SerializedName("Etag")
  private String etag = null;

  @SerializedName("Prefix")
  private String prefix = null;

  @SerializedName("PlaylistItemId")
  private String playlistItemId = null;

  @SerializedName("DateCreated")
  private OffsetDateTime dateCreated = null;

  @SerializedName("ExtraType")
  private String extraType = null;

  @SerializedName("SortIndexNumber")
  private Integer sortIndexNumber = null;

  @SerializedName("SortParentIndexNumber")
  private Integer sortParentIndexNumber = null;

  @SerializedName("CanDelete")
  private Boolean canDelete = null;

  @SerializedName("CanDownload")
  private Boolean canDownload = null;

  @SerializedName("CanEditItems")
  private Boolean canEditItems = null;

  @SerializedName("SupportsResume")
  private Boolean supportsResume = null;

  @SerializedName("PresentationUniqueKey")
  private String presentationUniqueKey = null;

  @SerializedName("PreferredMetadataLanguage")
  private String preferredMetadataLanguage = null;

  @SerializedName("PreferredMetadataCountryCode")
  private String preferredMetadataCountryCode = null;

  @SerializedName("SupportsSync")
  private Boolean supportsSync = null;

  @SerializedName("SyncStatus")
  private SyncJobItemStatus syncStatus = null;

  @SerializedName("CanManageAccess")
  private Boolean canManageAccess = null;

  @SerializedName("CanLeaveContent")
  private Boolean canLeaveContent = null;

  @SerializedName("CanMakePublic")
  private Boolean canMakePublic = null;

  @SerializedName("Container")
  private String container = null;

  @SerializedName("SortName")
  private String sortName = null;

  @SerializedName("ForcedSortName")
  private String forcedSortName = null;

  @SerializedName("Video3DFormat")
  private Video3DFormat video3DFormat = null;

  @SerializedName("PremiereDate")
  private OffsetDateTime premiereDate = null;

  @SerializedName("ExternalUrls")
  private List<ExternalUrl> externalUrls = null;

  @SerializedName("MediaSources")
  private List<MediaSourceInfo> mediaSources = null;

  @SerializedName("CriticRating")
  private Float criticRating = null;

  @SerializedName("GameSystemId")
  private Long gameSystemId = null;

  @SerializedName("AsSeries")
  private Boolean asSeries = null;

  @SerializedName("GameSystem")
  private String gameSystem = null;

  @SerializedName("ProductionLocations")
  private List<String> productionLocations = null;

  @SerializedName("Path")
  private String path = null;

  @SerializedName("OfficialRating")
  private String officialRating = null;

  @SerializedName("CustomRating")
  private String customRating = null;

  @SerializedName("ChannelId")
  private String channelId = null;

  @SerializedName("ChannelName")
  private String channelName = null;

  @SerializedName("Overview")
  private String overview = null;

  @SerializedName("Taglines")
  private List<String> taglines = null;

  @SerializedName("Genres")
  private List<String> genres = null;

  @SerializedName("CommunityRating")
  private Float communityRating = null;

  @SerializedName("RunTimeTicks")
  private Long runTimeTicks = null;

  @SerializedName("Size")
  private Long size = null;

  @SerializedName("FileName")
  private String fileName = null;

  @SerializedName("Bitrate")
  private Integer bitrate = null;

  @SerializedName("ProductionYear")
  private Integer productionYear = null;

  @SerializedName("Number")
  private String number = null;

  @SerializedName("ChannelNumber")
  private String channelNumber = null;

  @SerializedName("IndexNumber")
  private Integer indexNumber = null;

  @SerializedName("IndexNumberEnd")
  private Integer indexNumberEnd = null;

  @SerializedName("ParentIndexNumber")
  private Integer parentIndexNumber = null;

  @SerializedName("RemoteTrailers")
  private List<MediaUrl> remoteTrailers = null;

  @SerializedName("ProviderIds")
  private ProviderIdDictionary providerIds = null;

  @SerializedName("IsFolder")
  private Boolean isFolder = null;

  @SerializedName("ParentId")
  private String parentId = null;

  @SerializedName("Type")
  private String type = null;

  @SerializedName("People")
  private List<BaseItemPerson> people = null;

  @SerializedName("Studios")
  private List<NameLongIdPair> studios = null;

  @SerializedName("GenreItems")
  private List<NameLongIdPair> genreItems = null;

  @SerializedName("TagItems")
  private List<NameLongIdPair> tagItems = null;

  @SerializedName("ParentLogoItemId")
  private String parentLogoItemId = null;

  @SerializedName("ParentBackdropItemId")
  private String parentBackdropItemId = null;

  @SerializedName("ParentBackdropImageTags")
  private List<String> parentBackdropImageTags = null;

  @SerializedName("LocalTrailerCount")
  private Integer localTrailerCount = null;

  @SerializedName("UserData")
  private UserItemDataDto userData = null;

  @SerializedName("RecursiveItemCount")
  private Integer recursiveItemCount = null;

  @SerializedName("ChildCount")
  private Integer childCount = null;

  @SerializedName("SeriesName")
  private String seriesName = null;

  @SerializedName("SeriesId")
  private String seriesId = null;

  @SerializedName("SeasonId")
  private String seasonId = null;

  @SerializedName("SpecialFeatureCount")
  private Integer specialFeatureCount = null;

  @SerializedName("DisplayPreferencesId")
  private String displayPreferencesId = null;

  @SerializedName("Status")
  private String status = null;

  @SerializedName("AirDays")
  private List<DayOfWeek> airDays = null;

  @SerializedName("Tags")
  private List<String> tags = null;

  @SerializedName("PrimaryImageAspectRatio")
  private Double primaryImageAspectRatio = null;

  @SerializedName("Artists")
  private List<String> artists = null;

  @SerializedName("ArtistItems")
  private List<NameIdPair> artistItems = null;

  @SerializedName("Composers")
  private List<NameIdPair> composers = null;

  @SerializedName("Album")
  private String album = null;

  @SerializedName("CollectionType")
  private String collectionType = null;

  @SerializedName("DisplayOrder")
  private String displayOrder = null;

  @SerializedName("AlbumId")
  private String albumId = null;

  @SerializedName("AlbumPrimaryImageTag")
  private String albumPrimaryImageTag = null;

  @SerializedName("SeriesPrimaryImageTag")
  private String seriesPrimaryImageTag = null;

  @SerializedName("AlbumArtist")
  private String albumArtist = null;

  @SerializedName("AlbumArtists")
  private List<NameIdPair> albumArtists = null;

  @SerializedName("SeasonName")
  private String seasonName = null;

  @SerializedName("MediaStreams")
  private List<MediaStream> mediaStreams = null;

  @SerializedName("PartCount")
  private Integer partCount = null;

  @SerializedName("ImageTags")
  private Map<String, String> imageTags = null;

  @SerializedName("BackdropImageTags")
  private List<String> backdropImageTags = null;

  @SerializedName("ParentLogoImageTag")
  private String parentLogoImageTag = null;

  @SerializedName("SeriesStudio")
  private String seriesStudio = null;

  @SerializedName("PrimaryImageItemId")
  private String primaryImageItemId = null;

  @SerializedName("PrimaryImageTag")
  private String primaryImageTag = null;

  @SerializedName("ParentThumbItemId")
  private String parentThumbItemId = null;

  @SerializedName("ParentThumbImageTag")
  private String parentThumbImageTag = null;

  @SerializedName("Chapters")
  private List<ChapterInfo> chapters = null;

  @SerializedName("LocationType")
  private LocationType locationType = null;

  @SerializedName("MediaType")
  private String mediaType = null;

  @SerializedName("EndDate")
  private OffsetDateTime endDate = null;

  @SerializedName("LockedFields")
  private List<MetadataFields> lockedFields = null;

  @SerializedName("LockData")
  private Boolean lockData = null;

  @SerializedName("Width")
  private Integer width = null;

  @SerializedName("Height")
  private Integer height = null;

  @SerializedName("CameraMake")
  private String cameraMake = null;

  @SerializedName("CameraModel")
  private String cameraModel = null;

  @SerializedName("Software")
  private String software = null;

  @SerializedName("ExposureTime")
  private Double exposureTime = null;

  @SerializedName("FocalLength")
  private Double focalLength = null;

  @SerializedName("ImageOrientation")
  private DrawingImageOrientation imageOrientation = null;

  @SerializedName("Aperture")
  private Double aperture = null;

  @SerializedName("ShutterSpeed")
  private Double shutterSpeed = null;

  @SerializedName("Latitude")
  private Double latitude = null;

  @SerializedName("Longitude")
  private Double longitude = null;

  @SerializedName("Altitude")
  private Double altitude = null;

  @SerializedName("IsoSpeedRating")
  private Integer isoSpeedRating = null;

  @SerializedName("SeriesTimerId")
  private String seriesTimerId = null;

  @SerializedName("ChannelPrimaryImageTag")
  private String channelPrimaryImageTag = null;

  @SerializedName("StartDate")
  private OffsetDateTime startDate = null;

  @SerializedName("CompletionPercentage")
  private Double completionPercentage = null;

  @SerializedName("IsRepeat")
  private Boolean isRepeat = null;

  @SerializedName("IsNew")
  private Boolean isNew = null;

  @SerializedName("EpisodeTitle")
  private String episodeTitle = null;

  @SerializedName("IsMovie")
  private Boolean isMovie = null;

  @SerializedName("IsSports")
  private Boolean isSports = null;

  @SerializedName("IsSeries")
  private Boolean isSeries = null;

  @SerializedName("IsLive")
  private Boolean isLive = null;

  @SerializedName("IsNews")
  private Boolean isNews = null;

  @SerializedName("IsKids")
  private Boolean isKids = null;

  @SerializedName("IsPremiere")
  private Boolean isPremiere = null;

  @SerializedName("TimerType")
  private LiveTvTimerType timerType = null;

  @SerializedName("Disabled")
  private Boolean disabled = null;

  @SerializedName("ManagementId")
  private String managementId = null;

  @SerializedName("TimerId")
  private String timerId = null;

  @SerializedName("CurrentProgram")
  private BaseItemDto currentProgram = null;

  @SerializedName("MovieCount")
  private Integer movieCount = null;

  @SerializedName("SeriesCount")
  private Integer seriesCount = null;

  @SerializedName("AlbumCount")
  private Integer albumCount = null;

  @SerializedName("SongCount")
  private Integer songCount = null;

  @SerializedName("MusicVideoCount")
  private Integer musicVideoCount = null;

  @SerializedName("Subviews")
  private List<String> subviews = null;

  @SerializedName("ListingsProviderId")
  private String listingsProviderId = null;

  @SerializedName("ListingsChannelId")
  private String listingsChannelId = null;

  @SerializedName("ListingsPath")
  private String listingsPath = null;

  @SerializedName("ListingsId")
  private String listingsId = null;

  @SerializedName("ListingsChannelName")
  private String listingsChannelName = null;

  @SerializedName("ListingsChannelNumber")
  private String listingsChannelNumber = null;

  @SerializedName("AffiliateCallSign")
  private String affiliateCallSign = null;

}
