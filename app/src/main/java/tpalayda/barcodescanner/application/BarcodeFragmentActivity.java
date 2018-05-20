package tpalayda.barcodescanner.application;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tpalayda.barcodescanner.R;

public class BarcodeFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_fragment);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BarcodeFragment bf = new BarcodeFragment();
        ft.add(R.id.container,bf);
        ft.commit();
    }
}
