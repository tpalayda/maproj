package tpalayda.barcodescanner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

import tpalayda.barcodescanner.application.BarcodeBank;
import tpalayda.barcodescanner.application.BarcodeInf;

/**
 * Created by taras on 19/05/18.
 */

public class BarcodeBaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    private static final String DATABASE_NAME = "barcodeBase.db";

    public BarcodeBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + database.BarcodeTable.NAME + "(" +
                   "_id integer primary key autoincrement, "          +
                database.BarcodeTable.Cols.UUID + ", "                +
                database.BarcodeTable.Cols.BARCODEID + ", "           +
                database.BarcodeTable.Cols.PRICE + ", "               +
                database.BarcodeTable.Cols.PRODUCT + ", "             +
                database.BarcodeTable.Cols.DATE + ", "                +
                database.BarcodeTable.Cols.CATEGORY + ")"
        );
        for(int i = 0; i < 20; ++i){
            ContentValues values = BarcodeBank.getContentValues(new BarcodeInf(i+"","product"+i,"dasd", UUID.randomUUID(), DateFormat.getDateInstance().format(new Date())));
            db.insert(database.BarcodeTable.NAME,null,values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
