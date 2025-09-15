package Datos;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class AdmiSerializable {


    private static final XmlMapper xmlMapper = new XmlMapper();

    // Guardar cualquier objeto
    public static <T> void saveToXml(T obj, File file) throws IOException {
        xmlMapper.writeValue(file, obj);
    }

    public static <T> List<T> loadListFromXml(File file, Class<T> elementType, T obj) throws IOException {
        try {
            return xmlMapper.readValue(file,
                    xmlMapper.getTypeFactory().constructCollectionType(List.class, elementType));
        } catch (IOException e) {
            // Crear una lista vacía y guardarla
            List<T> listaVacia = new ArrayList<>();
            saveToXml(listaVacia, file);
            return listaVacia;
        }
    }


}