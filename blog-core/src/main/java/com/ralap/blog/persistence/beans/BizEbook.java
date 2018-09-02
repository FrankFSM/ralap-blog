package com.ralap.blog.persistence.beans;

import com.ralap.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BizEbook extends AbstractDO {

    private String title;

    private String author;

    private String coverImage;

    private String path;

    private Boolean status;

    private String description;
}