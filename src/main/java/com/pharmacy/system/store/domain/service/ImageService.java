package com.pharmacy.system.store.domain.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.pharmacy.system.store.domain.model.Image;
import com.pharmacy.system.store.domain.repository.ImageRepository;

/**
 * FirebaseService
 */
@Service
public class ImageService {

  @Autowired
  private Storage storage;

  @Autowired
  private ImageRepository imageRepository;

  @Value("${firebase.storage.bucket}")
  private String firebaseStorageBucket;

  public Page<Image> findAll(Pageable pageable) {
    return imageRepository.findAll(pageable);
  }

  public String uploadImage(MultipartFile file) throws IOException {
    BlobId blobId = BlobId.of(firebaseStorageBucket, generateObjectName(file.getOriginalFilename()));
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
        .setContentType(file.getContentType())
        .build();

    storage.create(blobInfo, file.getBytes());

    return storage.get(blobId).getMediaLink();
  }

  private String generateObjectName(String name) {
    // Generate a unique object name (e.g., using UUID) based on content type
    return UUID.randomUUID().toString() + name;
  }

  public Image create(Image img) {
    return imageRepository.save(img);
  }
}
