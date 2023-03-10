package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    private String nameTag;
    private Map<String, String> bodyTag;

    public Tag(String nameTag, Map<String, String> bodyTag) {
        this.nameTag = nameTag;
        this.bodyTag = bodyTag;
    }

    public String getNameTag() {
        return nameTag;
    }

    public Map<String, String> getBodyTag() {
        return bodyTag;
    }

    public abstract String toString();

}
// END
