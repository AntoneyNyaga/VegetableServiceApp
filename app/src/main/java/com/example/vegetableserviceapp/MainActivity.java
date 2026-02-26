package com.example.vegetableserviceapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vegetableserviceapp.network.ApiService;
import com.example.vegetableserviceapp.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText nameInput, priceInput;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        priceInput = findViewById(R.id.priceInput);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(v -> {

            String name = nameInput.getText().toString();
            double price = Double.parseDouble(priceInput.getText().toString());

            ApiService apiService = RetrofitClient
                    .getInstance()
                    .create(ApiService.class);

            Call<String> call = apiService.addVegetable(name, price);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(MainActivity.this,
                            "Success: " + response.body(),
                            Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(MainActivity.this,
                            "Error: " + t.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
