package com.example.SQLprac2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dominicnunes on 12/30/2013.
 */
public class Database {



    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;


    private String[] collumn =
            {SQLiteHelper.COLUMN};


    public Database(Context context) {
        dbHelper = new SQLiteHelper(context);
    }


    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Comment createComment(String comment) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN, comment);
        long insertId = database.insert(SQLiteHelper.TABLE_COMMENTS, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_COMMENTS,
                collumn, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }


    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_COMMENTS,
                collumn, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }


    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(0));
        return comment;
    }

}
