package com.trabajonube.trabajoenlanubefirebase;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.collection.LruCache;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class UsuarioFragment extends Fragment
{
    TextView correo, telefono;
    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup contenedor, Bundle savedInstanceState)
    {
        View vista = inflador.inflate(R.layout.fragment_usuario, contenedor, false);
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        TextView nombre = (TextView) vista.findViewById(R.id.nombreUsuario);
        correo = (TextView) vista.findViewById(R.id.correoUsuario);
        telefono = (TextView) vista.findViewById(R.id.telefonoUsuario);

        nombre.setText(usuario.getDisplayName());
        correo.setText(usuario.getEmail());
        telefono.setText(usuario.getPhoneNumber());

        RequestQueue colaPeticiones = Volley.newRequestQueue(getActivity() .getApplicationContext());
        ImageLoader lectorImagenes = new ImageLoader(colaPeticiones, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap)
            {
                cache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url)
            {
                return cache.get(url);
            }
        });

        // Foto de usuario
        Uri urlImagen = usuario.getPhotoUrl();
        if (urlImagen != null)
        {
            NetworkImageView fotoUsuario = (NetworkImageView) vista.findViewById(R.id.imagenUsuario);
            fotoUsuario.setImageUrl(urlImagen.toString(), lectorImagenes);
        }



        return vista;
    }
}




