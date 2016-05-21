package shapio.largo.model;

/**
 * Created by TheOSka on 20/5/2016.
 */
public class Paper {
    String paperName;
    String paperDesc;
    Integer paperThumbImage;

    public Integer getPaperThumbImage() {
        return paperThumbImage;
    }

    public String getPaperDesc() {
        return paperDesc;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperDesc(String paperDesc) {
        this.paperDesc = paperDesc;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public void setPaperThumbImage(Integer paperThumImage) {
        this.paperThumbImage = paperThumImage;
    }
}
