public class ListaFotografo {
    private Fotografo[] fotografoArray;
    private int tamanho;

    public ListaFotografo(int capacidade) {
        fotografoArray = new Fotografo[capacidade];
        tamanho = 0;
    }

    public void add(Fotografo fotografo) {
        if (tamanho == fotografoArray.length) {
            System.out.println("A lista de fotógrafos está cheia");
        } else {
            fotografoArray[tamanho] = fotografo;
            tamanho++;
        }
    }

    public void addNoInicio(Fotografo fotografo) {
        if (tamanho == fotografoArray.length) {
            System.out.println("A lista de fotógrafos está cheia");
        } else {
            for (int i = tamanho; i > 0; i--) {
                fotografoArray[i] = fotografoArray[i - 1];
            }
            fotografoArray[0] = fotografo;
            tamanho++;
        }
    }

    public void ordenarSelectionSort() {
        for (int i = 0; i < tamanho - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < tamanho; j++) {
                if (fotografoArray[j].getId() < fotografoArray[minIndex].getId()) {
                    minIndex = j;
                }
            }
            Fotografo temp = fotografoArray[i];
            fotografoArray[i] = fotografoArray[minIndex];
            fotografoArray[minIndex] = temp;
        }
    }

    public void ordenarBubbleSort() {
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - i - 1; j++) {
                if (fotografoArray[j].getId() > fotografoArray[j + 1].getId()) {
                    Fotografo temp = fotografoArray[j];
                    fotografoArray[j] = fotografoArray[j + 1];
                    fotografoArray[j + 1] = temp;
                }
            }
        }
    }

    public void ordenarInsertionSort() {
        for (int i = 1; i < tamanho; i++) {
            Fotografo key = fotografoArray[i];
            int j = i - 1;
            while (j >= 0 && fotografoArray[j].getId() > key.getId()) {
                fotografoArray[j + 1] = fotografoArray[j];
                j--;
            }
            fotografoArray[j + 1] = key;
        }
    }

    public void deletarPorIndice(int index) {
        if (index < 0 || index >= tamanho) {
            System.out.println("Índice inválido");
        } else {
            for (int i = index; i < tamanho - 1; i++) {
                fotografoArray[i] = fotografoArray[i + 1];
            }
            tamanho--;
        }
    }

    public void deletarPorFotografo(Fotografo fotografo) {
        int index = -1;
        for (int i = 0; i < tamanho; i++) {
            if (fotografoArray[i].equals(fotografo)) {
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
            if (fotografoArray[i].getId() == id) {
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

    public void atualizarFotografo(int id, Fotografo fotografoAtualizado) {
        int index = -1;
        for (int i = 0; i < tamanho; i++) {
            if (fotografoArray[i].getId() == id) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Fotógrafo não encontrado na lista");
        } else {
            fotografoArray[index] = fotografoAtualizado;
        }
    }

    public Fotografo buscarFotografo(int id) {
        for (int i = 0; i < tamanho; i++) {
            if (fotografoArray[i].getId() == id) {
                return fotografoArray[i];
            }
        }
        return null;
    }

    public Fotografo buscarFotografoPesquisaBinaria(int id) {
        ordenarInsertionSort();
        int left = 0;
        int right = tamanho - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (fotografoArray[mid].getId() == id) {
                return fotografoArray[mid];
            } else if (fotografoArray[mid].getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}