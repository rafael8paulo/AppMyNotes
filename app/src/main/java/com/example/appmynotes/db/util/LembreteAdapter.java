package com.example.appmynotes.db.util;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appmynotes.MainActivity;
import com.example.appmynotes.R;
import com.example.appmynotes.db.bean.Lembrete;

import org.w3c.dom.Text;

import java.util.List;

public class LembreteAdapter extends ArrayAdapter<Lembrete> {

    private int layout;
    public LembreteAdapter(@NonNull Context context, int resource, @NonNull List<Lembrete> objects) {
        super(context, resource, objects);
        layout=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layout, parent, false);

        }
        TextView tvTitulo = (TextView) convertView.findViewById(R.id.tvTitulo);
        TextView tvPrioridade = (TextView) convertView.findViewById(R.id.tvPrioridade);
        TextView tvTexto = (TextView) convertView.findViewById(R.id.tvTexto);
        TextView tvCodigo = (TextView) convertView.findViewById(R.id.tvCodigo);

        Lembrete item = (Lembrete) this.getItem(position);

        tvTitulo.setText(item.getTitulo());
        tvTexto.setText(item.getTexto());
        if(item.getPrioridade().getDescricao().equals("Baixa")){
            convertView.setBackgroundColor(Color.parseColor("#FF7800"));
        }else if(item.getPrioridade().getDescricao().equals("Alta")){
            convertView.setBackgroundColor(Color.parseColor("#FF0000"));
        }else if(item.getPrioridade().getDescricao().equals("Normal")){
            convertView.setBackgroundColor(Color.parseColor("#0000ff"));
        }
        tvPrioridade.setText(item.getPrioridade().getDescricao());
        tvCodigo.setText("#"+item.getId());

        return convertView;
    }

}
