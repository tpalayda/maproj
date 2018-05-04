package tpalayda.barcodescanner;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;


public class BarcodeFragment extends Fragment implements BarcodeReader.BarcodeReaderListener {

    private BarcodeReader m_barcodeReader;

    public BarcodeFragment(){}
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_barcode,container,false);
        m_barcodeReader = (BarcodeReader) getChildFragmentManager().findFragmentById(R.id.barcode_fragment);
        m_barcodeReader.setListener(this);
        return view;
    }

    @Override
    public void onScanned(final Barcode barcode) {
        Log.e("123","scanned:"+barcode.displayValue);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Barcode:" + barcode.displayValue, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {
        Log.e("123","multiplyscanned:"+barcodes.size());

        String values = "";
        for(Barcode barcode : barcodes){
            values += barcode.displayValue + ", ";
        }
        final String value = values;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Barcodes:"+value, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getActivity(), "Camera permission denied", Toast.LENGTH_SHORT).show();
    }
}
