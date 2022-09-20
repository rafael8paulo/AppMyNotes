package com.example.appmynotes.db.dal;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;
import android.content.Context;
import com.example.appmynotes.db.bean.Prioridade;
import com.example.appmynotes.db.util.Conexao;

import java.util.ArrayList;

public class PrioridadeDal {
    private String TABLE = "prioridade";
    private Conexao con;

    public PrioridadeDal(Context context) {
        con = new Conexao(context);
        try {
            con.conectar();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<Prioridade> get(String filtro) {
        ArrayList<Prioridade> objs = new ArrayList();
        String sql = "select * from " + TABLE;
        if (!filtro.equals(""))
            sql += " where " + filtro;

        Cursor cursor = con.consultar(sql);
        if (cursor.moveToFirst())
            while (!cursor.isAfterLast()) {
                objs.add(new Prioridade(cursor.getInt(0), cursor.getString(1)));
                cursor.moveToNext();
            }
        cursor.close();
        ;
        return objs;
    }

}

