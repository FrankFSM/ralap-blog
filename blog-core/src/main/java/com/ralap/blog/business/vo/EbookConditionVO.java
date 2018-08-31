package com.ralap.blog.business.vo;

import com.ralap.blog.business.entity.Ebook;
import com.ralap.blog.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: ralap
 * @date: created at 2018/8/31 15:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EbookConditionVO extends BaseConditionVO {

    private Ebook ebook;

    private Integer status;

    public EbookConditionVO() {
    }

    public EbookConditionVO(Integer status) {
        this.status = status;
    }
}
