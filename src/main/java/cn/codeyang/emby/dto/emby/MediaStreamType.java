/*
 * Emby Server REST API
 *
 */

package cn.codeyang.emby.dto.emby;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Enum MediaStreamType
 */
@JsonAdapter(MediaStreamType.Adapter.class)
public enum MediaStreamType {
    UNKNOWN("Unknown"), AUDIO("Audio"), VIDEO("Video"), SUBTITLE("Subtitle"), EMBEDDEDIMAGE("EmbeddedImage"), ATTACHMENT("Attachment"), DATA("Data");

    private String value;

    MediaStreamType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static MediaStreamType fromValue(String input) {
        for (MediaStreamType b : MediaStreamType.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }

    public static class Adapter extends TypeAdapter<MediaStreamType> {
        @Override
        public void write(final JsonWriter jsonWriter, final MediaStreamType enumeration) throws IOException {
            jsonWriter.value(String.valueOf(enumeration.getValue()));
        }

        @Override
        public MediaStreamType read(final JsonReader jsonReader) throws IOException {
            Object value = jsonReader.nextString();
            return MediaStreamType.fromValue((String) (value));
        }
    }
}
