package tpalayda.barcodescanner.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tpalayda.barcodescanner.database.BarcodeBaseHelper;
import tpalayda.barcodescanner.database.DataBaseCursorWrapper;
import tpalayda.barcodescanner.database.database;

/**
 * Created by taras on 19/05/18.
 */

public class BarcodeBank {
    private static BarcodeBank m_instance;
    private SQLiteDatabase m_database;

    private BarcodeBank(Context context){
        m_database = new BarcodeBaseHelper(context).getWritableDatabase();
    }
    public static BarcodeBank getInstance(Context context){
        if(m_instance == null)
            m_instance = new BarcodeBank(context);
        return m_instance;
    }
    public List<BarcodeInf> getBarcodes(){
        List<BarcodeInf> barcodes = new ArrayList<>();
        DataBaseCursorWrapper cursor = queryBarcodes(null,null);
        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                barcodes.add(cursor.getBarcode());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return barcodes;

    }
    public String[] getCategories()
    {
        List<BarcodeInf> barcodes =  getBarcodes();
        List<String> categories = new ArrayList<String>();

        for(BarcodeInf barcode : barcodes){
            if(categories.contains(barcode.getCategory())){}
            else
                categories.add(barcode.getCategory());
        }
        String[] categoriesString = new String[categories.size()];
        categoriesString = categories.toArray(categoriesString);
        return categoriesString;
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
        String uuid = barcodeInf.getUUID().toString();
        ContentValues values = getContentValues(barcodeInf);
        m_database.update(database.BarcodeTable.NAME,values,database.BarcodeTable.Cols.UUID + " = ? ",new String[]{uuid});
    }
    public void addBarcodeInf(BarcodeInf barcodeInf){
        ContentValues values = getContentValues(barcodeInf);
        m_database.insert(database.BarcodeTable.NAME,null,values);
    }
    public void removeBarcodeInf(BarcodeInf barcodeInf){
        String uuid = barcodeInf.getUUID().toString();
        m_database.delete(database.BarcodeTable.NAME,database.BarcodeTable.Cols.UUID + " = ? ",new String[]{uuid});
    }
    private DataBaseCursorWrapper queryBarcodes(String whereClause,String[] whereArgs){
        Cursor cursor = m_database.query(database.BarcodeTable.NAME,null,whereClause,whereArgs,null,null,null);
        return new DataBaseCursorWrapper(cursor);
    }
    public static ContentValues getContentValues(BarcodeInf barcodeInf){
        ContentValues values = new ContentValues();

        values.put(database.BarcodeTable.Cols.UUID,barcodeInf.getUUID().toString());
        values.put(database.BarcodeTable.Cols.BARCODEID,barcodeInf.getBarcodeID());
        values.put(database.BarcodeTable.Cols.PRICE,barcodeInf.getPrice());
        values.put(database.BarcodeTable.Cols.PRODUCT,barcodeInf.getProductName());
        values.put(database.BarcodeTable.Cols.DATE,barcodeInf.getDate());
        values.put(database.BarcodeTable.Cols.CATEGORY,barcodeInf.getCategory());

        return values;
    }
}
