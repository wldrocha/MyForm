package wladcom.example.practice01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    //UI elements
    private Button btnConfirm;
    private Button btnShare;

    private String name;
    private int age;
    private int messageType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //Back to main
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnConfirm = findViewById(R.id.buttonConfirm);
        btnShare = findViewById(R.id.buttonShare);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            name = bundle.getString("name");
            age = bundle.getInt("age");
            messageType  = bundle.getInt("option");
        }

        btnShare.setVisibility(View.INVISIBLE);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, createMessage(name, age, messageType), Toast.LENGTH_SHORT).show();
                btnShare.setVisibility(View.VISIBLE);
                btnConfirm.setVisibility(View.INVISIBLE);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, createMessage(name, age, messageType));
                startActivity(intent);
            }
        });


    }

    private String createMessage(String name, int age, int messageType){
        if(messageType == SecondActivity.GREETER_OPTION){
           return "Hola "+ name + ", ¿Cómo llevas esos "+age+" años?";
        }else{
            return "Espero verte pronto "+name+", antes que cumplas "+(age+1);
        }
    }
}
