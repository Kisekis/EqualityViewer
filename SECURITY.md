# Security Hardening Documentation

This document describes the security measures implemented in the EqualityViewer application.

## Overview

The EqualityViewer application has been hardened against common security vulnerabilities through multiple layers of defense.

## Security Measures

### 1. Input Validation and Sanitization

**Class:** `InputValidator` (`springboot/src/main/java/com/nime/eqviewer/security/InputValidator.java`)

This utility class provides comprehensive input validation and sanitization methods:

#### Path Traversal Prevention
- **Method:** `isValidPath(String basePath, String filePath)`
- **Purpose:** Prevents path traversal attacks (e.g., `../../../etc/passwd`)
- **Implementation:**
  - Checks for dangerous characters like `..` and `~`
  - Validates path pattern using regex
  - Normalizes paths and ensures they stay within the base directory
  - Uses `Path.normalize()` and `Path.startsWith()` for security

#### ID Validation
- **Method:** `isValidId(Integer id)`
- **Purpose:** Ensures IDs are non-negative integers
- **Usage:** Validates all user-provided IDs before processing

#### CSV Injection Prevention
- **Method:** `sanitizeCsvContent(String content)`
- **Purpose:** Prevents CSV injection attacks
- **Implementation:**
  - Escapes dangerous formula characters (`=`, `+`, `-`, `@`)
  - Properly escapes quotes in CSV content
  - Prevents execution of malicious formulas in spreadsheet applications

#### File Size Limits
- **Constant:** `MAX_FILE_SIZE = 10 * 1024 * 1024` (10 MB)
- **Purpose:** Prevents resource exhaustion attacks
- **Usage:** Applied before reading any file content

### 2. Secure File Access

**Class:** `SourceCodePairServiceImpl` (`springboot/src/main/java/com/nime/eqviewer/service/SourceCodePairServiceImpl.java`)

The `getSourceCode()` method implements multiple security checks:

```java
// 1. Validate path to prevent traversal
if (!InputValidator.isValidPath(basePath, path)) {
    throw new SecurityException("Invalid file path");
}

// 2. Check file size to prevent resource exhaustion
long fileSize = Files.size(filePath);
if (fileSize > InputValidator.MAX_FILE_SIZE) {
    throw new SecurityException("File size exceeds maximum");
}

// 3. Read file with proper error handling
```

### 3. API Security

**Class:** `SourceCodePairController` (`springboot/src/main/java/com/nime/eqviewer/rest/SourceCodePairController.java`)

All API endpoints implement input validation:

- **ID Validation:** All endpoints accepting IDs validate them before processing
- **DTO Validation:** Request bodies are checked for null values
- **Error Responses:**
  - `400 Bad Request` - Invalid input
  - `403 Forbidden` - Security violation
  - `404 Not Found` - Resource not found
  - `500 Internal Server Error` - Other errors (without exposing details)

### 4. CSV Output Security

**Class:** `CSVWriter` (`springboot/src/main/java/com/nime/eqviewer/util/CSVWriter.java`)

All CSV output is sanitized using `InputValidator.sanitizeCsvContent()` to prevent CSV injection attacks.

### 5. CORS and Security Headers

**Class:** `SecurityConfig` (`springboot/src/main/java/com/nime/eqviewer/security/SecurityConfig.java`)

#### CORS Configuration
- **Allowed Origins:** `http://localhost:80`, `http://localhost`, `http://127.0.0.1:80`, `http://127.0.0.1`
- **Allowed Methods:** GET, POST, PUT, DELETE, OPTIONS
- **Credentials:** Allowed
- **Max Age:** 3600 seconds

#### Security Headers
The following security headers are added to all responses:

1. **X-Frame-Options: DENY**
   - Prevents clickjacking attacks
   - Disallows embedding in iframes

2. **X-Content-Type-Options: nosniff**
   - Prevents MIME type sniffing
   - Forces browsers to respect declared content types

3. **X-XSS-Protection: 1; mode=block**
   - Enables XSS filter in older browsers
   - Blocks page rendering on XSS detection

4. **Content-Security-Policy: default-src 'self'**
   - Restricts resource loading to same origin
   - Prevents loading of external scripts

5. **Referrer-Policy: strict-origin-when-cross-origin**
   - Controls referrer information
   - Protects sensitive URLs from leaking

### 6. CSV Input Validation

**Class:** `CSVReader` (`springboot/src/main/java/com/nime/eqviewer/util/CSVReader.java`)

CSV input is validated to ensure:
- Proper line format (at least 2 columns)
- Non-empty paths
- Invalid lines are skipped with error logging

## Testing

**Test Class:** `InputValidatorTest` (`springboot/src/test/java/com/nime/eqviewer/security/InputValidatorTest.java`)

Comprehensive test coverage includes:
- Path traversal prevention (various attack patterns)
- ID validation (positive, negative, null)
- CSV injection prevention (formula characters)
- ResultType and ConfiLevel validation
- File size limit verification

**Test Results:** All 6 tests passing ✓

## Security Scan Results

**CodeQL Analysis:** No security alerts found ✓

## Best Practices

When working with this codebase, always:

1. **Validate all user input** using `InputValidator` methods
2. **Check file paths** with `isValidPath()` before file operations
3. **Verify file sizes** before reading large files
4. **Sanitize CSV output** with `sanitizeCsvContent()`
5. **Handle exceptions properly** without exposing internal details
6. **Use proper HTTP status codes** for different error types

## Deployment Considerations

### Production Environment

When deploying to production:

1. **Enable HTTPS:** Uncomment the HSTS header in `SecurityConfig`
2. **Update CORS origins:** Change allowed origins to production URLs
3. **Configure logging:** Replace `System.err.println` with proper logging framework
4. **Set strict CSP:** Review and tighten Content-Security-Policy
5. **File path validation:** Ensure `application.properties` path is properly configured

### Security Checklist

- [ ] HTTPS enabled with valid certificate
- [ ] CORS origins updated for production
- [ ] File size limits appropriate for use case
- [ ] Logging framework configured
- [ ] Security headers reviewed
- [ ] Input validation tested with real data
- [ ] Error messages don't expose sensitive information

## Vulnerabilities Addressed

This security hardening addresses the following vulnerability classes:

1. **CWE-22: Path Traversal** - Prevented through path validation
2. **CWE-1236: CSV Injection** - Prevented through content sanitization
3. **CWE-400: Resource Exhaustion** - Prevented through file size limits
4. **CWE-20: Improper Input Validation** - Prevented through comprehensive validation
5. **CWE-209: Information Exposure** - Prevented through proper error handling
6. **CWE-1021: CORS Misconfiguration** - Prevented through restrictive CORS policy
7. **CWE-693: Protection Mechanism Failure** - Addressed through security headers

## References

- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [CWE - Common Weakness Enumeration](https://cwe.mitre.org/)
- [Spring Security Documentation](https://spring.io/projects/spring-security)

## Contact

For security concerns or to report vulnerabilities, please open a security advisory on GitHub.
