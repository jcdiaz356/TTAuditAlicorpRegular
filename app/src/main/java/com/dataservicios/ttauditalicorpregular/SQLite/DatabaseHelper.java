package com.dataservicios.ttauditalicorpregular.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.dataservicios.ttauditalicorpregular.Model.Audit;
import com.dataservicios.ttauditalicorpregular.Model.Categoria;
import com.dataservicios.ttauditalicorpregular.Model.Encuesta;
import com.dataservicios.ttauditalicorpregular.Model.PresenceProduct;
import com.dataservicios.ttauditalicorpregular.Model.PresencePublicity;
import com.dataservicios.ttauditalicorpregular.Model.Publicity;
import com.dataservicios.ttauditalicorpregular.Model.SODVentanas;
import com.dataservicios.ttauditalicorpregular.Model.User;

/**
 * Created by usuario on 12/02/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "db_Alicorp_regular";
    // Table Names
    protected static final String TABLE_POLL = "poll";
    protected static final String TABLE_USER = "user";
    protected static final String TABLE_PRODUCTS = "products";
    protected static final String TABLE_PUBLICITY = "publicity";
    protected static final String TABLE_PRESENCE_PRODUCTS = "presense_products";
    protected static final String TABLE_PRESENCE_PUBLICITY = "presence_publicity";
    protected static final String TABLE_AUDITS = "audits";
    protected static final String TABLE_SOD_VENTANAS = "sodventanas";
    protected static final String TABLE_MEDIAS = "medias";

    //Name columns common
    protected static final String KEY_ID = "id";
    protected static final String KEY_NAME = "name";
    protected static final String KEY_STORE = "store_id";
    protected static final String KEY_POLL = "poll_id";
    protected static final String KEY_DATE_CREATED= "created_at";
    protected static final String KEY_DATE_UPDATE= "update_at";



    //Name columns user
    protected static final String KEY_EMAIL = "email";
    protected static final String KEY_PASSWORD = "password";

    //Name columns poll
    protected static final String KEY_QUESTION = "name_question";
    protected static final String KEY_ID_AUDITORIA = "auditoria_id";

    protected static final String KEY_POLL_ID = "poll_id";

    //Name columns Products
    protected static final String KEY_CODE = "code";
    protected static final String KEY_IMAGEN = "image";
    protected static final String KEY_COMPANY_ID = "company_id";
    protected static final String KEY_CATEGORY_ID = "category_id";
    protected static final String KEY_CATEGORY_NAME = "category_name";
    protected static final String KEY_PRECIO = "precio";
    protected static final String KEY_STATUS = "status";

    //Name columns SODVentanas


    //Name column Presense Product
    protected static final String KEY_STORY_ID = "store_id";
    protected static final String KEY_PRODUCT_ID = "product_id";
    protected static final String KEY_PRECIO_CHECK = "precio_check";
    protected static final String KEY_PRECIO_VISIBLE = "precio_visible";
    protected static final String KEY_STORE_ID = "store_id";


    //Name column Presense Publicity
    protected static final String KEY_PUBLICITY_ID = "publicity_id";
    protected static final String KEY_FOUND = "found";
    protected static final String KEY_VISIBLE = "visible";
    protected static final String KEY_LAYOUT_CORRECT = "layout_correct";
    protected static final String KEY_CONTAMINATED = "contaminated";
    protected static final String KEY_COMMENT = "comment";



    //Name column Table publicity
    protected   static final String KEY_ACTIVE = "active";

    //Name column Audit
    protected static final String KEY_SCORE = "score";


    //Name column Table medias
    protected   static final String KEY_TIPO = "tipo";
    protected   static final String KEY_NAME_FILE = "archivo";
    protected   static final String KEY_TYPE = "type";
    protected   static final String KEY_MONTO = "monto";
    protected   static final String KEY_RAZON_SOCIAL = "razon_social";

    protected   static final String KEY_CATEGORY_PRODUCT_ID = "category_product_id";

    // Table Create Statements
    // eNCUESTA table create statement
    private static final String CREATE_TABLE_POLL = "CREATE TABLE "
            + TABLE_POLL + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_ID_AUDITORIA + " INTEGER,"
            + KEY_QUESTION  + " TEXT " + ")";

    // User table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT,"
            + KEY_EMAIL + " TEXT,"
            + KEY_PASSWORD + " TEXT " + ")";


    // Publicity table create statement

    private static final String CREATE_TABLE_PUBLICITY = "CREATE TABLE "
            + TABLE_PUBLICITY + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT,"
            + KEY_IMAGEN + " TEXT, "
            + KEY_COMPANY_ID + " INTEGER, "
            + KEY_CATEGORY_ID + " INTEGER, "
            + KEY_CATEGORY_NAME + " TEXT, "
            + KEY_ACTIVE + " INTEGER " + ")";

    // Presence product create table statemnt
    private static final String CREATE_TABLE_PRESENCE_PRODUCTS  = "CREATE TABLE "
            + TABLE_PRESENCE_PRODUCTS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_STORY_ID + " INTEGER, "
            + KEY_CATEGORY_ID + " INTEGER, "
            + KEY_CODE + " STRING, "
            + KEY_STATUS + " INTEGER, "
            + KEY_PRECIO_CHECK + " STRING, "
            + KEY_PRECIO_VISIBLE + " INTEGER, "
            + KEY_PRODUCT_ID + " INTEGER )";

    // Presence product create table statemnt
    private static final String CREATE_TABLE_PRESENCE_PUBLICITY  = "CREATE TABLE "
            + TABLE_PRESENCE_PUBLICITY + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_STORY_ID + " INTEGER, "
            + KEY_CATEGORY_ID + " INTEGER, "
            + KEY_FOUND + " INTEGER, "
            + KEY_VISIBLE + " INTEGER, "
            + KEY_LAYOUT_CORRECT + " INTEGER, "
            + KEY_CONTAMINATED + " INTEGER, "
            + KEY_COMMENT + " TEXT, "
            + KEY_PUBLICITY_ID + " INTEGER )";

    // Audit create table statement
    private static final String CREATE_TABLE_AUDITS  = "CREATE TABLE "
            + TABLE_AUDITS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT ,"
            + KEY_STORY_ID + " INTEGER, "
            + KEY_SCORE + " INTEGER )";

    private static final String CREATE_TABLE_SOD_VENTANAS  = "CREATE TABLE "
            + TABLE_SOD_VENTANAS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT ,"
            + KEY_IMAGEN + " TEXT, "
            + KEY_STATUS+ " INTEGER )";
    private static final String CREATE_TABLE_MEDIAS  = "CREATE TABLE "
            + TABLE_MEDIAS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_STORE + " INTEGER ,"
            + KEY_POLL_ID + " INTEGER, "
            + KEY_PUBLICITY_ID + " INTEGER, "
            + KEY_PRODUCT_ID + " INTEGER, "
            + KEY_CATEGORY_PRODUCT_ID + " INTEGER, "
            + KEY_COMPANY_ID + " INTEGER, "
            + KEY_NAME_FILE + " TEXT, "
            + KEY_MONTO + " TEXT, "
            + KEY_RAZON_SOCIAL + " TEXT, "
            + KEY_TYPE + " INTEGER, "
            + KEY_DATE_CREATED + " TEXT )";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_POLL);
        db.execSQL(CREATE_TABLE_USER);

        db.execSQL(CREATE_TABLE_PRESENCE_PRODUCTS);
        db.execSQL(CREATE_TABLE_PRESENCE_PUBLICITY);
        db.execSQL(CREATE_TABLE_AUDITS);
        db.execSQL(CREATE_TABLE_PUBLICITY);
        db.execSQL(CREATE_TABLE_SOD_VENTANAS);
        db.execSQL(CREATE_TABLE_MEDIAS);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POLL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESENCE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESENCE_PUBLICITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUDITS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PUBLICITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOD_VENTANAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDIAS);

        // create new tables
        onCreate(db);
    }


    // ------------------------ "POLS" table methods ----------------//

    /*
     * Creating a Encuesta
     */
    public long createEncuesta(Encuesta encuesta) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, encuesta.getId());
        values.put(KEY_ID_AUDITORIA, encuesta.getIdAuditoria());
        values.put(KEY_QUESTION, encuesta.getQuestion());

        // insert row
        //long todo_id = db.insert(TABLE_PEDIDO, null, values);
         db.insert(TABLE_POLL, null, values);

        long todo_id = encuesta.getId();
        return todo_id;
    }



    /*
     * get single Encuesta
     */
    public Encuesta getEncuesta(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_POLL + " WHERE "
                + KEY_ID + " = " + id;
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        Encuesta pd = new Encuesta();
        pd.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        pd.setIdAiditoria(c.getInt(c.getColumnIndex(KEY_ID_AUDITORIA)));
        pd.setQuestion((c.getString(c.getColumnIndex(KEY_QUESTION))));
        return pd;
    }

    /*
     * get single Encuesta por Auditoria
     */
    public Encuesta getEncuestaAuditoria(long idAuditoria) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_POLL + " WHERE "
                + KEY_ID_AUDITORIA + " = " + idAuditoria;
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        Encuesta pd = new Encuesta();
        pd.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        pd.setIdAiditoria(c.getInt(c.getColumnIndex(KEY_ID_AUDITORIA)));
        pd.setQuestion((c.getString(c.getColumnIndex(KEY_QUESTION))));
        return pd;
    }

    /**
     * getting all Encuesta
     * */
    public List<Encuesta> getAllEncuesta() {
        List<Encuesta> encuesta = new ArrayList<Encuesta>();
        String selectQuery = "SELECT  * FROM " + TABLE_POLL;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Encuesta pd = new Encuesta();
                pd.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pd.setIdAiditoria(c.getInt((c.getColumnIndex(KEY_ID_AUDITORIA))));
                pd.setQuestion((c.getString(c.getColumnIndex(KEY_QUESTION))));

                // adding to todo list
                encuesta.add(pd);
            } while (c.moveToNext());
        }
        return encuesta;
    }

    /*
     * getting Encuesta count
     */
    public int getEncuestaCount() {
        String countQuery = "SELECT  * FROM " + TABLE_POLL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }
    /*
     * Updating a Encuesta
     */
    public int updateEncuesta(Encuesta encuesta) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, encuesta.getQuestion());
        // updating row
        return db.update(TABLE_POLL, values, KEY_ID + " = ?",
                new String[] { String.valueOf(encuesta.getId()) });
    }

    /*
     * Deleting a Encuesta
     */
    public void deleteEncuesta(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POLL, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    /*
     * Deleting all Encuesta
     */
    public void deleteAllEncuesta() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POLL, null, null);

    }


    //---------------------------------------------------------------//
    // ------------------------ "USER" table methods ----------------//
    //---------------------------------------------------------------//
    /*
     * Creating a USER
     */
    /*
     * Creating a USER
     */
    public long createUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, user.getId());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());

        // insert row
        //long todo_id = db.insert(TABLE_PEDIDO, null, values);
        db.insert(TABLE_USER, null, values);

        long todo_id = user.getId();
        return todo_id;
    }



    /*
     * get single User id
     */
    public User getUser(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE "
                + KEY_ID + " = " + id;
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        User pd = new User();
        pd.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        pd.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
        pd.setName((c.getString(c.getColumnIndex(KEY_NAME))));
        pd.setPassword((c.getString(c.getColumnIndex(KEY_PASSWORD))));
        return pd;
    }

    /**
     *
     * @param name
     * @return
     */
    public User getUserName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE "
                + KEY_NAME + " = " + name;
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        User pd = new User();
        pd.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        pd.setName((c.getString(c.getColumnIndex(KEY_NAME))));
        pd.setEmail((c.getString(c.getColumnIndex(KEY_EMAIL))));
        pd.setPassword((c.getString(c.getColumnIndex(KEY_PASSWORD))));
        return pd;
    }

    /**
     *
     * @param email
     * @return
     */
    public User getUserEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE "
                + KEY_EMAIL + " = " + email;
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        User pd = new User();
        pd.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        pd.setName((c.getString(c.getColumnIndex(KEY_NAME))));
        pd.setEmail((c.getString(c.getColumnIndex(KEY_EMAIL))));
        pd.setPassword((c.getString(c.getColumnIndex(KEY_PASSWORD))));
        return pd;
    }

    /**
     * getting all User
     * */
    public List<User> getAllUser() {
        List<User> users = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                User pd = new User();
                pd.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pd.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                pd.setEmail((c.getString(c.getColumnIndex(KEY_EMAIL))));
                pd.setPassword((c.getString(c.getColumnIndex(KEY_PASSWORD))));
                // adding to todo list
                users.add(pd);
            } while (c.moveToNext());
        }
        return users;
    }
    /*
         * getting User count
         */
    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        Log.e(LOG, countQuery);
        cursor.close();
        // return count
        return count;
    }
    /*
     * Updating a User
     */
    public int updatePedido(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_PASSWORD, user.getPassword());
        // updating row
        return db.update(TABLE_USER, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }


    /*
     * Deleting a User
     */
    public void deleteUser(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    /*
     * Deleting all User
     */
    public void deleteAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null );

    }




//---------------------------------------------------------------//
    // ------------------------ "AUDIT" table methods ----------------//
    //-------------------------------------------------------------

    /**
     * Create Audit
     * @param audit
     * @return
     */
    public long createAudit(Audit audit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

       // values.put(KEY_ID, audit.getId());
        values.put(KEY_NAME, audit.getName());
        values.put(KEY_STORY_ID, audit.getStore_id());
        values.put(KEY_SCORE, audit.getScore());

        // insert row
        //long todo_id = db.insert(TABLE_PEDIDO, null, values);
        db.insert(TABLE_AUDITS, null, values);

        long todo_id = audit.getId();
        return todo_id;
    }
    public Audit getAudit(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_AUDITS + " WHERE "
                + KEY_ID + " = " + id;
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        Audit pd = new Audit();
        pd.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        pd.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        pd.setScore((c.getInt(c.getColumnIndex(KEY_SCORE))));
        pd.setStore_id((c.getInt(c.getColumnIndex(KEY_STORY_ID))));


        return pd;
    }




    /**
     * Update Audit
     * @param audit
     * @return
     */
    public int updateAudit(Audit audit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, audit.getId());
        values.put(KEY_NAME, audit.getName());
        values.put(KEY_STORY_ID, audit.getStore_id());
        values.put(KEY_SCORE, audit.getScore());


        // updating row
        return db.update(TABLE_AUDITS, values, KEY_ID + " = ?", new String[] { String.valueOf(audit.getId()) });
    }

    public int updateAuditScore(int audit_id,int score , int store_id ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,  audit_id);
        values.put(KEY_SCORE, score);


        // updating row
        return db.update(TABLE_AUDITS, values, KEY_ID + " = ? and " + KEY_STORY_ID + " = ? ", new String[] { String.valueOf(audit_id) , String.valueOf(store_id)  });
    }

    /**
     * Delete all Audits
     */
    public void deleteAllAudits() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AUDITS, null, null );
    }


    /**
     *
     * @param store_id
     */
    public void deleteAllAuditForStoreId(int store_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AUDITS, KEY_STORY_ID + " = ? ", new String[] { String.valueOf(store_id)  } );
    }

    /**
     * .Return List Audits
     * @return
     */
    public List<Audit> getAllAudits() {
        List<Audit> audit = new ArrayList<Audit>();
        String selectQuery = "SELECT  * FROM " + TABLE_AUDITS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Audit pd = new Audit();
                pd.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pd.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                pd.setStore_id((c.getInt(c.getColumnIndex(KEY_STORY_ID))));
                pd.setScore((c.getInt(c.getColumnIndex(KEY_SCORE))));
                // adding to todo list
                audit.add(pd);
            } while (c.moveToNext());
        }
        return audit;
    }

    /**
     *
     * @param store_id
     * @return
     */
    public List<Audit> getAllAuditsForStoreId(long store_id) {
        List<Audit> audit = new ArrayList<Audit>();
        String selectQuery = "SELECT  * FROM " + TABLE_AUDITS + " WHERE " + KEY_STORY_ID+ " = " + store_id;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Audit pd = new Audit();
                pd.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pd.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                pd.setStore_id((c.getInt(c.getColumnIndex(KEY_STORY_ID))));
                pd.setScore((c.getInt(c.getColumnIndex(KEY_SCORE))));
                // adding to todo list
                audit.add(pd);
            } while (c.moveToNext());
        }
        return audit;
    }





    /**
     *
     * @param id
     * @return
     */

    public int getCountAuditForId(int id) {
        String countQuery = "SELECT  * FROM " + TABLE_AUDITS + " WHERE " +  KEY_ID  + " = " + id  ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        Log.e(LOG, countQuery);
        cursor.close();
        // return count
        return count;
    }

    public int getCountAuditForIdForStoreId(int id,int store_id) {
        String countQuery = "SELECT  * FROM " + TABLE_AUDITS + " WHERE " +  KEY_ID  + " = " + id + " AND " + KEY_STORY_ID  + " = " + store_id  ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        Log.e(LOG, countQuery);
        cursor.close();
        // return count
        return count;
    }

    //---------------------------------------------------------------//
    // ------------------------ "PUBLICITY" table methods ----------------//
    //---------------------------------------------------------------//

    /**
     * Create publicity
     * @param publicity
     * @return
     */
    public long createPublicity(Publicity publicity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, publicity.getId());
        values.put(KEY_NAME, publicity.getName());
        values.put(KEY_ACTIVE, publicity.getActive());
        values.put(KEY_IMAGEN, publicity.getImage());
        values.put(KEY_COMPANY_ID, publicity.getCompany_id());
        values.put(KEY_CATEGORY_ID, publicity.getCategory_id());
        values.put(KEY_CATEGORY_NAME, publicity.getCategory_name());

        // insert row
        //long todo_id = db.insert(TABLE_PEDIDO, null, values);
        db.insert(TABLE_PUBLICITY, null, values);

        long todo_id = publicity.getId();
        return todo_id;
    }


    public Publicity getPublicity(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_PUBLICITY + " WHERE "
                + KEY_ID + " = " + id;
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        Publicity pd = new Publicity();
        pd.setId(c.getInt((c.getColumnIndex(KEY_ID))));
        pd.setName((c.getString(c.getColumnIndex(KEY_NAME))));
        pd.setActive((c.getInt(c.getColumnIndex(KEY_ACTIVE))));
        pd.setImage((c.getString(c.getColumnIndex(KEY_IMAGEN))));
        pd.setCompany_id((c.getInt(c.getColumnIndex(KEY_COMPANY_ID))));
        pd.setCategory_id((c.getInt(c.getColumnIndex(KEY_CATEGORY_ID))));
        pd.setCategory_name((c.getString(c.getColumnIndex(KEY_CATEGORY_NAME))));

        return pd;
    }

    /**
     * Get all publicity
     * @return ListPublicity
     */
    public List<Publicity> getAllPublicity() {
        List<Publicity> publicity = new ArrayList<Publicity>();
        String selectQuery = "SELECT  * FROM " + TABLE_PUBLICITY;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Publicity pd = new Publicity();
                pd.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pd.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                pd.setActive((c.getInt(c.getColumnIndex(KEY_ACTIVE))));
                pd.setImage((c.getString(c.getColumnIndex(KEY_IMAGEN))));
                pd.setCompany_id((c.getInt(c.getColumnIndex(KEY_COMPANY_ID))));
                pd.setCategory_id((c.getInt(c.getColumnIndex(KEY_CATEGORY_ID))));
                pd.setCategory_name((c.getString(c.getColumnIndex(KEY_CATEGORY_NAME))));
                // adding to todo list
                publicity.add(pd);
            } while (c.moveToNext());
        }
        return publicity;
    }
    public int updatePublicity(Publicity publicity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, publicity.getName());
        values.put(KEY_ACTIVE, publicity.getActive());
        values.put(KEY_COMPANY_ID, publicity.getCompany_id());
        values.put(KEY_IMAGEN, publicity.getImage());
        values.put(KEY_CATEGORY_ID, publicity.getCategory_id());
        values.put(KEY_CATEGORY_NAME, publicity.getCategory_name());


        // updating row
        return db.update(TABLE_PUBLICITY, values, KEY_ID + " = ?",  new String[] { String.valueOf(publicity.getId()) });
    }

    /**
     *
     * @return
     */

    public int updatePublicityDesactive() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ACTIVE, 1);



        // updating row
        return db.update(TABLE_PUBLICITY, values, null,  null);
    }

    /**
     * Delete All Publicity
     */
    public void deleteAllPublicity() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PUBLICITY, null, null );

    }


    //---------------------------------------------------------------//
    // ------------------------ "PRESENSE PRODUCTS" table methods ----------------//
    //---------------------------------------------------------------//


    /**
     *
     * @param presenseProduct
     * @return is
     */
    public long createPresenseProduct(PresenceProduct presenseProduct) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //values.put(KEY_ID, product.getId());
        values.put(KEY_STORY_ID, presenseProduct.getStore_id());
        values.put(KEY_CATEGORY_ID, presenseProduct.getCategory_id());
        values.put(KEY_STATUS, presenseProduct.getStatus());
        values.put(KEY_PRECIO_CHECK, presenseProduct.getPrecio_check());
        values.put(KEY_PRECIO_VISIBLE, presenseProduct.getPrecio_visible());
        values.put(KEY_PRODUCT_ID, presenseProduct.getProduct_id());
        values.put(KEY_CODE, presenseProduct.getProduct_Code());



        // insert row
        //long todo_id = db.insert(TABLE_PEDIDO, null, values);
        long id = db.insert(TABLE_PRESENCE_PRODUCTS, null, values);

        //long todo_id = presenseProduct.getId();
        return id;
    }


    public PresenceProduct getPresenceProduct(long product_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_PRESENCE_PRODUCTS + " WHERE "
                + KEY_PRODUCT_ID + " = " + product_id;
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        PresenceProduct pd = new PresenceProduct();
        pd.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        pd.setStore_id(c.getInt(c.getColumnIndex(KEY_STORY_ID)));
        pd.setProduct_id(c.getInt(c.getColumnIndex(KEY_PRODUCT_ID)));
        pd.setProduct_Code((c.getString(c.getColumnIndex(KEY_CODE))));
        pd.setPrecio_check((c.getString(c.getColumnIndex(KEY_PRECIO_CHECK))));
        pd.setPrecio_visible((c.getInt(c.getColumnIndex(KEY_PRECIO_VISIBLE))));
        pd.setStatus((c.getInt(c.getColumnIndex(KEY_STATUS))));
        pd.setCategory_id((c.getInt(c.getColumnIndex(KEY_CATEGORY_ID))));

        return pd;
    }

    /**
     * Return List Presenc Products
     * @return
     */
    public List<PresenceProduct> getAllPresenceProduct() {
        List<PresenceProduct> products = new ArrayList<PresenceProduct>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRESENCE_PRODUCTS ;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                PresenceProduct pd = new PresenceProduct();
                pd.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pd.setProduct_id((c.getInt(c.getColumnIndex(KEY_PRODUCT_ID))));
                pd.setStatus((c.getInt(c.getColumnIndex(KEY_STATUS))));
                pd.setPrecio_check((c.getString(c.getColumnIndex(KEY_PRECIO_CHECK))));
                pd.setPrecio_visible((c.getInt(c.getColumnIndex(KEY_PRECIO_VISIBLE))));
                pd.setCategory_id((c.getInt(c.getColumnIndex(KEY_CATEGORY_ID))));
                pd.setStore_id((c.getInt(c.getColumnIndex(KEY_STORY_ID))));
                // adding to todo list
                products.add(pd);
            } while (c.moveToNext());
        }
        return products;
    }



    /**
     *
     * @param store_id
     * @return
     */
    public List<PresenceProduct> getAllPresenceProductForStoreId(long store_id) {
        List<PresenceProduct> products = new ArrayList<PresenceProduct>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRESENCE_PRODUCTS  + " WHERE "  + KEY_STORY_ID + " = " + store_id;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                PresenceProduct pd = new PresenceProduct();
                pd.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pd.setProduct_id((c.getInt(c.getColumnIndex(KEY_PRODUCT_ID))));
                pd.setStatus((c.getInt(c.getColumnIndex(KEY_STATUS))));
                pd.setPrecio_check((c.getString(c.getColumnIndex(KEY_PRECIO_CHECK))));
                pd.setPrecio_visible((c.getInt(c.getColumnIndex(KEY_PRECIO_VISIBLE))));
                pd.setCategory_id((c.getInt(c.getColumnIndex(KEY_CATEGORY_ID))));
                pd.setStore_id((c.getInt(c.getColumnIndex(KEY_STORY_ID))));
                // adding to todo list
                products.add(pd);
            } while (c.moveToNext());
        }
        return products;
    }


    /**
     * Retun total presence products selected
     * @return
     */
    public int getCountPresenseProduct() {
        String countQuery = "SELECT  * FROM " + TABLE_PRESENCE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        Log.e(LOG, countQuery);
        cursor.close();
        // return count
        return count;
    }

    /**
     *
     * @param product_id
     * @return
     */
    public int getCountPresenseProductForProductId(int product_id) {
        String countQuery = "SELECT  * FROM " + TABLE_PRESENCE_PRODUCTS + " WHERE " +  KEY_PRODUCT_ID  + " = " + product_id ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        Log.e(LOG, countQuery);
        cursor.close();
        // return count
        return count;
    }

    public int getCountPresenseProductForCategory(int category_id) {
        String countQuery = "SELECT  * FROM " + TABLE_PRESENCE_PRODUCTS + " WHERE " +  KEY_CATEGORY_ID  + " = " + category_id ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        Log.e(LOG, countQuery);
        cursor.close();
        // return count
        return count;
    }



    public int getCountPresenseProductForCode(String code_product) {
        String countQuery = "SELECT  * FROM " + TABLE_PRESENCE_PRODUCTS + " WHERE " +  KEY_CODE  + " = " + code_product ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        Log.e(LOG, countQuery);
        cursor.close();
        // return count
        return count;
    }

    public int updateProductPresencePrecioCheck(int product_id,String precio) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRECIO_CHECK, precio);

        // updating row
        return db.update(TABLE_PRESENCE_PRODUCTS, values, KEY_PRODUCT_ID + " = ?",  new String[] { String.valueOf(product_id) });
    }

    public int updateProductPresencePrecioVisible(int product_id,int precio_visible) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRECIO_VISIBLE, precio_visible);

        // updating row
        return db.update(TABLE_PRESENCE_PRODUCTS, values, KEY_PRODUCT_ID + " = ?",  new String[] { String.valueOf(product_id) });
    }
    /**
     * Delete all Presense  Product
     */
    public void deleteAllPresenseProduct() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRESENCE_PRODUCTS, null, null );

    }

    /**
     *
     * @param store_id
     */
    public void deletePresenseProductForStoreId(int store_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRESENCE_PRODUCTS, KEY_STORY_ID + " = ? ",  new String[] { String.valueOf(store_id) } );

    }

//---------------------------------------------------------------//
    // ------------------------ "PRESENSE PUBLYCITY" table methods ----------------//
    //---------------------------------------------------------------//



    public long createPresensePublicity(PresencePublicity presencePublicity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //values.put(KEY_ID, product.getId());
        values.put(KEY_STORY_ID, presencePublicity.getStore_id());
        values.put(KEY_CATEGORY_ID, presencePublicity.getCategory_id());
        values.put(KEY_PUBLICITY_ID, presencePublicity.getPublicity_id());
        values.put(KEY_FOUND, presencePublicity.getFound());
        values.put(KEY_VISIBLE, presencePublicity.getVisible());
        values.put(KEY_LAYOUT_CORRECT, presencePublicity.getLayout_correcto());
        values.put(KEY_CONTAMINATED, presencePublicity.getContaminated());
        values.put(KEY_COMMENT, presencePublicity.getComment());


        // insert row
        //long todo_id = db.insert(TABLE_PEDIDO, null, values);
        long id = db.insert(TABLE_PRESENCE_PUBLICITY, null, values);

        //long todo_id = presenseProduct.getId();
        return id;
    }

    /**
     * Return List Presenc Products
     * @return
     */
    public List<PresencePublicity> getAllPresencePublicity() {
        List<PresencePublicity> publicities = new ArrayList<PresencePublicity>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRESENCE_PUBLICITY;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                PresencePublicity pd = new PresencePublicity();
                pd.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pd.setPublicity_id((c.getInt(c.getColumnIndex(KEY_PUBLICITY_ID))));
                pd.setCategory_id((c.getInt(c.getColumnIndex(KEY_CATEGORY_ID))));
                pd.setStore_id((c.getInt(c.getColumnIndex(KEY_STORY_ID))));
                pd.setFound((c.getInt(c.getColumnIndex(KEY_FOUND))));
                pd.setVisible((c.getInt(c.getColumnIndex(KEY_VISIBLE))));
                pd.setLayout_correcto((c.getInt(c.getColumnIndex(KEY_LAYOUT_CORRECT))));
                pd.setContaminated((c.getInt(c.getColumnIndex(KEY_CONTAMINATED))));
                pd.setComment((c.getString(c.getColumnIndex(KEY_COMMENT))));
                // adding to todo list
                publicities.add(pd);
            } while (c.moveToNext());
        }
        return publicities;
    }

    /**
     *
     * @param store_id
     * @return
     */
    public List<PresencePublicity> getAllPresencePublicityForStoreId(long store_id) {
        List<PresencePublicity> publicities = new ArrayList<PresencePublicity>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRESENCE_PUBLICITY + " WHERE " +  KEY_STORY_ID + " = " + store_id ;;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                PresencePublicity pd = new PresencePublicity();
                pd.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pd.setPublicity_id((c.getInt(c.getColumnIndex(KEY_PUBLICITY_ID))));
                pd.setCategory_id((c.getInt(c.getColumnIndex(KEY_CATEGORY_ID))));
                pd.setStore_id((c.getInt(c.getColumnIndex(KEY_STORY_ID))));
                pd.setFound((c.getInt(c.getColumnIndex(KEY_FOUND))));
                pd.setVisible((c.getInt(c.getColumnIndex(KEY_VISIBLE))));
                pd.setLayout_correcto((c.getInt(c.getColumnIndex(KEY_LAYOUT_CORRECT))));
                pd.setContaminated((c.getInt(c.getColumnIndex(KEY_CONTAMINATED))));
                pd.setComment((c.getString(c.getColumnIndex(KEY_COMMENT))));
                // adding to todo list
                publicities.add(pd);
            } while (c.moveToNext());
        }
        return publicities;
    }



    public List<PresencePublicity> getAllPresencePublicityGroupCategory() {
        List<PresencePublicity> publicities = new ArrayList<PresencePublicity>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRESENCE_PUBLICITY + " GROUP BY " + KEY_CATEGORY_ID ;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                PresencePublicity pd = new PresencePublicity();
                pd.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pd.setPublicity_id((c.getInt(c.getColumnIndex(KEY_PUBLICITY_ID))));
                pd.setCategory_id((c.getInt(c.getColumnIndex(KEY_CATEGORY_ID))));
                pd.setStore_id((c.getInt(c.getColumnIndex(KEY_STORY_ID))));
                pd.setFound((c.getInt(c.getColumnIndex(KEY_FOUND))));
                pd.setVisible((c.getInt(c.getColumnIndex(KEY_VISIBLE))));
                pd.setLayout_correcto((c.getInt(c.getColumnIndex(KEY_LAYOUT_CORRECT))));
                pd.setContaminated((c.getInt(c.getColumnIndex(KEY_CONTAMINATED))));
                pd.setComment((c.getString(c.getColumnIndex(KEY_COMMENT))));
                // adding to todo list
                publicities.add(pd);
            } while (c.moveToNext());
        }
        return publicities;
    }
    /**
     * Retun total presence products selected
     * @return
     */
    public int getCountPresensePublicity() {
        String countQuery = "SELECT  * FROM " + TABLE_PRESENCE_PUBLICITY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        Log.e(LOG, countQuery);
        cursor.close();
        // return count
        return count;
    }

    /**
     *
     * @param category_id
     * @return count
     */
    public int getCountPresensePublicityForCategory(int category_id) {
        String countQuery = "SELECT  * FROM " + TABLE_PRESENCE_PUBLICITY + " WHERE " +  KEY_CATEGORY_ID  + " = " + category_id ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        Log.e(LOG, countQuery);
        cursor.close();
        // return count
        return count;
    }



    /**
     * Delete all Presense  Product
     */
    public void deleteAllPresensePublicity() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRESENCE_PUBLICITY, null, null );

    }


    public void deletePresensePublicitytForStoreId(int store_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRESENCE_PUBLICITY, KEY_STORY_ID + " = ? ",  new String[] { String.valueOf(store_id) } );

    }


    //---------------------------------------------------------------//
    // ------------------------ "SOD_VENTANAS" table methods ----------------//
    //---------------------------------------------------------------//
    /*
     * Creating a SOD_VENTANAS
     */
    /*
     * Creating a SOD_VENTANAS
     */
    public long createSDOVentana(SODVentanas sodVentanas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, sodVentanas.getId());
        values.put(KEY_NAME, sodVentanas.getName());
        values.put(KEY_IMAGEN, sodVentanas.getImage());
        values.put(KEY_STATUS, sodVentanas.getStatus());

        // insert row
        //long todo_id = db.insert(TABLE_PEDIDO, null, values);
        db.insert(TABLE_SOD_VENTANAS, null, values);

        long todo_id = sodVentanas.getId();
        return todo_id;
    }



    /*
     * get single User id
     */
    public SODVentanas getSODVentana(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_SOD_VENTANAS + " WHERE "
                + KEY_ID + " = " + id;
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        SODVentanas pd = new SODVentanas();
        pd.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        pd.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        pd.setImage((c.getString(c.getColumnIndex(KEY_IMAGEN))));
        pd.setStatus((c.getInt(c.getColumnIndex(KEY_STATUS))));

        return pd;
    }






    /**
     * getting all products
     * */
    public List<SODVentanas> getAllSODVentanas() {
        List<SODVentanas> sodventanas = new ArrayList<SODVentanas>();
        String selectQuery = "SELECT  * FROM " + TABLE_SOD_VENTANAS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                SODVentanas pd = new SODVentanas();
                pd.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pd.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                pd.setImage((c.getString(c.getColumnIndex(KEY_IMAGEN))));
                pd.setStatus((c.getInt(c.getColumnIndex(KEY_STATUS))));

                // adding to todo list
                sodventanas.add(pd);
            } while (c.moveToNext());
        }
        return sodventanas;
    }



    public int getSODVentanasStatus(int id) {
        List<Categoria> categorias = new ArrayList<Categoria>();
        String selectQuery = "SELECT  "  + KEY_STATUS +  " FROM " + TABLE_SOD_VENTANAS + " WHERE " + KEY_ID+ " = '" + id + "'" ;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        int status = 0;
        if (c.moveToFirst()) {
            do {
                status =  (c.getInt(c.getColumnIndex(KEY_STATUS)));
            } while (c.moveToNext());
        }
        return status ;
    }
    /*
         * getting User count
         */
    public int getSODVentanasCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SOD_VENTANAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        Log.e(LOG, countQuery);
        cursor.close();
        // return count
        return count;
    }
    /*
     * Updating a User
     */
    public int updateSODVentanas(SODVentanas sodventanas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, sodventanas.getName());
        values.put(KEY_IMAGEN, sodventanas.getImage());
        values.put(KEY_STATUS, sodventanas.getStatus());



        // updating row
        return db.update(TABLE_SOD_VENTANAS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(sodventanas.getId()) });
    }



    public int updateSODVentanasStatus(int id,int status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STATUS, status);

        // updating row
        return db.update(TABLE_SOD_VENTANAS, values, KEY_ID + " = ?",  new String[] { String.valueOf(id) });
    }



    public int updateAllSODVentanasStatus(int status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STATUS, status);

        // updating row
        return db.update(TABLE_SOD_VENTANAS, values, null,  null);
    }


    /*
     * Deleting a User
     */
    public void deleteSODVentanas(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SOD_VENTANAS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    /*
     * Deleting all User
     */
    public void deleteAllSODVentanas() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SOD_VENTANAS, null, null );

    }

    ///////////////////////////****END TABLAS MANTNIMENT**********////////////////////
    //////////////////////////////////////////////////////////////////////////////////


    public static boolean checkDataBase(Context context) {
        SQLiteDatabase checkDB = null;
        try {
            File database=context.getDatabasePath(DATABASE_NAME);
            if (database.exists()) {
                Log.i("Database", "Found");
                String myPath = database.getAbsolutePath();
                Log.i(LOG, myPath);
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
                //return true;
            } else {
                // Database does not exist so copy it from assets here
                Log.i(LOG, "Not Found");
                //return false;
            }
        } catch(SQLiteException e) {
            Log.i(LOG, "Not Found");
        } finally {
            if(checkDB != null) {
                checkDB.close();
            }
        }
        return checkDB != null ? true : false;
    }

}
