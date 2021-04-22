package es.drodriguezlopez.javastreamtutorial;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase con tests de creacion de stream
 */
class StreamCreationTest {

    /**
     * Stream.of nos permite crear un stream en base a una coleccion,
     * para cada elemento de la coleccion que sea nulo nos añadira
     * un elemento nulo al stream
     */
    @Test
    public void testStreamOf() {
        Stream<Integer> ns = Stream.of(1, null, 3);
        assertNotNull(ns);
        assertEquals(3L, ns.count());
        ns = Stream.of(1, null, null);
        assertNotNull(ns);
        assertEquals(3L, ns.count());
        ns = Stream.of(1);
        assertNotNull(ns);
        assertEquals(1L, ns.count());
    }

    /**
     * Stream.ofNullable nos permite crear un stream
     * en base a un un unico elemento, si el elemento es nulo
     * se creara un stream vacio y no añadira un elemento nulo
     * al stream
     */
    @Test
    public void testStreamOfNullable() {
        Stream<Integer> ns = Stream.ofNullable(1);
        assertNotNull(ns);
        assertEquals(1L, ns.count());
        Integer i = null;
        ns = Stream.ofNullable(i);
        assertNotNull(ns);
        assertEquals(0L, ns.count());
    }

    /**
     * Stream.empty nos permite crear un stream vacio
     */
    @Test
    public void testStreamEmpty() {
        Stream<Integer> ns = Stream.empty();
        assertNotNull(ns);
        assertEquals(0L, ns.count());
    }

    /**
     * Creacion de un stream mediante el metodo stream
     * de las {@link java.util.Collection}
     */
    @Test
    public void testCollectionStream() {
        Stream<Integer> ns = List.of(1, 2, 3).stream();
        assertNotNull(ns);
        assertEquals(3L, ns.count());
        ns = List.of(1).stream();
        assertNotNull(ns);
        assertEquals(1L, ns.count());
    }
}