package com.tencent.subject.domain.convert;

import com.tencent.subject.domain.entity.SubjectLabelBO;
import com.tencent.subject.infra.basic.entity.SubjectLabel;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-03T18:48:27+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
public class SubjectLabelConverterImpl implements SubjectLabelConverter {

    @Override
    public SubjectLabel convertBoToLabel(SubjectLabelBO subjectLabelBO) {
        if ( subjectLabelBO == null ) {
            return null;
        }

        SubjectLabel subjectLabel = new SubjectLabel();

        subjectLabel.setId( subjectLabelBO.getId() );
        subjectLabel.setLabelName( subjectLabelBO.getLabelName() );
        subjectLabel.setSortNum( subjectLabelBO.getSortNum() );

        return subjectLabel;
    }
}
