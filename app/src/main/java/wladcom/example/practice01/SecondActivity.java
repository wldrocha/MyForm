package wladcom.example.practice01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private String name = "";
    private int age = 16;
    private TextView textViewAge;
    private Button btnNextView;
    private RadioButton radioButtonGreeter;
    private RadioButton radioButtonFarewell;
    private SeekBar seekBarAge;

    public static final int MIN_AGE = 16;
    public static final int MAX_AGE = 60;

    public static final int GREETER_OPTION = 1;
    public static final int FAREWELL_OPTION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Back to main
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get name of prev activity
        Bundle bundle = getIntent().getExtras();
        if(bundle != null ){
            name = bundle.getString("name");
        }

        //instance UI elements
        radioButtonGreeter = findViewById(R.id.radioButtonGreeter);
        radioButtonFarewell = findViewById(R.id.radioButtonFarewell);
        seekBarAge = findViewById(R.id.seekBar);
        textViewAge = findViewById(R.id.textViewAge);
        btnNextView = findViewById(R.id.buttonNextActivity);

        seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentAge, boolean fromUser) {
                age = currentAge;
                textViewAge.setText(age + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Aunque no se sobreescriba, esto debe permanecer por ser una interfaz
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                age = seekBar.getProgress();
                textViewAge.setText(age + "");

                if(age < MIN_AGE){
                    btnNextView.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "The min age allowed is :"+MIN_AGE+" years old", Toast.LENGTH_SHORT).show();
                }else if(age > MAX_AGE){
                    btnNextView.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "The max age allowed is :"+MAX_AGE+" years old", Toast.LENGTH_SHORT).show();
                }else{
                    btnNextView.setVisibility(View.VISIBLE);
                }
            }
        });


        btnNextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int option
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);

                if(radioButtonFarewell.isChecked() || radioButtonGreeter){
                    option = (radioButtonGreeter.isChecked()) ? GREETER_OPTION : FAREWELL_OPTION;
                    intent.putExtra("option", option);
                    startActivity(intent);
                }else{
                    Toast.makeText(SecondActivity.this, "Select option Farewell or greeter", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
