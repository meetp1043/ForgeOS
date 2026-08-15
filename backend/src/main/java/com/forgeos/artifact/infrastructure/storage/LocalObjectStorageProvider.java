package com.forgeos.artifact.infrastructure.storage;

import com.forgeos.artifact.domain.service.ObjectStorageProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LocalObjectStorageProvider implements ObjectStorageProvider {

    private final Path rootLocation;

    public LocalObjectStorageProvider(@Value("${forgeos.artifact.storage.local.path:/tmp/forgeos-artifacts}") String basePath) {
        this.rootLocation = Paths.get(basePath);
        try {
            Files.createDirectories(this.rootLocation);
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize storage directory", e);
        }
    }

    private Path resolveSafePath(String key) {
        if (key.contains("..") || key.contains("~") || key.startsWith("/")) {
            throw new IllegalArgumentException("Path traversal attempt detected in key: " + key);
        }
        return rootLocation.resolve(key).normalize();
    }

    @Override
    public void putObject(String key, InputStream inputStream) throws Exception {
        Path targetPath = resolveSafePath(key);
        Files.createDirectories(targetPath.getParent());
        
        try (FileOutputStream out = new FileOutputStream(targetPath.toFile())) {
            inputStream.transferTo(out);
        }
    }

    @Override
    public InputStream getObject(String key) throws Exception {
        Path targetPath = resolveSafePath(key);
        File file = targetPath.toFile();
        if (!file.exists()) {
            throw new IllegalArgumentException("Object not found: " + key);
        }
        return new FileInputStream(file);
    }

    @Override
    public void deleteObject(String key) throws Exception {
        Path targetPath = resolveSafePath(key);
        Files.deleteIfExists(targetPath);
    }

    @Override
    public boolean exists(String key) {
        return Files.exists(resolveSafePath(key));
    }
}
