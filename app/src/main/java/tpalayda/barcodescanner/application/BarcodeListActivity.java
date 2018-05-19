package tpalayda.barcodescanner.application;

import android.support.v4.app.Fragment;

public class BarcodeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new BarcodeListFragment();
    }
}
