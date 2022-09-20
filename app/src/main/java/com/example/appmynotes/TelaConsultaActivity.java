package com.example.appmynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class TelaConsultaActivity extends AppCompatActivity {

    private TextView tvCodigoC, tvTextoC, tvTituloC, tvPrioridadeC, tvVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);
        tvCodigoC = findViewById(R.id.tvCodigoC);
        tvTextoC = findViewById(R.id.tvTextoC);
        tvTituloC = findViewById(R.id.tvTituloC);
        tvPrioridadeC = findViewById(R.id.tvPrioridadeC);
        tvVoltar = findViewById(R.id.tvVoltar);

        Intent intent = getIntent();
        tvCodigoC.setText(intent.getStringExtra("codigo"));
        tvPrioridadeC.setText(intent.getStringExtra("prioridade"));
        tvTextoC.setText(intent.getStringExtra("texto"));
        tvTituloC.setText(intent.getStringExtra("titulo"));

        if(tvPrioridadeC.getText().equals("Baixa")){
            tvPrioridadeC.setTextColor(Color.parseColor("#FF7800"));
        }else if(tvPrioridadeC.getText().equals("Alta")){
            tvPrioridadeC.setTextColor(Color.parseColor("#FF0000"));
        }else if(tvPrioridadeC.getText().equals("Normal")){
            tvPrioridadeC.setTextColor(Color.parseColor("#0000ff"));
        }

        tvVoltar.setOnClickListener(v->{
            finish();
        });

    }
}