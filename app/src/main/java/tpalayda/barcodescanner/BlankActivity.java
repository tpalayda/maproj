package tpalayda.barcodescanner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class BlankActivity extends AppCompatActivity {

    private EditText m_id;
    private EditText m_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        m_id = findViewById(R.id.barcode_id);
        m_date = findViewById(R.id.date_id);

        m_id.setText("BarcodeID:"+getIntent().getStringExtra("111"));
        m_date.setText(DateFormat.getDateInstance().format(new Date()));
    }
}
