package es.drodriguezlopez.javastreamtutorial;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase con tests de filtrado de los elementos del
 * stream mediante {@link java.util.function.Predicate}
 */
public class StreamPredicateTest {

    /**
     * {@link Stream::allMatch} nos permite evaluar si todos
     * los elementos del stream coinciden con el predicado
     */
    @Test
    public void testAllMatch() {
        Stream<Integer> ns = Stream.of(1, 2, 3, 1, 4, 5);
        assertFalse(ns.allMatch(n -> n.equals(1)));
        ns = Stream.of(1, 1, 1);
        assertTrue(ns.allMatch(n -> n.equals(1)));
    }

    /**
     * {@link Stream::anyMatch} nos permite evaluar si alguno
     * los elementos del stream coinciden con el predicado
     */
    @Test
    public void testAnyMatch() {
        Stream<Integer> ns = Stream.of(1, 2, 3, 1, 4, 5);
        assertTrue(ns.anyMatch(n -> n.equals(1)));
        ns = Stream.of(1, 2, 3, 1, 4, 5);
        assertFalse(ns.anyMatch(n -> n.equals(6)));
    }

    /**
     * {@link Stream::dropWhile} descarta los primeros elementos
     * que coincidan con el predicado
     */
    @Test
    public void testDropWhile() {
        Stream<Integer> ns = Stream.of(1, 1, 4, 5, 1, 2, 3);
        Stream<Integer> d = ns.dropWhile(n -> n.equals(1));
        assertNotNull(d);
        assertEquals(5, d.count());
        ns = Stream.of(4, 5, 1, 2, 3);
        d = ns.dropWhile(n -> n.equals(1));
        assertNotNull(d);
        assertEquals(5, d.count());
    }

    /**
     * {@link Stream::filter} Selecciona aquellos elementos
     * que coincidan con el predicado
     */
    @Test
    public void testFilter() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        Stream<Integer> d = ns.filter(n -> !n.equals(2));
        assertNotNull(d);
        assertEquals(2, d.count());
    }

    /**
     * {@link Stream::noneMatch} evalua si todos los elementos
     * del stream no coinciden con el predicado
     */
    @Test
    public void testNoneMatch() {
        Stream<Integer> ns = Stream.of(1, 2, 3);
        assertFalse(ns.noneMatch(n -> n.equals(1)));
        ns = Stream.of(1, 2, 3);
        assertTrue(ns.noneMatch(n -> n.equals(4)));
    }

    /**
     * {@link Stream::takeWhile} selecciona los primeros elementos
     * que coincidan con el predicado
     */
    @Test
    public void testTakeWhile() {
        Stream<Integer> ns = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> d = ns.takeWhile(n -> !n.equals(3));
        assertNotNull(d);
        assertEquals(2, d.count());
    }

    /**
     * Ejemplo del uso de predicados anidados
     */
    @Test
    public void testAllMatchComplex() {
        // Valida que todos los elementos tengan el valor 1 o 2
        Predicate<Integer> pEq1 = n -> n.equals(1);
        Predicate<Integer> pEq2 = n -> n.equals(2);
        Predicate<Integer> pEq1OrpEq2 = pEq1.or(pEq2);

        // No es correcto porque los valores no son todos ni 1 o 2
        Stream<Integer> ns = Stream.of(1, 2, 3, 1, 4, 5);
        assertFalse(ns.allMatch(pEq1OrpEq2));

        // Es correcto porque todos los valores son 1
        ns = Stream.of(1, 1, 1);
        assertTrue(ns.allMatch(pEq1OrpEq2));

        // Es correcto porque todos los valores son 2
        ns = Stream.of(2, 2, 2);
        assertTrue(ns.allMatch(pEq1OrpEq2));

        // No es correcto porque los valores no son todos ni 1 o 2
        ns = Stream.of(3, 3, 3);
        assertFalse(ns.allMatch(pEq1OrpEq2));
    }
}
