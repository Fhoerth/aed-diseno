package aed;

interface Conjunto<T> {

    /**
     * Devuelve la cantidad de elementos del conjunto.
     * 
     */
    public int cardinal();

    /**
     * Devuelve el menor elemento del conjunto
     *
     */
    public T minimo();

    /**
     * Devuelve el mayor elemento del conjunto
     *
     */
    public T maximo();

    /**
     * Agrega un elemento al conjunto
     * 
     */
    public void insertar(T elem);

    /**
     * Devuelve verdadero si el elemento pertenece al conjunto
     * 
     */
    public boolean pertenece(T elem);

    /**
     * Elimina el elemento del donjunto
     * 
     */
    public void eliminar(T elem);

    /**
     * Imprime el conjunto
     *
     */
    public String toString();

    /**
     * Retorna un conjunto con los mismos elementos
     * 
     */
    // public ABB<T> copiar();

}
