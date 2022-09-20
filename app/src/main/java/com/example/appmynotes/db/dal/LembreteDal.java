package com.example.appmynotes.db.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.appmynotes.NovaAnotacao;
import com.example.appmynotes.db.bean.Lembrete;
import com.example.appmynotes.db.bean.Prioridade;
import com.example.appmynotes.db.util.Conexao;

import java.util.ArrayList;

public class LembreteDal {
    private Conexao con;
    private final String TABLE = "lembrete";

    public LembreteDal(Context context) {
        con = new Conexao(context);
        try {
            con.conectar();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean salvar(Lembrete l) {
        ContentValues dados = new ContentValues();
        dados.put("lem_titulo", l.getTitulo());
        dados.put("lem_texto", l.getTexto());
        dados.put("lem_prioridade", l.getPrioridade().getId());

        return con.inserir(TABLE, dados) > 0;
    }

    public boolean alterar(Lembrete l) {
        ContentValues dados = new ContentValues();
        dados.put("lem_id", l.getId());
        dados.put("lem_titulo", l.getTitulo());
        dados.put("lem_texto", l.getTexto());
        dados.put("lem_prioridade", l.getPrioridade().getDescricao());
        return con.alterar(TABLE, dados, "lem_id=" + l.getId()) > 0;
    }

    public boolean apagar(long chave) {
        return con.apagar(TABLE, "lem_id=" + chave) > 0;
    }

    public Lembrete get(int id) {
        Lembrete o = null;
        Cursor cursor = con.consultar("select * from " + TABLE + " where gen_id=" + id);
        if (cursor.moveToFirst())
            o = new Lembrete(cursor.getInt(0), cursor.getString(1), cursor.getString(3), new Prioridade(cursor.getInt(4), ""));
        cursor.close();
        ;
        return o;
    }

    public ArrayList<Lembrete> get(String filtro, String order) {
        ArrayList<Lembrete> objs = new ArrayList();
        String sql = "select * from lembrete l INNER JOIN prioridade p ON  p.pri_id = l.lem_prioridade";
        if (!filtro.equals(""))
            sql += " where " + filtro;
        if (!order.equals(""))
            sql += " order by " + order;

        System.out.println(sql);

        Cursor cursor = con.consultar(sql);
        if (cursor.moveToFirst())
            while (!cursor.isAfterLast()) {
                objs.add(
                        new Lembrete(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                new Prioridade(cursor.getLong(4) ,cursor.getString(5))
                         ));
                cursor.moveToNext();
            }
        cursor.close();

        return objs;
    }

}
