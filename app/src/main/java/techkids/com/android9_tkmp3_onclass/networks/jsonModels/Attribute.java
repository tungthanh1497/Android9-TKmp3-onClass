package techkids.com.android9_tkmp3_onclass.networks.jsonModels;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public class Attribute {
    private int height;

    public Attribute(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return height+"";
    }
}
