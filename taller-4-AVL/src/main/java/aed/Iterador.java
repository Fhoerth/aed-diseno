package aed;

interface Iterador<T> {
    
    /**
     * Devuelve true si hay un elemento siguiente en la colección.
     * 
     */
    public boolean haySiguiente();

    /**
     * Devuelve el elemento siguiente en la colección y avanza el iterador.
     * 
     */
    public T siguiente();

}
