package tpalayda.barcodescanner.application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import tpalayda.barcodescanner.R;

import static tpalayda.barcodescanner.application.BarcodeFragment.BARCODE_KEY;

public class EditBlankActivity extends AppCompatActivity {

    private EditText m_id;
    private EditText m_date;
    private EditText m_product;
    private EditText m_other;
    private EditText m_category_item;
    private BarcodeInf m_barcodeInf;
    private UUID m_barcodeUUID = null;
    private Spinner  m_category;
    private Button m_save;
    private Button m_cancel;

    private static final String EXTRA_CURRENT_ID = "current_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity_blank);

        m_id       = findViewById(R.id.barcode_id);
        m_date     = findViewById(R.id.date_id);
        m_product  = findViewById(R.id.product_id);
        m_category = findViewById(R.id.category_id);
        m_category_item = findViewById(R.id.id_category);
        m_other = findViewById(R.id.link_id);
        m_save = findViewById(R.id.save_id);
        m_cancel = findViewById(R.id.cancel_id);

        if(getIntent().hasExtra(EXTRA_CURRENT_ID)) {
            m_barcodeUUID = UUID.fromString(getIntent().getStringExtra(EXTRA_CURRENT_ID));
            m_barcodeInf = BarcodeBank.getInstance(this).getBarcode(m_barcodeUUID);
        }

        final List<String> items = BarcodeBank.getInstance(EditBlankActivity.this).getCategories();
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
                    Toast.makeText(EditBlankActivity.this,"Enter product information",Toast.LENGTH_LONG).show();
                    return;
                }
                if(m_category.getSelectedItem().toString().equals("..."))
                {
                    if(m_category_item.getText().toString().isEmpty() || m_category_item.getText().toString().contains("...") || DoesCategoryExist(m_category_item.getText().toString()))
                    {
                        Toast.makeText(EditBlankActivity.this,"Category already exists or field left empty",Toast.LENGTH_LONG).show();
                        return;
                    }
                    else
                    {
                        items.set(items.size() - 1, m_category_item.getText().toString());
                        items.add("...");
                        m_category_item.setVisibility(View.GONE);
                    }
                }
                m_barcodeInf.setProductName(m_product.getText().toString());
                m_barcodeInf.setCategory(m_category.getSelectedItem().toString());
                BarcodeBank.getInstance(EditBlankActivity.this).updateBarcodeInf(m_barcodeInf);
                finish();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,items);
        m_category.setAdapter(adapter);
       // m_category.setSelection();
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
        if(m_barcodeInf.getBarcodeID().isEmpty())
            m_id.setVisibility(View.GONE);
        else
            m_id.setText(m_barcodeInf.getBarcodeID());
        if(!m_barcodeInf.getOtherInf().isEmpty())
        {
            m_other.setVisibility(View.VISIBLE);
            m_other.setText(m_barcodeInf.getOtherInf());
        }
        m_product.setText(m_barcodeInf.getProductName());
        m_date.setText(m_barcodeInf.getDate());
        m_category.setSelection(adapter.getPosition(m_barcodeInf.getCategory()));
    }
    public static Intent newIntent(Context packageContext, UUID index){
        Intent intent = new Intent(packageContext,EditBlankActivity.class);
        intent.putExtra(EXTRA_CURRENT_ID,index.toString());
        return intent;
    }
    private boolean DoesCategoryExist(String str)
    {
        List<String> list = BarcodeBank.getInstance(EditBlankActivity.this).getCategories();
        if(list.contains(str))
            return true;
        return false;
    }
}
