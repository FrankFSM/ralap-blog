package com.ralap.blog.persistence.mapper;


import com.ralap.blog.business.vo.EbookConditionVO;
import com.ralap.blog.persistence.beans.BizEbook;
import com.ralap.blog.plugin.BaseMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BizEbookMapper extends BaseMapper<BizEbook> {

    /**
     * 分页查询
     */
    List<BizEbook> findPageBreakByCondition(EbookConditionVO vo);
}