package tpalayda.barcodescanner.application;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import tpalayda.barcodescanner.R;

import static tpalayda.barcodescanner.application.BarcodeFragment.BARCODE_KEY;

public class BlankActivity extends AppCompatActivity {

    private EditText m_id;
    private EditText m_date;
    private EditText m_product;
    private EditText m_other;
    private EditText m_category_item;
    private BarcodeInf m_barcodeInf;
    private Spinner  m_category;
    private Button m_save;
    private Button m_cancel;

    private static final String EXTRA_CURRENT_ID = "current_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        m_id       = findViewById(R.id.barcode_id);
        m_date     = findViewById(R.id.date_id);
        m_product  = findViewById(R.id.product_id);
        m_category = findViewById(R.id.category_id);
        m_category_item = findViewById(R.id.id_category);
        m_other = findViewById(R.id.link_id);
        m_save = findViewById(R.id.save_id);
        m_cancel = findViewById(R.id.cancel_id);

        final List<String> items = BarcodeBank.getInstance(BlankActivity.this).getCategories();
        items.add("...");

        m_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        m_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_product.getText().toString().isEmpty()) {
                    Toast.makeText(BlankActivity.this,"Enter product information",Toast.LENGTH_LONG).show();
                    return;
                }
                if(m_category.getSelectedItem().toString().equals("..."))
                {
                    if(m_category_item.getText().toString().isEmpty() || DoesCategoryExist(m_category_item.getText().toString()))
                    {
                        Toast.makeText(BlankActivity.this,"Category already exists or field left empty",Toast.LENGTH_LONG).show();
                        return;
                    }
                    else
                    {
                        items.set(items.size() - 1, m_category_item.getText().toString());

                        items.add("...");
                        m_category_item.setVisibility(View.GONE);
                    }
                }
                BarcodeBank.getInstance(BlankActivity.this).addBarcodeInf(new BarcodeInf(m_id.getText().toString(), m_product.getText().toString(), m_category.getSelectedItem().toString(), UUID.randomUUID(),m_date.getText().toString(),m_other.getText().toString()));

                finish();
            }
        });

        m_category.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,items));
        m_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == items.size()-1){
                    m_category_item.setVisibility(View.VISIBLE);
                }
                else
                    m_category_item.setVisibility(View.GONE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if(getIntent().getBooleanExtra("barcodeType",false)) {
               m_other.setVisibility(View.VISIBLE);
               m_id.setVisibility(View.GONE);
               m_other.setText(getIntent().getStringExtra(""+BARCODE_KEY));
           }
        else
               m_id.setText("BarcodeID:" + getIntent().getStringExtra(""+BARCODE_KEY));
        m_date.setText("Date:" + DateFormat.getDateInstance().format(new Date()));
    }
    private boolean DoesCategoryExist(String str)
    {
        List<String> list = BarcodeBank.getInstance(BlankActivity.this).getCategories();
        if(list.contains(str))
            return true;
        return false;
    }
}
