package com.example.picmejava.lista;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Identificavel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Lista<T> {
    private T[] array;
    private int tamanho;

    public Lista(int capacidade) {
        this.array = (T[]) new Object[capacidade];
        this.tamanho = 0;
    }

    public Lista() {
        this(10); // construtor padrão com capacidade inicial de 10
    }

    public void add(T fotografo) {
        if (tamanho == array.length) {
            System.out.println("A lista de fotógrafos está cheia");
        } else {
            array[tamanho] = fotografo;
            tamanho++;
        }
    }

    private void aumentaCapacidade() {
        T[] novosElementos = (T[]) new Object[this.array.length * 2];
        System.arraycopy(this.array, 0, novosElementos, 0, this.array.length);
        this.array = novosElementos;
    }

    public void addFirst(T elem) {
        if (tamanho == array.length) {
            System.out.println("A lista está cheia");
        } else {
            for (int i = tamanho; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = elem;
            tamanho++;
        }
    }

    public void set(int indice, T elemento) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista");
        }
        array[indice] = elemento;
    }


    public void ordenarSelectionSort() {
        if (array == null) {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista");
        }
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {

                if (isElementOfIdentificavel(array[j]) && isElementOfIdentificavel(array[minIndex])) {
                    Identificavel arrJ = (Identificavel) array[j];
                    Identificavel arrMinIndex = (Identificavel) array[minIndex];
                    if (arrJ.getId() < arrMinIndex.getId()) {
                        minIndex = j;
                    }
                }
            }
            if (minIndex != i) {
                T temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }


    public void ordenarBubbleSort() {
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - i - 1; j++) {
                if (isElementOfIdentificavel(array[j]) && isElementOfIdentificavel(array[j + 1])) {
                    Identificavel identificavel1 = (Identificavel) array[j];
                    Identificavel identificavel2 = (Identificavel) array[j + 1];
                    if (identificavel1.getId() > identificavel2.getId()) {
                        T temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }
    }

    public boolean isElementOfIdentificavel(Object element) {
        return element instanceof Identificavel;
    }

    public void deletarPorIndice(int index) {
        if (index < 0 || index >= tamanho) {
            System.out.println("Índice inválido");
        } else {
            for (int i = index; i < tamanho - 1; i++) {
                array[i] = array[i + 1];
            }
            tamanho--;
        }
    }

    public void deletarPorFotografo(Fotografo fotografo) {
        int index = -1;
        for (int i = 0; i < tamanho; i++) {
            if (array[i].equals(fotografo)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Fotógrafo não encontrado na lista");
        } else {
            deletarPorIndice(index);
        }
    }

    public void deletarPorId(int id) {
        int index = -1;
        for (int i = 0; i < tamanho; i++) {
            if (isElementOfIdentificavel(array[i])) {
                Identificavel identificavel = (Identificavel) array[i];

                if (identificavel.getId() == id) {
                    index = i;
                    break;
                }
            }
        }
        if (index == -1) {
            System.out.println("Fotógrafo não encontrado na lista");
        } else {
            deletarPorIndice(index);
        }
    }

    public void atualizarFotografo(int id, Fotografo fotografoAtualizado) {
        int index = -1;
        for (int i = 0; i < tamanho; i++) {
            if (isElementOfIdentificavel(array[i])) {
                Identificavel identificavel1 = (Identificavel) array[i];
                if (identificavel1.getId() == id) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                System.out.println("Fotógrafo não encontrado na lista");
            } else {
                array[index] = (T) fotografoAtualizado;
            }
        }
    }

    public Fotografo buscarFotografo(int id) {
        for (int i = 0; i < tamanho; i++) {
            if (isElementOfIdentificavel(array[i])) {
                Identificavel identificavel1 = (Identificavel) array[i];
                if (identificavel1.getId() == id) {
                    return (Fotografo) array[i];
                }
            }
            return null;
        }
        return null;
    }

    public Identificavel buscarPesquisaBinaria(Identificavel identificavel) {
        ordenarSelectionSort();
        int left = 0;
        int right = tamanho - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(isElementOfIdentificavel(array[mid])) {
                Identificavel identificavelMeio = (Identificavel) array[mid];

                if (identificavelMeio.getId().equals(identificavel.getId())) {
                    return (Identificavel) array[mid];
                } else if (identificavelMeio.getId().compareTo(identificavel.getId()) < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return null;
    }

    private boolean temId(Object obj) {
        try {
            Method getIdMethod = obj.getClass().getMethod("getId");
            Field idField = obj.getClass().getDeclaredField("id");
            return getIdMethod != null && idField != null;
        } catch (NoSuchMethodException | NoSuchFieldException e) {
            return false;
        }
    }


    public List<T> toList() {
        List<T> arrayList = new ArrayList<>(tamanho);
        arrayList.addAll(Arrays.asList(array).subList(0, tamanho));
        return arrayList;
    }


    public Stream<T> stream() {
        return (Stream<T>) Arrays.stream(array).toList();
    }
}