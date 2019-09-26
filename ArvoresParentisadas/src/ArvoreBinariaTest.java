import org.junit.Test;

import static org.junit.Assert.*;

public class ArvoreBinariaTest {

    @Test
    public void testeToString() {
        ArvoreBinaria arvore = new ArvoreBinaria();

        NoArvoreBinaria raiz = arvore.adicionarNo(10, null, false);
        assertEquals("10", arvore.toString());

        NoArvoreBinaria no4 = arvore.adicionarNo(4, raiz, false);  // filho direito da raiz
        assertEquals("10()(4)", arvore.toString());

        NoArvoreBinaria no5 = arvore.adicionarNo(5, raiz, true);  // filho esquerdo da raiz
        assertEquals("10(5)(4)", arvore.toString());

        arvore.adicionarNo(20, no5, true);  // filho esquerdo do nó 5
        assertEquals("10(5(20))(4)", arvore.toString());
    }

    @Test
    public void testeIdaEVolta() {
        String arvoreAsString = "10(5(20))(4)";
        ArvoreBinaria arvore = ArvoreBinaria.fromString(arvoreAsString);
        assertEquals(arvoreAsString, arvore.toString());
    }
}