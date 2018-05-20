package tpalayda.barcodescanner.database;

/**
 * Created by taras on 16/05/18.
 */

public class database {
    public static final class BarcodeTable{
        public static final String NAME = "barcodes";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String BARCODEID = "barcodeID";
            public static final String PRICE = "price";
            public static final String PRODUCT = "product";
            public static final String CATEGORY = "category";
        }
    }
}
