package com.example.appmynotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAppMyNotes extends SQLiteOpenHelper {
    private static final int VERSAO = 1;

    public DBAppMyNotes(Context context) {
        super(context, "appmynotes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE prioridade( pri_id INTEGER PRIMARY KEY AUTOINCREMENT, pri_descricao VARCHAR (25));");
        db.execSQL("INSERT INTO prioridade (pri_descricao) VALUES ('Baixa'), ('Normal'), ('Alta');");
        db.execSQL("CREATE TABLE lembrete " +
                "(lem_id  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lem_titulo VARCHAR (40), " +
                "lem_texto VARCHAR (60), " +
                "lem_prioridade INTEGER REFERENCES prioridade (pri_id));");
        db.execSQL("INSERT INTO lembrete (lem_titulo, lem_texto, lem_prioridade) VALUES ('Lembrete Teste', 'Texto do Lembrete teste', 1);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS prioridade");
        db.execSQL("DROP TABLE IF EXISTS lembrete");
        onCreate(db);

    }
}