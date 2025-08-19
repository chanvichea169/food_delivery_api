package com.vicheaCoder.food_delivery_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemPhotoResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("file_type")
    private String fileType;

    @JsonProperty("file_format")
    private String fileFormat;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_size")
    private String fileSize;

    @JsonProperty("small_url")
    private String smallUrl;

    @JsonProperty("medium_url")
    private String mediumUrl;

    @JsonProperty("large_url")
    private String largeUrl;

    @JsonProperty("upload_by")
    private String uploadBy;

    private String status;
}
