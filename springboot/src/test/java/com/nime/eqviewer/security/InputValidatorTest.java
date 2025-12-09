package com.nime.eqviewer.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for InputValidator security methods
 */
public class InputValidatorTest {

    @Test
    public void testValidId() {
        // Test valid IDs
        assertTrue(InputValidator.isValidId(0));
        assertTrue(InputValidator.isValidId(1));
        assertTrue(InputValidator.isValidId(100));
        
        // Test invalid IDs
        assertFalse(InputValidator.isValidId(-1));
        assertFalse(InputValidator.isValidId(-100));
        assertFalse(InputValidator.isValidId(null));
    }

    @Test
    public void testPathTraversalPrevention() {
        String basePath = "/tmp/test";
        
        // Test valid paths
        assertTrue(InputValidator.isValidPath(basePath, "input/test.csv"));
        assertTrue(InputValidator.isValidPath(basePath, "input/csv/equal.csv"));
        
        // Test path traversal attempts
        assertFalse(InputValidator.isValidPath(basePath, "../etc/passwd"));
        assertFalse(InputValidator.isValidPath(basePath, "../../secret"));
        assertFalse(InputValidator.isValidPath(basePath, "input/../../../etc/passwd"));
        
        // Test invalid characters
        assertFalse(InputValidator.isValidPath(basePath, "test~file"));
        
        // Test null and empty
        assertFalse(InputValidator.isValidPath(basePath, null));
        assertFalse(InputValidator.isValidPath(basePath, ""));
    }

    @Test
    public void testCsvInjectionPrevention() {
        // Test dangerous formulas
        assertEquals("'=1+1", InputValidator.sanitizeCsvContent("=1+1"));
        assertEquals("'+cmd", InputValidator.sanitizeCsvContent("+cmd"));
        assertEquals("'-cmd", InputValidator.sanitizeCsvContent("-cmd"));
        assertEquals("'@cmd", InputValidator.sanitizeCsvContent("@cmd"));
        
        // Test quote escaping
        assertEquals("test\"\"quote", InputValidator.sanitizeCsvContent("test\"quote"));
        
        // Test normal content
        assertEquals("normal text", InputValidator.sanitizeCsvContent("normal text"));
        assertEquals("input/test.csv", InputValidator.sanitizeCsvContent("input/test.csv"));
        
        // Test null
        assertEquals("", InputValidator.sanitizeCsvContent(null));
    }

    @Test
    public void testValidResultType() {
        // Test valid result types
        assertTrue(InputValidator.isValidResultType("EQUAL"));
        assertTrue(InputValidator.isValidResultType("INEQUAL"));
        assertTrue(InputValidator.isValidResultType("SAME"));
        assertTrue(InputValidator.isValidResultType("UNCERTAIN"));
        
        // Test invalid result types
        assertFalse(InputValidator.isValidResultType("INVALID"));
        assertFalse(InputValidator.isValidResultType("equal"));
        assertFalse(InputValidator.isValidResultType(null));
        assertFalse(InputValidator.isValidResultType(""));
    }

    @Test
    public void testValidConfiLevel() {
        // Test valid confidence levels
        assertTrue(InputValidator.isValidConfiLevel("RELIABLE"));
        assertTrue(InputValidator.isValidConfiLevel("UNRELIABLE"));
        assertTrue(InputValidator.isValidConfiLevel("SUSPICIOUS"));
        
        // Test invalid confidence levels
        assertFalse(InputValidator.isValidConfiLevel("INVALID"));
        assertFalse(InputValidator.isValidConfiLevel("reliable"));
        assertFalse(InputValidator.isValidConfiLevel(null));
        assertFalse(InputValidator.isValidConfiLevel(""));
    }

    @Test
    public void testMaxFileSize() {
        // Verify the max file size constant is set to 10MB
        assertEquals(10 * 1024 * 1024, InputValidator.MAX_FILE_SIZE);
    }
}
