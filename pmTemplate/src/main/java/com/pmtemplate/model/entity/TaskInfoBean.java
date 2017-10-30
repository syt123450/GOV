package com.pmtemplate.model.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by ss on 2017/10/19.
 */

@Data
public class TaskInfoBean {

    List<PointEntityBean> taskPoints;
    List<RelationBean> taskRelations;
    List<List<PointEntityBean>> processors;
    List<List<RelationBean>> processTaskRelation;
}
