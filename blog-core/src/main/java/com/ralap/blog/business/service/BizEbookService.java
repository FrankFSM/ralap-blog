/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.ralap.blog.business.service;


import com.github.pagehelper.PageInfo;
import com.ralap.blog.business.entity.Ebook;
import com.ralap.blog.business.vo.EbookConditionVO;
import java.util.List;
import java.util.Map;

/**
 * BizEbookService
 * 电子书
 *
 * @author: ralap
 * @date: created at 2018/8/31 16:11
 */
public interface BizEbookService {

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    Ebook insert(Ebook entity);

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     */
    void insertList(List<Ebook> entities);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
    boolean removeByPrimaryKey(Long primaryKey);

    /**
     * 根据主键更新实体全部字段，null值会被更新
     */
    boolean update(Ebook entity);

    /**
     * 根据主键更新属性不为null的值
     */
    boolean updateSelective(Ebook entity);

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */
    Ebook getByPrimaryKey(Long primaryKey);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     */
    Ebook getOneByEntity(Ebook entity);


    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     */
    List<Ebook> listAll();

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    List<Ebook> listByEntity(Ebook entity);

    /**
     * 分页查询
     */
    PageInfo<Ebook> findPageBreakByCondition(EbookConditionVO vo);

    /**
     * 查询可在首页显示的友情链接列表
     */
    List<Ebook> listOfIndex();

    /**
     * 查询可在内页显示的友情链接列表
     */
    List<Ebook> listOfInside();

    /**
     * 查询已禁用的友情链接列表
     */
    List<Ebook> listOfDisable();

    /**
     * 分组获取所有连接
     * {index:首页显示,inside:内页,disable:禁用}
     */
    Map<String, List<Ebook>> listAllByGroup();

}
