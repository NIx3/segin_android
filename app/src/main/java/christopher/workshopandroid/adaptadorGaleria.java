package christopher.workshopandroid;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Christopher on 24-06-2017.
 */

public class adaptadorGaleria extends  RecyclerView.Adapter<adaptadorGaleria.MyViewHolder>{

    List<imagenGaleria> ListaGaleria;
    Context context;

    public adaptadorGaleria(Context context, List<imagenGaleria> data){

        this.context=context;
        this.ListaGaleria=data;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView card_titulo, card_detalle;
        public ImageView card_imagen;
        public MyViewHolder (View itemView){
            super(itemView);

            card_titulo = (TextView) itemView.findViewById(R.id.card_titulo);
            card_detalle = (TextView) itemView.findViewById(R.id.card_detalle);
            card_imagen = (ImageView) itemView.findViewById(R.id.card_imagen);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("palabra clave", card_titulo.getText().toString());
                    Intent i = new Intent(view.getContext(), detalleGaleria.class);
                    i.putExtras(bundle);
                    view.getContext().startActivity(i);
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){

        View itemView = LayoutInflater.from(context).inflate(R.layout.cardview_galeria,parent,false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(adaptadorGaleria.MyViewHolder holder, int position) {

        imagenGaleria imagenGaleria = ListaGaleria.get(position);
        holder.card_titulo.setText(imagenGaleria.getTitulo());
        holder.card_detalle.setText(imagenGaleria.getDetalle());
        Glide.with(context).load(imagenGaleria.getImagen()).into(holder.card_imagen);

    }

    @Override
    public int getItemCount() {
        return ListaGaleria.size();
    }
}
