package com.trabajonube.trabajoenlanubefirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorArticulos extends RecyclerView.Adapter<AdaptadorArticulos.ViewHolder> {
    private LayoutInflater inflador; //Crea Layouts a partir del XML protecte
    private List<String> lista;
    private Context contexto;


    public AdaptadorArticulos(Context contexto, List<String> listaLibros) {
        inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lista = listaLibros;
        this.contexto = contexto;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, detalle;
        public TextView costo;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            detalle = (TextView) itemView.findViewById(R.id.detalle);
            costo = (TextView) itemView.findViewById(R.id.costo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.item_base_datos, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {

        String[] items = lista.get(i).split(",");
        holder.nombre.setText(items[0]);
        holder.detalle.setText(items[1]);
        holder.costo.setText(items[2]);
    }


    @Override
    public int getItemCount() {
        return lista.size();

    }
}