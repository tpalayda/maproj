package tpalayda.barcodescanner.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import tpalayda.barcodescanner.application.BarcodeInf;

/**
 * Created by taras on 16/05/18.
 */

public class DataBaseCursorWrapper  extends CursorWrapper{
    public DataBaseCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public BarcodeInf getBarcode(){
        return BarcodeInf();
    }
}
