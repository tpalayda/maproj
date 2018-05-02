package tpalayda.barcodescanner;

import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private Button m_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        m_button = findViewById(R.id.tes2);
        m_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuActivity.this,"clicked",Toast.LENGTH_LONG).show();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                BarcodeFragment bf = new BarcodeFragment();
                ft.commit();
            }
        });
    }
}
