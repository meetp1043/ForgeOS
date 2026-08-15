package com.forgeos.artifact.infrastructure.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.FileSystemUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class LocalObjectStorageProviderTests {

    private LocalObjectStorageProvider provider;
    private Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        tempDir = Files.createTempDirectory("forgeos-test-artifacts");
        provider = new LocalObjectStorageProvider(tempDir.toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        FileSystemUtils.deleteRecursively(tempDir);
    }

    @Test
    void testPutAndGetObject() throws Exception {
        String key = "tenants/t1/artifacts/a1";
        byte[] content = "Hello World".getBytes();
        
        provider.putObject(key, new ByteArrayInputStream(content));
        
        assertTrue(provider.exists(key));
        
        try (InputStream is = provider.getObject(key)) {
            assertArrayEquals(content, is.readAllBytes());
        }
    }

    @Test
    void testDeleteObject() throws Exception {
        String key = "tenants/t1/artifacts/a2";
        provider.putObject(key, new ByteArrayInputStream("Test".getBytes()));
        assertTrue(provider.exists(key));
        
        provider.deleteObject(key);
        
        assertFalse(provider.exists(key));
    }

    @Test
    void testPathTraversalIsBlocked() {
        String unsafeKey = "../../../etc/passwd";
        
        assertThrows(IllegalArgumentException.class, () -> {
            provider.putObject(unsafeKey, new ByteArrayInputStream("Test".getBytes()));
        });
    }
}
