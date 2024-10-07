package com.erikat.practica_json.Utils;

import java.net.URL;

public class R {
    public static URL getUI(String name){
        return Thread.currentThread().getContextClassLoader().getResource("ui/"+name); //Devuelve el archivo encontrado en la carpeta ui
    }
}
