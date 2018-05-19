package tpalayda.barcodescanner.application;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tpalayda.barcodescanner.database.DataBaseCursorWrapper;
import tpalayda.barcodescanner.database.database;

/**
 * Created by taras on 19/05/18.
 */

public class BarcodeBank {
    private static BarcodeBank m_instance;
    private SQLiteDatabase m_database;

    private BarcodeBank(Context context){

    }
    public static BarcodeBank getInstance(Context context){
        if(m_instance == null)
            m_instance = new BarcodeBank(context);
        return m_instance;
    }
    public List<BarcodeInf> getBarcodes(){
        List<BarcodeInf> barcodes = new ArrayList<>();
        return barcodes;

    }
    public BarcodeInf getBarcode(UUID id){
        DataBaseCursorWrapper cursor = queryBarcodes(database.BarcodeTable.Cols.UUID + " = ? ",new String[]{id.toString()});
        try{
            if(cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getBarcode();
        }
        finally {
            cursor.close();
        }
    }
    public void updateBarcodeInf(BarcodeInf barcodeInf){

    }
    private DataBaseCursorWrapper queryBarcodes(String whereClause,String[] whereArgs){
        Cursor cursor = m_database.query(database.BarcodeTable.NAME,null,whereClause,whereArgs,null,null,null);
        return new DataBaseCursorWrapper(cursor);
    }
}
