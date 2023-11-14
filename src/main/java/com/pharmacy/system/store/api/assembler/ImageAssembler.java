package com.pharmacy.system.store.api.assembler;

import org.springframework.stereotype.Component;

import com.pharmacy.system.store.api.dto.ImageWithIdDTO;
import com.pharmacy.system.store.domain.model.Image;

/**
 * ImageAssembler
 */
@Component
public class ImageAssembler {

  public ImageWithIdDTO toOutput(Image entity) {
    return new ImageWithIdDTO(
        entity.getId(),
        entity.getImageUrl(),
        entity.getDescription());
  }

}
