package drivequickstart.example.com.myapplication;

import java.text.SimpleDateFormat;

/**
 * Created by arthome on 2016/9/24.
 */

/**
 * 自訂的Class, 用以接收DB裡的資料
 */
public class CustomImage {
    public enum Category {HAT, CLOTHES, PANTS, SHOES}
    private String title, description;
    private byte[] imageByte;
    private long datetimeLong;
    private Category category;
    private int isMatch;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

    private SimpleDateFormat df = new SimpleDateFormat("MMMM d, yy  h:mm");

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDatetime() {
        return datetimeLong;
    }

    public void setDatetime(long datetimeLong) {
        this.datetimeLong = datetimeLong;
    }


    public int getIsMatch() {
        return isMatch;
    }

    public void setIsMatch(int isMatch) {
        this.isMatch = isMatch;
    }
}
