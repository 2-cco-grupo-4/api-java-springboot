package com.example.picmejava.service.utils;

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

    public List<PerfilClienteDTO> searchByString(String name) {
        char firstChar = Character.toLowerCase(name.charAt(0));
        int index = firstChar - 'a';
        List<PerfilClienteDTO> clients = new LinkedList<>();

        System.out.println("Index: " + index);

        if (index >= 0 && index < NUM_OF_INDICES) {
            LinkedList<PerfilClienteDTO> list = hashtable.get(index);
            System.out.println("List: " + list.toString());

            for (PerfilClienteDTO cliente : list) {
                if (cliente.getNome().toLowerCase().contains(name.toLowerCase())) {
                    clients.add(cliente);
                }
            }

            return clients;
        } else {
            return new LinkedList<>();
        }
    }


}