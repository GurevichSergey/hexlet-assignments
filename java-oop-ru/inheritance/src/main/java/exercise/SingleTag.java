package exercise;

import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class SingleTag extends Tag {
    public SingleTag(String nameTag, Map<String, String> bodyTag) {
        super (nameTag, bodyTag);
    }

    public String toString() {
        return "<" + getNameTag() + getBodyTag().entrySet().stream()
                .map(e -> (" " + e.getKey() + "=\"" + e.getValue()) + "\"")
                .collect(Collectors.joining())
                + ">";
//      "<img class="v-10" id="wop">
    }
}
// END
