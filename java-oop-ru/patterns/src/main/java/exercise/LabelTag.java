package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String tag;
    private TagInterface obj;

    public LabelTag(String tag, TagInterface obj) {
        this.tag = tag;
        this.obj = obj;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", tag, obj.render());
    }
}
// END
