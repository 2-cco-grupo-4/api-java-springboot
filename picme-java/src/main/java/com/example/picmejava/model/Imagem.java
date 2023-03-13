package com.example.picmejava.model;

import java.util.ArrayList;
import java.util.List;
public class Imagem {
        private String endereco;
        private Album album;
        private List<Tag> tags;

        public Imagem(String endereco) {
            this.endereco = endereco;
            this.tags = new ArrayList<>();
        }

        public String getEndereco() {
            return endereco;
        }

        public Album getAlbum() {
            return album;
        }

        public void setAlbum(Album album) {
            this.album = album;
        }

        public List<Tag> getTags() {
            return tags;
        }

        //Adicionar tag na imagem
        public void adicionarTag(Tag tag) {
            this.tags.add(tag);
        }

        //Metodo não terminado ainda, preciso fazer algumas alterações na classe fotografo
        //antes de terminar esse metodo

        //Conectar imagem ao fotografo
        public String conectar(Fotografo fotografo) {
            return "Imagem conectada ao fotógrafo " + fotografo.getNome();
        }
    }

