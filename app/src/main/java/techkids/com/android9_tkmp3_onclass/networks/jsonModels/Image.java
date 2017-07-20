package techkids.com.android9_tkmp3_onclass.networks.jsonModels;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public class Image {
    private String label;
    private Attribute attributes;

    public Image(String label, Attribute attributes) {
        this.label = label;
        this.attributes = attributes;
    }

    public String getLabel() {

        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Attribute getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Image{" +
                "label='" + label + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
