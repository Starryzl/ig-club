package com.tencent.interview.api.req;

import com.tencent.interview.api.enums.EngineEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StartReq implements Serializable {

    private String engine = EngineEnum.IGLOCAL.name();

    private List<Key> questionList;

    @Data
    public static class Key {
        private String keyWord;
        private Long categoryId;
        private Long labelId;
    }


}
