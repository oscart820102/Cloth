package drivequickstart.example.com.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of SqliteImage
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on March 22, 2015.
 */
public class MyDAOdb {

    private SQLiteDatabase database;
    private MyDBhelper dbHelper;

    public MyDAOdb(Context context) {
        dbHelper = new MyDBhelper(context);
        database = dbHelper.getWritableDatabase();
    }

    /**
     * close any database object
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * insert a text report item to the location database table
     *
     * @param image
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long addImage(CustomImage image) {
        ContentValues cv = new ContentValues();
        cv.put(MyDBhelper.COLUMN_TITLE, image.getTitle());
        cv.put(MyDBhelper.COLUMN_DESCRIPTION, image.getDescription());
        cv.put(MyDBhelper.COLUMN_DATETIME, System.currentTimeMillis());
        cv.put(MyDBhelper.COLUMN_IMAGE, image.getImageByte());
        cv.put(MyDBhelper.COLUMN_CATEGORY, image.getCategory().name());
        cv.put(MyDBhelper.COLUMN_IS_MATCH, 0);
        return database.insert(MyDBhelper.TABLE_NAME, null, cv);
    }

    public long updateImage(CustomImage image){ //ismatch變成1


        ContentValues cv = new ContentValues();
//        cv.put(MyDBhelper.COLUMN_TITLE, image.getTitle());
//        cv.put(MyDBhelper.COLUMN_DESCRIPTION, image.getDescription());
//        cv.put(MyDBhelper.COLUMN_DATETIME, System.currentTimeMillis());
//        cv.put(MyDBhelper.COLUMN_IMAGE, image.getImageByte());
//        cv.put(MyDBhelper.COLUMN_CATEGORY, image.getCategory().name());
        cv.put(MyDBhelper.COLUMN_IS_MATCH, 1);

        String whereClause =
                MyDBhelper.COLUMN_TITLE + "=? AND " + MyDBhelper.COLUMN_DATETIME +
                        "=?";
        String[] whereArgs = new String[]{image.getTitle(),
                String.valueOf(image.getDatetime())};

        return database.update(MyDBhelper.TABLE_NAME, cv, whereClause, whereArgs);
    }

    /**
     * delete the given image from database
     *
     * @param image
     */
    public void deleteImage(CustomImage image) {
        String whereClause =
                MyDBhelper.COLUMN_TITLE + "=? AND " + MyDBhelper.COLUMN_DATETIME +
                        "=?";
        String[] whereArgs = new String[]{image.getTitle(),
                String.valueOf(image.getDatetime())};
        database.delete(MyDBhelper.TABLE_NAME, whereClause, whereArgs);
    }

    /**
     * @return all image as a List
     */
    public List<CustomImage> getImages() {
        List<CustomImage> MyImages = new ArrayList<>();
        Cursor cursor =
                database.query(MyDBhelper.TABLE_NAME, null, null, null, null,
                        null, MyDBhelper.COLUMN_DATETIME + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CustomImage MyImage = cursorToMyImage(cursor);
            MyImages.add(MyImage);
            cursor.moveToNext();
        }
        cursor.close();
        return MyImages;
    }

    /**
     * @return all image as a List
     */
    public List<CustomImage> getCategoryImages(String category) {
        List<CustomImage> MyImages = new ArrayList<>();
        Cursor cursor =
                database.query(MyDBhelper.TABLE_NAME, null, null, null, null,
                        null, MyDBhelper.COLUMN_DATETIME + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CustomImage MyImage = cursorToMyImage(cursor);
            if (MyImage.getCategory().name().equals(category))
                MyImages.add(MyImage);
            cursor.moveToNext();
        }
        cursor.close();
        return MyImages;
    }

    /**
     * read the cursor row and convert the row to a MyImage object
     *
     * @param cursor
     * @return MyImage object
     */
    private CustomImage cursorToMyImage(Cursor cursor) {
        CustomImage image = new CustomImage();
//        image.setPath(
//                cursor.getString(cursor.getColumnIndex(DBhelper.COLUMN_PATH)));
        image.setTitle(
                cursor.getString(cursor.getColumnIndex(MyDBhelper.COLUMN_TITLE)));
        image.setDatetime(cursor.getLong(
                cursor.getColumnIndex(MyDBhelper.COLUMN_DATETIME)));
        image.setDescription(cursor.getString(
                cursor.getColumnIndex(MyDBhelper.COLUMN_DESCRIPTION)));
        image.setImageByte(cursor.getBlob(
                cursor.getColumnIndex(MyDBhelper.COLUMN_IMAGE)));
        image.setCategory(Enum.valueOf(CustomImage.Category.class, cursor.getString(cursor.getColumnIndex(MyDBhelper.COLUMN_CATEGORY))));

        image.setIsMatch(cursor.getInt(cursor.getColumnIndex(MyDBhelper.COLUMN_IS_MATCH)));
        return image;
    }
}
