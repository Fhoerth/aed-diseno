package aed;

public class AVLTest {
  public static void main(String[] args) {
    ABB<Integer> avlTree = new ABB<Integer>();

    int[] testSizes = { 1000, 10000, 100000, 1000000, 5000000, 10000000 };

    System.out.println("Testing AVL Tree Performance with larger datasets...");

    for (int size : testSizes) {
      System.out.println("Testing with " + size + " nodes:");

      // Insertar
      long startInsert = System.nanoTime();
      for (int i = 0; i < size; i++) {
        avlTree.insertar(size - i);
      }

      System.err.println("Es balanceado " + avlTree.esBalanceado());
      long endInsert = System.nanoTime();
      System.out.println("Insert Time: " + (endInsert - startInsert) / 1e6 + " ms");

      // Búsqueda
      long startSearch = System.nanoTime();
      for (int i = 0; i < size; i++) {
        avlTree.pertenece(size - i);
      }
      long endSearch = System.nanoTime();
      System.out.println("Search Time: " + (endSearch - startSearch) / 1e6 + "ms");

      // Eliminación
      long startDelete = System.nanoTime();
      for (int i = 0; i < size; i++) {
        avlTree.eliminar(size - i);
      }
      long endDelete = System.nanoTime();
      System.out.println("Delete Time: " + (endDelete - startDelete) / 1e6 + "ms");

      System.out.println();
    }
  }
}
