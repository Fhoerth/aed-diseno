package aed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ABBTests {

    @Test
    void nuevo_conjunto_vacio() {
        ABB<Integer> conjunto = new ABB<Integer>();

        assertFalse(conjunto.pertenece(42));
        assertEquals(0, conjunto.cardinal());
    }

    @Test
    void insertar_un_elemento() {
        ABB<Integer> conjunto = new ABB<Integer>();

        conjunto.insertar(42);

        assertEquals(1, conjunto.cardinal());
        assertTrue(conjunto.pertenece(42));
    }

    @Test
    void insertar_izquierda() {
        ABB<Integer> conjunto = new ABB<Integer>();

        conjunto.insertar(43);
        conjunto.insertar(42);
        conjunto.insertar(41);

        assertTrue(conjunto.pertenece(43));
        assertTrue(conjunto.pertenece(42));
        assertTrue(conjunto.pertenece(41));
        assertEquals(3, conjunto.cardinal());
    }

    @Test
    void insertar_derecha() {
        ABB<Integer> conjunto = new ABB<Integer>();

        conjunto.insertar(43);
        conjunto.insertar(44);
        conjunto.insertar(45);

        assertTrue(conjunto.pertenece(43));
        assertTrue(conjunto.pertenece(44));
        assertTrue(conjunto.pertenece(45));
        assertEquals(3, conjunto.cardinal());
    }

    @Test
    void insertar_elemento_repetido() {
        ABB<Integer> conjunto = new ABB<Integer>();

        /*
         * ___2___
         * _1 _ 5_
         * . . 3 7
         * 
         */

        conjunto.insertar(2);
        conjunto.insertar(1);
        conjunto.insertar(5);
        conjunto.insertar(3);
        conjunto.insertar(7);
        conjunto.insertar(3);

        assertEquals(5, conjunto.cardinal());

        assertTrue(conjunto.pertenece(2));
        assertTrue(conjunto.pertenece(1));
        assertTrue(conjunto.pertenece(5));
        assertTrue(conjunto.pertenece(3));
        assertTrue(conjunto.pertenece(7));

        assertFalse(conjunto.pertenece(4));
    }

    @Test
    void insertar_cinco_nombres() {
        ABB<String> conjunto = new ABB<String>();

        // Todos los tipos de datos "Comparables" tienen el método compareTo()
        assertTrue("Jujuy".compareTo("La Pampa") < 0);
        assertTrue("Jujuy".compareTo("Chubut") > 0);

        conjunto.insertar("La Pampa");
        assertEquals(1, conjunto.cardinal());
        assertEquals("La Pampa", conjunto.minimo());
        assertEquals("La Pampa", conjunto.maximo());

        conjunto.insertar("Chubut");
        assertEquals(2, conjunto.cardinal());
        assertEquals("Chubut", conjunto.minimo());
        assertEquals("La Pampa", conjunto.maximo());

        conjunto.insertar("Formosa");
        assertEquals(3, conjunto.cardinal());
        assertEquals("Chubut", conjunto.minimo());
        assertEquals("La Pampa", conjunto.maximo());

        conjunto.insertar("Catamarca");
        assertEquals(4, conjunto.cardinal());
        assertEquals("Catamarca", conjunto.minimo());
        assertEquals("La Pampa", conjunto.maximo());

        conjunto.insertar("Jujuy");
        assertEquals(5, conjunto.cardinal());
        assertEquals("Catamarca", conjunto.minimo());
        assertEquals("La Pampa", conjunto.maximo());

        assertTrue(conjunto.pertenece("Catamarca"));
        assertFalse(conjunto.pertenece("Buenos Aires"));
        assertTrue(conjunto.pertenece("Jujuy"));
    }

    @Test
    void eliminar_elemento_con_un_descendiente() {
        ABB<Integer> conjunto = new ABB<Integer>();

        conjunto.insertar(5);
        conjunto.insertar(6);
        conjunto.insertar(7);
        conjunto.eliminar(6);

        assertFalse(conjunto.pertenece(6));
        assertEquals(2, conjunto.cardinal());
        assertEquals(5, conjunto.minimo());
        assertEquals(7, conjunto.maximo());
    }

    @Test
    void eliminar_elemento_con_dos_descendientes() {
        ABB<Integer> conjunto = new ABB<Integer>();

        conjunto.insertar(5);
        conjunto.insertar(4);
        conjunto.insertar(7);
        conjunto.insertar(6);
        conjunto.insertar(8);
        conjunto.eliminar(7);

        assertFalse(conjunto.pertenece(7));
        assertEquals(4, conjunto.cardinal());
        assertEquals(4, conjunto.minimo());
        assertEquals(8, conjunto.maximo());
    }

    @Test
    void eliminar_raiz_cons_dos_hijos() {
        ABB<Integer> conjunto = new ABB<Integer>();

        conjunto.insertar(5);
        conjunto.insertar(4);
        conjunto.insertar(7);
        conjunto.insertar(6);
        conjunto.insertar(8);
        conjunto.eliminar(5);

        assertFalse(conjunto.pertenece(5));
        assertEquals(4, conjunto.cardinal());
        assertEquals(4, conjunto.minimo());
        assertEquals(8, conjunto.maximo());
    }

    @Test
    void eliminar_raiz_con_derecho() {
        ABB<Integer> conjunto = new ABB<Integer>();

        /*
         * ___6___
         * _____8
         * ____7 9
         * 
         */

        conjunto.insertar(6);
        conjunto.insertar(8);
        conjunto.insertar(9);
        conjunto.insertar(7);

        conjunto.eliminar(6);

        assertFalse(conjunto.pertenece(6));
        assertTrue(conjunto.pertenece(8));
        assertTrue(conjunto.pertenece(7));
        assertTrue(conjunto.pertenece(9));

        assertEquals(3, conjunto.cardinal());
        assertEquals(7, conjunto.minimo());
        assertEquals(9, conjunto.maximo());
    }

    @Test
    void eliminar_raiz_con_hijo_izquierdo() {
        ABB<Integer> conjunto = new ABB<Integer>();

        /*
         * ___6___
         * _4
         * 2 5
         * 
         */

        conjunto.insertar(6);
        conjunto.insertar(4);
        conjunto.insertar(2);
        conjunto.insertar(5);

        conjunto.eliminar(6);

        assertFalse(conjunto.pertenece(6));
        assertTrue(conjunto.pertenece(4));
        assertTrue(conjunto.pertenece(2));
        assertTrue(conjunto.pertenece(5));

        assertEquals(3, conjunto.cardinal());
        assertEquals(2, conjunto.minimo());
        assertEquals(5, conjunto.maximo());
    }

    @Test
    void eliminar_elemento_con_doble_descendencia() {
        ABB<Integer> conjunto = new ABB<Integer>();

        conjunto.insertar(5);
        conjunto.insertar(4);
        conjunto.insertar(20);
        conjunto.insertar(15);
        conjunto.insertar(12);
        conjunto.insertar(16);
        conjunto.insertar(24);
        conjunto.insertar(22);
        conjunto.insertar(25);
        conjunto.eliminar(20);

        assertFalse(conjunto.pertenece(20));
        assertEquals(8, conjunto.cardinal());
        assertEquals(4, conjunto.minimo());
        assertEquals(25, conjunto.maximo());
    }

    @Test
    void eliminar_elemento_con_sucesor_arriba() {
        ABB<Integer> conjunto = new ABB<Integer>();

        conjunto.insertar(5);
        conjunto.insertar(4);
        conjunto.insertar(20);
        conjunto.insertar(15);
        conjunto.insertar(12);
        conjunto.insertar(24);
        conjunto.insertar(22);
        conjunto.insertar(25);
        conjunto.insertar(19);
        conjunto.insertar(21);
        conjunto.eliminar(20);

        assertFalse(conjunto.pertenece(20));
        assertEquals(9, conjunto.cardinal());
        assertEquals(4, conjunto.minimo());
        assertEquals(25, conjunto.maximo());
    }

    @Test
    void siguiente_inorder() {
        ABB<Integer> conjunto = new ABB<Integer>();

        conjunto.insertar(5);
        conjunto.insertar(4);
        conjunto.insertar(20);
        conjunto.insertar(15);
        conjunto.insertar(12);
        conjunto.insertar(16);
        conjunto.insertar(24);
        conjunto.insertar(22);
        conjunto.insertar(25);

        Iterador<Integer> iterador = conjunto.iterador();
        assertEquals(4, iterador.siguiente());
        assertEquals(5, iterador.siguiente());
        assertEquals(12, iterador.siguiente());
        assertEquals(15, iterador.siguiente());
        assertEquals(16, iterador.siguiente());
        assertEquals(20, iterador.siguiente());
        assertEquals(22, iterador.siguiente());
        assertEquals(24, iterador.siguiente());
        assertEquals(25, iterador.siguiente());

    }

    @Test
    void conjunto_vacio_toString() {
        ABB<Integer> c = new ABB<Integer>();

        assertEquals("{}", c.toString());

    }

    @Test
    void conjunto_de_un_elemento_toString() {
        ABB<Integer> c = new ABB<Integer>();

        c.insertar(7);

        assertEquals("{7}", c.toString());

    }

    @Test
    void conjunto_de_muchos_numeros_toString() {
        ABB<Integer> c = new ABB<Integer>();
        c.insertar(5);
        c.insertar(4);
        c.insertar(7);
        c.insertar(6);
        c.insertar(8);
        assertEquals("{4,5,6,7,8}", c.toString());
        c.eliminar(5);
        c.eliminar(7);
        assertEquals("{4,6,8}", c.toString());

    }

    Integer NCLAVES = 1000;

    private Integer clave(Integer i) {
        return NCLAVES * ((i * i - 100 * i) % NCLAVES) + i;
    }

    @Test
    void stress() {

        ABB<Integer> conjunto = new ABB<Integer>();

        // Insertar
        for (Integer i = 0; i < NCLAVES; i++) {
            Integer k = clave(i);
            assertEquals(i, conjunto.cardinal());
            assertEquals(false, conjunto.pertenece(k));
            conjunto.insertar(k);
            assertEquals(true, conjunto.pertenece(k));
        }
        assertEquals(NCLAVES, conjunto.cardinal());

        // Insertar de nuevo
        for (Integer i = 0; i < NCLAVES; i++) {
            Integer k = clave(i);
            assertTrue(conjunto.pertenece(k));

            conjunto.insertar(k);

            assertTrue(conjunto.pertenece(k));
            assertEquals(NCLAVES, conjunto.cardinal());
        }

        // Eliminar los valores para i par
        for (Integer i = 0; i < NCLAVES; i++) {
            Integer k = clave(i);
            assertTrue(conjunto.pertenece(k));
            if (i % 2 == 0) {
                conjunto.eliminar(k);

                assertFalse(conjunto.pertenece(k));
            }
        }
        assertEquals(NCLAVES / 2, conjunto.cardinal());

        // Eliminar los valores para i impar
        for (Integer i = 0; i < NCLAVES; i++) {
            Integer k = clave(i);
            if (i % 2 == 0) {
                assertFalse(conjunto.pertenece(k));
            } else {
                assertTrue(conjunto.pertenece(k));
                conjunto.eliminar(k);

                assertFalse(conjunto.pertenece(k));
            }
        }
        assertEquals(0, conjunto.cardinal());

        // Verificar que no haya valores
        for (Integer i = 0; i < NCLAVES; i++) {
            Integer k = clave(i);
            assertFalse(conjunto.pertenece(k));
        }
    }
}
