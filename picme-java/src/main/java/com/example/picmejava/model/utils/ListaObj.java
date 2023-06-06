package com.example.picmejava.model.utils;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Identificavel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

public class ListaObj<T> {
    private T[] array;
    private int tamanho;

    public ListaObj() {
        this.array = (T[]) new Object[10];
        this.tamanho = 0;
    }


    public ListaObj(List<T> elementos) {
        this.array = (T[]) elementos.toArray();
    }

    private void redimensionar() {
        int novoTamanho = this.array.length * 2;
        T[] novoArray = Arrays.copyOf(this.array, novoTamanho);
        this.array = novoArray;
    }


    public void add(T elemento) {
        if (tamanho == array.length) {
            redimensionar();
        } else {

            array[tamanho++] = elemento;

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
                } else if (array[j] instanceof Integer && array[minIndex] instanceof Integer) {
                    if ((Integer) array[j] < (Integer) array[minIndex]) {
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
                } else {
                    try {
                        Integer int1 = Integer.parseInt(array[j].toString());
                        Integer int2 = Integer.parseInt(array[j + 1].toString());
                        if (int1 > int2) {
                            T temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;
                        }
                    } catch (NumberFormatException e) {
                        // Se o elemento não for identificável nem inteiro, ignora-o e segue adiante
                        continue;
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
        ordenarBubbleSort();
        int esq = 0;
        int dir = tamanho - 1;
        while (esq <= dir) {
            int meio = (esq + dir) / 2;
            if (isElementOfIdentificavel(array[meio])) {
                Identificavel identificavelMeio = (Identificavel) array[meio];

                if (identificavelMeio.getId().equals(identificavel.getId())) {
                    return (Identificavel) array[meio];
                } else if (identificavelMeio.getId().compareTo(identificavel.getId()) < 0) {
                    esq = meio + 1;
                } else {
                    dir = meio - 1;
                }
            }
        }
        return null;
    }

    public int buscaBinaria(int valor) {
        int esq = 0, dir = tamanho - 1;
        ordenarBubbleSort();
        while (esq <= dir) {
            int meio = (esq + dir) / 2;
            if (array[meio] == null) { // adicione essa verificação para evitar o erro
                continue;
            }
            if ((int) array[meio] == valor) {

                return meio;
            } else if ((int) array[meio] < valor) {

                esq = meio + 1;
            } else {

                dir = meio - 1;
            }

        }
        return -1;
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
        return Arrays.stream(array);
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;

            public boolean hasNext() {
                return cursor < tamanho && array[cursor] != null;
            }

            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T next = array[cursor];
                cursor++;
                return next;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public int size() {
        int count = 0;
        for (int i = 0; i < tamanho; i++) {
            if (array[i] != null) {
                count++;
            }
        }
        return count;
    }

    public T get(int i) {
        return array[i];
    }

    public void deletarPorString(T f1) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(f1)) {
                for (int j = i; j < array.length - 1; j++) {
                    array[j] = array[j + 1];
                }

                array[array.length - 1] = null;
                return;
            }
        }
    }

    public void deletarPorIndex(int index) {
        if (index < 0 || index >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista");
        }
        for (int i = index; i < tamanho - 1; i++) {
            array[i] = array[i + 1];
        }
        tamanho--;
    }

    public boolean isEmpty() {
        return tamanho == 0 ? true : false;
    }

    public void listar() {
        for (int i = 0; i < tamanho; i++) {
            System.out.printf(array[i].toString() + ", ");
        }
    }


}