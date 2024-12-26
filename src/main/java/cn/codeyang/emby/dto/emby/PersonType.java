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
 * Gets or Sets PersonType
 */
@JsonAdapter(PersonType.Adapter.class)
public enum PersonType {
    ACTOR("Actor"), DIRECTOR("Director"), WRITER("Writer"), PRODUCER("Producer"), GUESTSTAR("GuestStar"), COMPOSER("Composer"), CONDUCTOR("Conductor"), LYRICIST("Lyricist");

    private String value;

    PersonType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static PersonType fromValue(String input) {
        for (PersonType b : PersonType.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }

    public static class Adapter extends TypeAdapter<PersonType> {
        @Override
        public void write(final JsonWriter jsonWriter, final PersonType enumeration) throws IOException {
            jsonWriter.value(String.valueOf(enumeration.getValue()));
        }

        @Override
        public PersonType read(final JsonReader jsonReader) throws IOException {
            Object value = jsonReader.nextString();
            return PersonType.fromValue((String) (value));
        }
    }
}
