package com.example.appmynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appmynotes.db.bean.Lembrete;
import com.example.appmynotes.db.bean.Prioridade;
import com.example.appmynotes.db.dal.LembreteDal;

public class NovaAnotacao extends AppCompatActivity {

    private Spinner spPrioridade;
    private EditText edTitulo, edTexto;
    private Button btnSalvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_anotacao);
        spPrioridade = findViewById(R.id.spPrioridade);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.prioridadedes,
                        android.R.layout.simple_spinner_item);
        spPrioridade.setAdapter(adapter);
        edTexto=findViewById(R.id.edTexto);
        edTitulo=findViewById(R.id.edTitulo);
        btnSalvar=findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(v->{salvarAnatocao();});
    }

    public void salvarAnatocao(){
        String titulo, texto;
        long prioridadeId;
        titulo = edTitulo.getText().toString();
        texto = edTexto.getText().toString();
        prioridadeId = spPrioridade.getSelectedItemId();
        //System.out.println("Titulo: "+titulo +"\nTexto: "+texto +"\nPrioridade: "+prioridadeId+1);
        Lembrete novo = new Lembrete(titulo, texto, new Prioridade(prioridadeId+1, ""));
        LembreteDal lembreteDal = new LembreteDal(this);
        if(lembreteDal.salvar(novo)){
            Toast.makeText(this,"Armazenado com sucesso",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Não foi possivel armazenar a anotação :(",Toast.LENGTH_LONG).show();
        }
        finish();
    }

}