package exercise;

import java.util.Map;

// BEGIN
public abstract class Tag {
    protected String name;
    protected Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("<" + name);
        for (String key : attributes.keySet()) {
            builder.append(String.format(" %s=\"%s\"", key, attributes.get(key)));
        }
        builder.append(">");
        return builder.toString();
    }
}
// END
