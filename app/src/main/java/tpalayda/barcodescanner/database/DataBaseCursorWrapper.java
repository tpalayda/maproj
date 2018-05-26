package tpalayda.barcodescanner.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import tpalayda.barcodescanner.application.BarcodeInf;

/**
 * Created by taras on 16/05/18.
 */

public class DataBaseCursorWrapper  extends CursorWrapper{
    public DataBaseCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public BarcodeInf getBarcode(){
        String uuidString = getString(getColumnIndex(database.BarcodeTable.Cols.UUID));
        String barcodeIDString = getString(getColumnIndex(database.BarcodeTable.Cols.BARCODEID));
        int priceInt = getInt(getColumnIndex(database.BarcodeTable.Cols.PRICE));
        String categoryString = getString(getColumnIndex(database.BarcodeTable.Cols.CATEGORY));
        String productString = getString(getColumnIndex(database.BarcodeTable.Cols.PRODUCT));
        String dateString = getString(getColumnIndex(database.BarcodeTable.Cols.DATE));

        BarcodeInf barcodeInf = new BarcodeInf(barcodeIDString,productString,categoryString, UUID.fromString(uuidString),priceInt,dateString);
        return barcodeInf;
    }
}
