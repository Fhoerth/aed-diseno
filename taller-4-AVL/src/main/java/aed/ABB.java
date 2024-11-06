package aed;

public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo raiz;
    private int cardinal;
    private Comparativa comparativa = new Comparativa();

    private class Comparativa {
        private boolean esMayor(T elem1, T elem2) {
            return elem1.compareTo(elem2) > 0;
        }

        private boolean esMenor(T elem1, T elem2) {
            return elem1.compareTo(elem2) < 0;
        }

        private boolean esIgual(T elem1, T elem2) {
            return elem1.compareTo(elem2) == 0;
        }
    }

    private class Nodo {
        private int altura;

        private Nodo izquierda;
        private Nodo derecha;
        private T elem;

        public Nodo(T elem) {
            this.elem = elem;
            this.altura = 1;
        }

        private boolean tieneDosHijos() {
            return izquierda != null && derecha != null;
        }

        private boolean tieneHijoIzquierda() {
            return izquierda != null;
        }

        private boolean tieneHijoDerecha() {
            return derecha != null;
        }
    }

    public int cardinal() {
        return cardinal;
    }

    /**
     * Mínimo
     */
    public T minimoSubArbol(Nodo raiz) {
        if (raiz.tieneHijoIzquierda()) {
            return minimoSubArbol(raiz.izquierda);
        }

        return raiz.elem;
    }

    public T minimo() {
        return minimoSubArbol(raiz);
    }

    /**
     * Máximo
     */
    public T maximoSubArbol(Nodo raiz) {
        if (raiz.tieneHijoDerecha()) {
            return maximoSubArbol(raiz.derecha);
        }

        return raiz.elem;
    }

    public T maximo() {
        return maximoSubArbol(raiz);
    }

    /**
     * Pertenece
     */
    private boolean perteneceSubArbol(Nodo raiz, T elem) {
        if (raiz == null) {
            return false;
        }

        if (comparativa.esIgual(raiz.elem, elem)) {
            return true;
        } else if (comparativa.esMenor(raiz.elem, elem)) {
            return perteneceSubArbol(raiz.derecha, elem);
        } else {
            return perteneceSubArbol(raiz.izquierda, elem);
        }
    }

    public boolean pertenece(T elem) {
        return perteneceSubArbol(raiz, elem);
    }

    /**
     * Balanceo de árbol
     */
    private int altura(Nodo nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    private int recalcularAltura(Nodo raiz) {
        return 1 + Math.max(altura(raiz.izquierda), altura(raiz.derecha));
    }

    private int factorDeBalanceo(Nodo raiz) {
        return altura(raiz.derecha) - altura(raiz.izquierda);
    }

    private Nodo rotacionIzquierda(Nodo x) {
        Nodo y = x.derecha;
        x.derecha = y.izquierda;
        y.izquierda = x;

        x.altura = recalcularAltura(x);
        y.altura = recalcularAltura(y);

        return y;
    }

    private Nodo rotacionDerecha(Nodo y) {
        Nodo x = y.izquierda;
        y.izquierda = x.derecha;
        x.derecha = y;

        y.altura = recalcularAltura(y);
        x.altura = recalcularAltura(x);

        return x;
    }

    private Nodo balancear(Nodo raiz) {
        if (raiz == null) {
            return null;
        }

        int fb = factorDeBalanceo(raiz);

        if (fb < -1 && factorDeBalanceo(raiz.izquierda) <= 0) {
            raiz = rotacionDerecha(raiz);
        } else if (fb > 1 && factorDeBalanceo(raiz.derecha) >= 0) {
            raiz = rotacionIzquierda(raiz);
        } else if (fb < -1 && factorDeBalanceo(raiz.izquierda) > 0) {
            raiz.izquierda = rotacionIzquierda(raiz.izquierda);
            raiz = rotacionDerecha(raiz);
        } else if (fb > 1 && factorDeBalanceo(raiz.derecha) < 0) {
            raiz.derecha = rotacionDerecha(raiz.derecha);
            raiz = rotacionIzquierda(raiz);
        }

        raiz.altura = recalcularAltura(raiz);

        return raiz;
    }

    /**
     * Insertar
     */
    private Nodo insertarEnSubArbol(Nodo raiz, T elem) {
        if (raiz == null) {
            cardinal += 1;
            return new Nodo(elem);
        }

        if (comparativa.esMenor(raiz.elem, elem)) {
            raiz.derecha = insertarEnSubArbol(raiz.derecha, elem);
        } else if (comparativa.esMayor(raiz.elem, elem)) {
            raiz.izquierda = insertarEnSubArbol(raiz.izquierda, elem);
        }

        raiz.altura = recalcularAltura(raiz);

        return balancear(raiz);
    }

    public void insertar(T elem) {
        raiz = insertarEnSubArbol(raiz, elem);
    }

    /**
     * Eliminar
     */
    public Nodo eliminarDelSubArbol(Nodo raiz, T elem) {
        if (raiz == null) {
            return null;
        }

        if (comparativa.esIgual(raiz.elem, elem)) {
            if (raiz.tieneDosHijos()) {
                raiz.elem = maximoSubArbol(raiz.izquierda);
                raiz.izquierda = eliminarDelSubArbol(raiz.izquierda, raiz.elem);

                return raiz;
            } else {
                cardinal -= 1;

                return raiz.izquierda != null ? raiz.izquierda : raiz.derecha;
            }
        } else if (comparativa.esMenor(raiz.elem, elem)) {
            raiz.derecha = eliminarDelSubArbol(raiz.derecha, elem);
        } else {
            raiz.izquierda = eliminarDelSubArbol(raiz.izquierda, elem);
        }

        raiz.altura = recalcularAltura(raiz);

        return balancear(raiz);
    }

    public void eliminar(T elem) {
        raiz = eliminarDelSubArbol(raiz, elem);
    }

    public String toString() {
        Iterador<T> iterador = iterador();
        String template = "{";

        while (iterador.haySiguiente()) {
            template += iterador.siguiente().toString();

            if (iterador.haySiguiente()) {
                template += ",";
            }
        }

        template += "}";

        return template;
    }

    private class ABB_Iterador implements Iterador<T> {
        public class Cola {
            private class NodoCola {
                private T elem;
                private NodoCola siguiente;
            }

            NodoCola primero;
            NodoCola ultimo;

            public boolean estaVacia() {
                return primero == null && ultimo == null;
            }

            public void encolar(T elem) {
                NodoCola nodo = new NodoCola();
                nodo.elem = elem;

                if (estaVacia()) {
                    primero = ultimo = nodo;
                } else {
                    ultimo.siguiente = ultimo = nodo;
                }
            }

            public T desencolar() {
                T elem = primero.elem;

                primero = primero.siguiente;

                if (primero == null) {
                    ultimo = null;
                }

                return elem;
            }
        }

        private Cola cola;

        public ABB_Iterador() {
            this.cola = new Cola();
            inOrder(raiz);
        }

        private void inOrder(Nodo raiz) {
            if (raiz == null) {
                return;
            }

            inOrder(raiz.izquierda);
            cola.encolar(raiz.elem);
            inOrder(raiz.derecha);
        }

        public boolean haySiguiente() {
            return !cola.estaVacia();
        }

        public T siguiente() {
            return cola.desencolar();
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

    /**
     * Testing
     */
    private boolean esSubArbolBalanceado(Nodo nodo) {
        if (nodo == null)
            return true;

        int fb = factorDeBalanceo(nodo);

        if (fb < -1 || fb > 1) {
            System.out.println("Nodo desbalanceado: " + nodo.elem + " con FB: " + fb);
            return false;
        }

        return esSubArbolBalanceado(nodo.izquierda) && esSubArbolBalanceado(nodo.derecha);
    }

    public boolean esBalanceado() {
        return esSubArbolBalanceado(raiz);
    }

}
