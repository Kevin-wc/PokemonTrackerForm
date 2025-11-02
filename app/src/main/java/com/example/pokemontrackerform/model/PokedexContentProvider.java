package com.example.pokemontrackerform.model;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class PokedexContentProvider extends ContentProvider {
    private MainDatabaseHelper mHelper;
    public final static String DB_NAME = "PokemonDB";
    public final static String COLUMN_NUMBER = "national_number";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_SPECIES = "species";
    public final static String COLUMN_HEIGHT = "height";
    public final static String COLUMN_WEIGHT = "weight";
    public final static String COLUMN_LEVEL = "level";
    public final static String COLUMN_HP = "hp";
    public final static String COLUMN_ATTACK = "attack";
    public final static String COLUMN_DEFENSE = "defense";
    public final static String TABLE_NAME = "PokemonTable";

    public final static String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            "_id INTEGER PRIMARY KEY, " +
            COLUMN_NUMBER + " INTEGER UNIQUE, " +
            COLUMN_NAME + " TEXT NOT NULL, " +
            COLUMN_SPECIES + " TEXT, " +
            COLUMN_HEIGHT + " REAL, " +
            COLUMN_WEIGHT + " REAL, " +
            COLUMN_LEVEL + " INTEGER, " +
            COLUMN_HP + " INTEGER, " +
            COLUMN_ATTACK + " INTEGER, " +
            COLUMN_DEFENSE + " INTEGER )";
    public static final Uri CONTENT_URI =
            Uri.parse("content://pokedex.model.provider");
    protected final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context){
            super(context, DB_NAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(SQL_CREATE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int Newversion){

        }
    }

    public PokedexContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mHelper.getWritableDatabase().delete(TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
//        int number = values.getAsInteger(COLUMN_NUMBER);
//        String name = values.getAsString(COLUMN_NAME);
//        String species = values.getAsString(COLUMN_SPECIES);
//        double height = values.getAsDouble(COLUMN_HEIGHT);
//        double weight = values.getAsDouble(COLUMN_WEIGHT);
//        int level = values.getAsInteger(COLUMN_LEVEL);
//        int hp = values.getAsInteger(COLUMN_HP);
//        int attack = values.getAsInteger(COLUMN_ATTACK);
//        int defense = values.getAsInteger(COLUMN_DEFENSE);
//
//        long id = mHelper.getWritableDatabase().insert("PokemonTable", null, values);

        long rowId = -1;
        try {
            rowId = mHelper.getWritableDatabase().insert(TABLE_NAME, null,values);
        } catch (Exception e){
            return null;
        }

        if (rowId == -1){
            return null;
        }
        return Uri.withAppendedPath(CONTENT_URI, String.valueOf(rowId));
    }

    @Override
    public boolean onCreate() {
        mHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return mHelper.getReadableDatabase().query(TABLE_NAME, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return mHelper.getWritableDatabase().update(TABLE_NAME, values, selection,selectionArgs);
    }
}