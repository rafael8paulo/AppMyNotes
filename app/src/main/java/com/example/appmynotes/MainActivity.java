package com.example.appmynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appmynotes.db.bean.Lembrete;
import com.example.appmynotes.db.bean.Prioridade;
import com.example.appmynotes.db.dal.LembreteDal;
import com.example.appmynotes.db.dal.PrioridadeDal;
import com.example.appmynotes.db.util.LembreteAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private LembreteDal lembreteDal;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lembreteDal = new LembreteDal(this);
        listview = findViewById(R.id.listview);
        listview.setAdapter(carregaLembretes(""));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChamaTelaDeConsulta(parent, position);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                aDApagarNotificação(i, adapterView);
                return false;
            }
        });

    }

    public void ChamaTelaDeConsulta(AdapterView parent, int position){
        String codigo = String.valueOf(((Lembrete) parent.getItemAtPosition(position)).getId());
        String titulo = ((Lembrete) parent.getItemAtPosition(position)).getTitulo();
        String texto = ((Lembrete) parent.getItemAtPosition(position)).getTexto();
        String prioridade = ((Lembrete) parent.getItemAtPosition(position)).getPrioridade().getDescricao();

        Intent intent = new Intent(this, TelaConsultaActivity.class);
        intent.putExtra("codigo", codigo);
        intent.putExtra("titulo", titulo);
        intent.putExtra("texto", texto);
        intent.putExtra("prioridade", prioridade);
        startActivity(intent);
    }

    private void aDApagarNotificação(int id, AdapterView adapterView) //Cria o gerador do AlertDialog
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Deseja excluir o lembrete?"); //define o titulo
        builder.setMessage("Deseja excluir o lembrete?"); //define a mensagem
        //define um botão como positivo
        builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                int codigo = ((Lembrete) adapterView.getItemAtPosition(id)).getId();
                if (lembreteDal.apagar(codigo)) {
                    Toast.makeText(getApplicationContext(), "Lembrete excluido com sucesso!", Toast.LENGTH_SHORT).show();
                    listview.setAdapter(carregaLembretes(""));
                } else {
                    Toast.makeText(getApplicationContext(), "Falha ao excluir lembrete.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getApplicationContext(), "Operação cancelada.", Toast.LENGTH_SHORT).show();
            }
        });
        alerta = builder.create(); //cria o AlertDialog
        alerta.show(); //Exibe
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itFechar:
                finish();
                break;
            case R.id.itNvAnotação:
                Intent intent = new Intent(this, NovaAnotacao.class);
                startActivity(intent);
                break;
            case R.id.itOrOrdem:
                listview.setAdapter(carregaLembretes("l.lem_titulo ASC"));
                break;
            case R.id.itOrPrioridade:
                listview.setAdapter(carregaLembretes("p.pri_id ASC"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        listview.setAdapter(carregaLembretes(""));
    }

    public LembreteAdapter carregaLembretes(String order) {
        List<Lembrete> lembretes = lembreteDal.get("", order);
        LembreteAdapter adapter;
        adapter = new LembreteAdapter(this,
                R.layout.item_listview,
                lembretes);
        return adapter;
    }


}