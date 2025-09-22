package com.tencent.interview.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.interview.server.dao.SensitiveWordsMapper;
import com.tencent.interview.server.entity.po.SensitiveWords;
import com.tencent.interview.server.service.SensitiveWordsService;
import org.springframework.stereotype.Service;

@Service
public class SensitiveWordsServiceImpl extends ServiceImpl<SensitiveWordsMapper, SensitiveWords> implements SensitiveWordsService {

}
