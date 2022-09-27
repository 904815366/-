package com.woniu.service;

import com.github.pagehelper.PageInfo;
import com.woniu.dao.po.Returnsales;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.repository.dto.ReturnsalesDto;
import com.woniu.web.fo.PageReturnSalesFo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xk
 * @since 2022-09-15
 */
public interface ReturnsalesService extends IService<Returnsales> {

    PageInfo<ReturnsalesDto> getPageRetuensaless(PageReturnSalesFo returnSalesFo);

    void addReturnsale(Returnsales returnsales);

}
