package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String nameTag2;
    private List<Tag> bodyTag2;

    public PairedTag(String nameTag, Map<String, String> bodyTag, String nameTag2, List<Tag> bodyTag2 ) {
        super(nameTag, bodyTag);
        this.nameTag2 = nameTag2;
        this.bodyTag2 = bodyTag2;
    }
    public String toString() {
        return "<" + super.getNameTag() + super.getBodyTag().entrySet().stream()
                .map(e -> (" " + e.getKey() + "=\"" + e.getValue()) + "\"")
                .collect(Collectors.joining())
                + ">" + nameTag2 + bodyTag2.stream()
                .map(e -> ("<" + e.getNameTag() + " " + e.getBodyTag().entrySet().stream()
                                .map(x -> (x.getKey() + "=\"" + x.getValue()) + "\"")
                                .collect(Collectors.joining())
                        + ">"))
                .collect(Collectors.joining())
                + "</" + getNameTag() + ">";
//        "<p class="m-10" id="10" lang="en">Text paragraph</p>"
    }
}
// END
