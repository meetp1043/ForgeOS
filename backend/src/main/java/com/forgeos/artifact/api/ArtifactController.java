package com.forgeos.artifact.api;

import com.forgeos.artifact.domain.model.ArtifactType;
import com.forgeos.artifact.domain.service.ArtifactService;
import com.forgeos.artifact.infrastructure.persistence.ArtifactEntity;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/artifacts")
public class ArtifactController {

    private final ArtifactService artifactService;

    public ArtifactController(ArtifactService artifactService) {
        this.artifactService = artifactService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ArtifactEntity> uploadArtifact(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") ArtifactType type) {
        
        try {
            ArtifactEntity artifact = artifactService.createAndUploadArtifact(
                    file.getOriginalFilename(),
                    type,
                    file.getContentType(),
                    file.getSize(),
                    file.getInputStream()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(artifact);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<InputStreamResource> downloadArtifact(@PathVariable UUID id) {
        ArtifactEntity metadata = artifactService.getArtifactMetadata(id);
        InputStream inputStream = artifactService.downloadArtifact(id);
        
        InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.getName() + "\"")
                .contentType(MediaType.parseMediaType(metadata.getContentType() != null ? metadata.getContentType() : MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .contentLength(metadata.getSizeBytes())
                .body(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtifactEntity> getArtifactMetadata(@PathVariable UUID id) {
        return ResponseEntity.ok(artifactService.getArtifactMetadata(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtifact(@PathVariable UUID id) {
        artifactService.deleteArtifact(id);
        return ResponseEntity.noContent().build();
    }
}
