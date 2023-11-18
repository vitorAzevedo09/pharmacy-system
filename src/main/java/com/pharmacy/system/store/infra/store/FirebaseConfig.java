package com.pharmacy.system.store.infra.store;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

/**
 * FirebaseConfig
 */
@Configuration
public class FirebaseConfig {

  @Value("${firebase.storage.bucket}")
  private String firebaseStorageBucket;

  @Value("${firebase.config.path}")
  private String firebaseConfigPath;

  @PostConstruct
  public void init() throws IOException {
    FileInputStream serviceAccount = new FileInputStream(firebaseConfigPath);

    FirebaseOptions options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setStorageBucket(firebaseStorageBucket)
        .build();

    FirebaseApp.initializeApp(options);
  }

  @Bean
  public Storage storage() {
    return StorageOptions.getDefaultInstance().getService();
  }

}
