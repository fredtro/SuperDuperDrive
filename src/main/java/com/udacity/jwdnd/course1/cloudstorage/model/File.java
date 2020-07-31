package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class File {
    private Integer fileId;
    private String contentType;
    private String fileName;
    private Long fileSize;
    private Integer userId;
    private byte[] fileData;
}
