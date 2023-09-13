package com.example.picmejava.service.utils;

import com.example.picmejava.service.usuario.dto.PerfilFotografoDTO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TabelaHash<V> {
    private final static int NUM_OF_INDICES = 26;
    private List<LinkedList<PerfilFotografoDTO>> hashtable;

    public TabelaHash() {
        hashtable = new ArrayList<>(NUM_OF_INDICES);
        for (int i = 0; i < NUM_OF_INDICES; i++) {
            hashtable.add(new LinkedList<PerfilFotografoDTO>());
        }
    }

    private String getNameFromValue(V value) {
        if (value instanceof String) {
            return (String) value;
        } else {
            throw new IllegalArgumentException("Invalid value type: " + value.getClass().getSimpleName());
        }
    }

    public void add( PerfilFotografoDTO cliente) {
        String name = cliente.getNome();
        char firstChar = Character.toLowerCase(name.charAt(0));
        int index = firstChar - 'a';

        if (index >= 0 && index < NUM_OF_INDICES) {
            LinkedList<PerfilFotografoDTO> list = hashtable.get(index);
            list.add(cliente);
        } else {
            throw new IllegalArgumentException("Invalid character: " + firstChar);
        }
    }

    public List<PerfilFotografoDTO> searchByString(String name) {
        char firstChar = Character.toLowerCase(name.charAt(0));
        int index = firstChar - 'a';
        List<PerfilFotografoDTO> clients = new LinkedList<>();

        System.out.println("Index: " + index);

        if (index >= 0 && index < NUM_OF_INDICES) {
            LinkedList<PerfilFotografoDTO> list = hashtable.get(index);
            System.out.println("List: " + list.toString());

            for (PerfilFotografoDTO cliente : list) {
                if (cliente.getNome().toLowerCase().contains(name.toLowerCase())) {
                    clients.add(cliente);
                }
            }

            return clients;
        } else {
            return new LinkedList<>();
        }
    }


    public boolean isEmpty() {
        for (LinkedList<PerfilFotografoDTO> list : hashtable) {
            if (!list.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}