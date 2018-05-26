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
    private EditText m_link;
    private EditText m_category_item;
    private BarcodeInf m_barcodeInf;
    private UUID m_barcodeUUID = null;
    private Spinner  m_category;
    private Button m_save;
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
        m_link = findViewById(R.id.link_id);
        m_save = findViewById(R.id.save_id);

        if(getIntent().hasExtra(EXTRA_CURRENT_ID)) {
            m_barcodeUUID = UUID.fromString(getIntent().getStringExtra(EXTRA_CURRENT_ID));
            m_barcodeInf = BarcodeBank.getInstance(this).getBarcode(m_barcodeUUID);
        }

        final List<String> items = BarcodeBank.getInstance(BlankActivity.this).getCategories();
        items.add("...");
        m_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_product.getText().toString().isEmpty()) {
                    Toast.makeText(BlankActivity.this,"Enter product name",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!m_category_item.getText().toString().isEmpty() && !m_category_item.getText().toString().equals("...")) {
                    items.set(items.size() - 1, m_category_item.getText().toString());
                    items.add("...");
                    m_category_item.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(BlankActivity.this,"Enter category",Toast.LENGTH_LONG).show();
                    return;
                }

                if(m_barcodeUUID == null) {
                    BarcodeBank.getInstance(BlankActivity.this).addBarcodeInf(new BarcodeInf(m_id.getText().toString(), m_product.getText().toString(), m_category.getSelectedItem().toString(), UUID.randomUUID(),m_date.toString()));
                }
                else {

                    m_barcodeInf.setProductName(m_product.getText().toString());
                    m_barcodeInf.setCategory(m_category.getSelectedItem().toString());
                    BarcodeBank.getInstance(BlankActivity.this).updateBarcodeInf(m_barcodeInf);
                }
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
        if(m_barcodeUUID == null) {
            if(getIntent().getBooleanExtra("barcodeType",false)) {
                m_link.setVisibility(View.VISIBLE);
                m_link.setText(getIntent().getStringExtra(""+BARCODE_KEY));
            }
            else
                m_id.setText("BarcodeID:" + getIntent().getStringExtra(""+BARCODE_KEY));
            m_date.setText("Date:" + DateFormat.getDateInstance().format(new Date()));
        }
        else
        {
            m_id.setText(m_barcodeInf.getBarcodeID());
            m_product.setText(m_barcodeInf.getProductName());
            m_date.setText(m_barcodeInf.getDate());
        }
    }
    public static Intent newIntent(Context packageContext, UUID index){
        Intent intent = new Intent(packageContext,BlankActivity.class);
        intent.putExtra(EXTRA_CURRENT_ID,index.toString());
        return intent;
    }
}
