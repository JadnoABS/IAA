#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int filhoEsquerda(int elem) {
  return (elem+1)*2 - 1;
}

int filhoDireita(int elem) {
  return (elem+1)*2;
};

int pai(int elem) {
  return (elem+1)/2;
}


void maxHeapify(int* heap, int tamanho, int elem) {
  int esquerda = filhoEsquerda(elem); 
  int direita = filhoDireita(elem); 
  int maior = elem;

  if(esquerda < tamanho && heap[esquerda] > heap[elem]){
    maior = esquerda;
  }
  if(direita < tamanho && heap[direita] > heap[maior]){
    maior = direita;
  }

  if(maior != elem){
    int aux = heap[elem];
    heap[elem] = heap[maior];
    heap[maior] = aux;
    maxHeapify(heap, tamanho, maior);
  }

  return;
}

void buildMaxHeap(int* heap, int tamanho) {
  for(int i = pai(tamanho-1); i >= 0; i--) {
    maxHeapify(heap, tamanho, i);
  }
}


void heapsort(int* heap, int tamanho) {
  buildMaxHeap(heap, tamanho);
  for(int i = tamanho-1; i >= 1; i--) {
    int aux = heap[0];
    heap[0] = heap[i];
    heap[i] = aux;
    maxHeapify(heap, i, 0);
  }
  return;
}



int main() {

  int* heap = (int*) malloc(sizeof(int) * 15);
  
  time_t t;
  srand((unsigned int) time(&t));


  for(int i = 0; i < 15; i++) {
    heap[i] = rand() % 100;
    printf("%d ", heap[i]);
  }
  printf("\n");

  heapsort(heap, 15);

  for(int i = 0; i < 15; i++) {
    printf("%d ", heap[i]);
  }
  printf("\n");

  return 0;
}
