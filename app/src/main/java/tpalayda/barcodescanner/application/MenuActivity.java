package tpalayda.barcodescanner.application;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import tpalayda.barcodescanner.R;

public class MenuActivity extends AppCompatActivity {

    private Button m_button;
    private Button m_showButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        m_button = findViewById(R.id.scan_id);
        m_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,BarcodeFragmentActivity.class));
            }
        });
        m_showButton = findViewById(R.id.show_id);
        m_showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,BarcodeListActivity.class));
            }
        });

    }
}
