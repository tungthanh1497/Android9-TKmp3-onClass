package techkids.com.android9_tkmp3_onclass.networks.jsonModels;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public class Name {
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Name(String label) {

        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
