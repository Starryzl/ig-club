package com.tencent.circle.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.circle.api.req.RemoveShareCircleReq;
import com.tencent.circle.api.req.SaveShareCircleReq;
import com.tencent.circle.api.req.UpdateShareCircleReq;
import com.tencent.circle.api.vo.ShareCircleVO;
import com.tencent.circle.server.entity.po.ShareCircle;

import java.util.List;

/**
 * 圈子信息 服务类
 */
public interface ShareCircleService extends IService<ShareCircle> {

    List<ShareCircleVO> listResult();

    Boolean saveCircle(SaveShareCircleReq req);

    Boolean updateCircle(UpdateShareCircleReq req);

    Boolean removeCircle(RemoveShareCircleReq req);

}
