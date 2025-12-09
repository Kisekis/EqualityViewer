package com.nime.eqviewer.security;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * Security utility class for input validation and sanitization
 */
public class InputValidator {
    
    // Pattern to allow only safe file paths (alphanumeric, dots, slashes, underscores, hyphens)
    // Note: consecutive dots are blocked separately in isValidPath()
    private static final Pattern SAFE_PATH_PATTERN = Pattern.compile("^[a-zA-Z0-9/_.-]+$");
    
    // Maximum file size to read (10 MB)
    public static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
    
    /**
     * Validates that an ID is non-negative
     * @param id the ID to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidId(Integer id) {
        return id != null && id >= 0;
    }
    
    /**
     * Validates and sanitizes a file path to prevent path traversal attacks
     * @param basePath the base directory path
     * @param filePath the file path to validate
     * @return true if the path is safe, false otherwise
     */
    public static boolean isValidPath(String basePath, String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return false;
        }
        
        // Check for path traversal attempts
        if (filePath.contains("..") || filePath.contains("~")) {
            return false;
        }
        
        // Additional check: ensure no consecutive dots anywhere in path
        if (filePath.matches(".*\\.{2,}.*")) {
            return false;
        }
        
        // Validate path pattern
        if (!SAFE_PATH_PATTERN.matcher(filePath).matches()) {
            return false;
        }
        
        try {
            // Resolve and normalize the path
            Path base = Paths.get(basePath).toAbsolutePath().normalize();
            Path resolved = base.resolve(filePath).normalize();
            
            // Ensure the resolved path is within the base path
            return resolved.startsWith(base);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Sanitizes CSV content to prevent CSV injection attacks
     * @param content the content to sanitize
     * @return sanitized content
     */
    public static String sanitizeCsvContent(String content) {
        if (content == null) {
            return "";
        }
        
        // Remove or escape characters that could be used for CSV injection
        // Characters like =, +, -, @, \t, \r at the beginning can be dangerous
        String sanitized = content.trim();
        
        if (sanitized.length() > 0) {
            char firstChar = sanitized.charAt(0);
            if (firstChar == '=' || firstChar == '+' || firstChar == '-' 
                || firstChar == '@' || firstChar == '\t' || firstChar == '\r') {
                sanitized = "'" + sanitized;
            }
        }
        
        // Escape quotes
        sanitized = sanitized.replace("\"", "\"\"");
        
        return sanitized;
    }
    
    /**
     * Validates a ResultType string
     * @param resultType the result type to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidResultType(String resultType) {
        if (resultType == null) {
            return false;
        }
        return resultType.equals("EQUAL") || resultType.equals("INEQUAL") 
            || resultType.equals("SAME") || resultType.equals("UNCERTAIN");
    }
    
    /**
     * Validates a ConfiLevel string
     * @param level the confidence level to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidConfiLevel(String level) {
        if (level == null) {
            return false;
        }
        return level.equals("RELIABLE") || level.equals("UNRELIABLE") 
            || level.equals("SUSPICIOUS");
    }
}
