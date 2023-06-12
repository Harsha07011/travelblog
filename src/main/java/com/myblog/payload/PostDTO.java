package com.myblog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private long id;
    @NotEmpty
    @Size(min =5,message = "Post title should have at least 5 characters")

    private String title;
    @NotEmpty
    @Size(min =5,message = "Post description should have at least 5 characters")
    private String description;
    @NotEmpty
    private String content;
}
