package com.forgeos.artifact.domain.service;

import java.io.InputStream;
import java.util.Optional;

public interface ObjectStorageProvider {

    /**
     * Upload an object.
     */
    void putObject(String key, InputStream inputStream) throws Exception;

    /**
     * Download an object stream.
     */
    InputStream getObject(String key) throws Exception;

    /**
     * Delete an object.
     */
    void deleteObject(String key) throws Exception;

    /**
     * Check if an object exists.
     */
    boolean exists(String key) throws Exception;
}
