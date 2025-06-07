package com.slibs.slibs.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.slibs.slibs.entities.Library;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.slibs.slibs.infrastructure.parsers.ParserNpm;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ParserNpmTests {
    @Test
    void whenNormalSearch_thenSearch_returnsLibraries() {
        var parser = new ParserNpm();
        List<Library> res = null;
        try {
            res = parser.getLibraries(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(res);
        assertEquals(10, res.size());
    }
}
