package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag {
    private String body;
    private List<Tag> chilList;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> chilList) {
        super(name, attributes);
        this.body = body;
        this.chilList = chilList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(body);
        for (Tag tag : chilList) {
            builder.append(tag.toString());
        }
        builder.append("</").append(name).append(">");
        return builder.toString();
    }
}
// END
