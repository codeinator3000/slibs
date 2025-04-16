package com.slibs.slibs.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.slibs.slibs.infrastructure.parsers.ParserNpm;

@ExtendWith(MockitoExtension.class)
class ParserNpmTests {
    @Test
    void whenNormalSearch_thenSearch_returnsLibraries() {
        var parser = new ParserNpm();
        var res = parser.getLibraries(0);
        assertNotNull(res);
        assertEquals(10, res.size());
    }
}
