package com.tencent.circle.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.circle.api.common.PageResult;
import com.tencent.circle.api.req.*;
import com.tencent.circle.api.vo.ShareCircleVO;
import com.tencent.circle.api.vo.ShareMomentVO;
import com.tencent.circle.server.entity.po.ShareCircle;
import com.tencent.circle.server.entity.po.ShareMoment;

import java.util.List;

/**
 * 动态信息 服务类
 */
public interface ShareMomentService extends IService<ShareMoment> {

    Boolean saveMoment(SaveMomentCircleReq req);

    PageResult<ShareMomentVO> getMoments(GetShareMomentReq req);

    Boolean removeMoment(RemoveShareMomentReq req);

}
