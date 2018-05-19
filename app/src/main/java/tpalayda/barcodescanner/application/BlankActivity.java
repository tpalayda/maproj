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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

import tpalayda.barcodescanner.R;

public class BlankActivity extends AppCompatActivity {

    private EditText m_id;
    private EditText m_date;
    private EditText m_product;
    private Spinner  m_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        m_id       = findViewById(R.id.barcode_id);
        m_date     = findViewById(R.id.date_id);
        m_product  = findViewById(R.id.product_id);
        m_category = findViewById(R.id.category_id);

        String[] items = new String[]{"products","technology","other"};

        m_category.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,items));
        m_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = m_category.getItemAtPosition(i).toString();
                Toast.makeText(BlankActivity.this,item,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        m_id.setText("BarcodeID:"+getIntent().getStringExtra("111"));
        m_date.setText("Date:"+DateFormat.getDateInstance().format(new Date()));
    }
}
