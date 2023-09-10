package com.example.picmejava.service.utils;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.service.usuario.dto.PerfilClienteDTO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TabelaHash<V> {
    private final static int NUM_OF_INDICES = 26;
    private List<LinkedList<PerfilClienteDTO>> hashtable;

    public TabelaHash() {
        hashtable = new ArrayList<>(NUM_OF_INDICES);
        for (int i = 0; i < NUM_OF_INDICES; i++) {
            hashtable.add(new LinkedList<PerfilClienteDTO>());
        }
    }

    private String getNameFromValue(V value) {
        if (value instanceof String) {
            return (String) value;
        } else {
            throw new IllegalArgumentException("Invalid value type: " + value.getClass().getSimpleName());
        }
    }

    public void add( PerfilClienteDTO cliente) {
        String name = cliente.getNome();
        char firstChar = Character.toLowerCase(name.charAt(0));
        int index = firstChar - 'a';

        if (index >= 0 && index < NUM_OF_INDICES) {
            LinkedList<PerfilClienteDTO> list = hashtable.get(index);
            list.add(cliente);
        } else {
            throw new IllegalArgumentException("Invalid character: " + firstChar);
        }
    }

    public List<PerfilClienteDTO> searchByFirstChar(char firstChar) {
        int index = Character.toLowerCase(firstChar) - 'a';

        if (index >= 0 && index < NUM_OF_INDICES) {
            LinkedList<PerfilClienteDTO> list = hashtable.get(index);
            return new LinkedList<PerfilClienteDTO>(list);
        } else {
            return new LinkedList<>();
        }
    }

}