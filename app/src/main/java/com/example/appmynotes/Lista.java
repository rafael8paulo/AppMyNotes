package com.example.appmynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appmynotes.db.bean.Lembrete;
import com.example.appmynotes.db.dal.LembreteDal;
import com.example.appmynotes.db.util.LembreteAdapter;

import java.util.List;

public class Lista extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        listView=findViewById(R.id.listview);

        LembreteDal lembreteDal= new LembreteDal(this);
        List<Lembrete> lembretes =  lembreteDal.get("", "");

        LembreteAdapter adapter;
        adapter= new LembreteAdapter(this,
                R.layout.item_listview,
                lembretes);
        listView.setAdapter(adapter);

    }
}