package AccesoADatos;

import Modelo.Tipo;

import java.util.ArrayList;
import java.util.HashMap;

public class ColeccionTipo {

   ArrayList<Tipo> listaTipo;

    public ColeccionTipo() {
        this.listaTipo = new ArrayList<>();
    }

    public ArrayList<Tipo> getListaTipos() {
        return new ArrayList<>(listaTipo);
    }

    public boolean AgregarTipo(Tipo tipo){
        if (buscarTipo(tipo.getDescripcion()) != null){
            return false;
        }
        listaTipo.add(tipo);
        return true;
    }

    public Tipo buscarTipo(String nombre){
        for (Tipo tipo : listaTipo) {
            if (tipo.getDescripcion().equals(nombre)){
                return tipo;
            }
        }
        return null;
    }
}
