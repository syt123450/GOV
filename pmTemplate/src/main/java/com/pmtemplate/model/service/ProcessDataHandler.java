package com.pmtemplate.model.service;

import com.pmtemplate.model.entity.PointEntityBean;
import com.pmtemplate.model.entity.RelationBean;
import com.pmtemplate.model.entity.TaskInfoBean;
import com.pmtemplate.model.utils.MySQLUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/10/19.
 */

public class ProcessDataHandler {

    public List<PointEntityBean> getProcessesInfo() {

        MySQLUtils mySQLUtils = new MySQLUtils();
        List<String> processes = mySQLUtils.getProcesses();

        List<PointEntityBean> infoList = new ArrayList<>();

        for (int i = 0; i < processes.size(); i++) {
            PointEntityBean pointEntityBean = new PointEntityBean();
            pointEntityBean.setId(i);
            pointEntityBean.setLabel(processes.get(i));
            pointEntityBean.setGroup(i);
            infoList.add(pointEntityBean);
        }

        return infoList;
    }

    public TaskInfoBean getTasksInfo(String processName) {

        int id = 0;

        MySQLUtils mySQLUtils = new MySQLUtils();
        List<String> tasks = mySQLUtils.getTasks(processName);

        List<PointEntityBean> taskEntityList = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            PointEntityBean pointEntityBean = new PointEntityBean();
            pointEntityBean.setId(id);
            pointEntityBean.setLabel("" + (id + 1) + ". " + tasks.get(i));
            pointEntityBean.setGroup(id);
            taskEntityList.add(pointEntityBean);
            id++;
        }

        List<RelationBean> taskRelationList = new ArrayList<>();

        for (int i = 0; i < tasks.size() - 1; i++) {
            RelationBean relationBean = new RelationBean();
            relationBean.setFrom(i);
            relationBean.setTo(i + 1);
            taskRelationList.add(relationBean);
        }

        TaskInfoBean taskInfoBean = new TaskInfoBean();
        taskInfoBean.setTaskPoints(taskEntityList);
        taskInfoBean.setTaskRelations(taskRelationList);

        List<List<PointEntityBean>> processors = new ArrayList<>();
        List<List<RelationBean>> processorTaskRelations = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            List<String> processorNames = mySQLUtils.getTasksProcessor(tasks.get(i));

            List<PointEntityBean> processesList = new ArrayList<>();
            for (int j = 0; j < processorNames.size(); j++) {
                PointEntityBean pointEntityBean = new PointEntityBean();
                pointEntityBean.setId(id);
                pointEntityBean.setLabel(processorNames.get(j));
                pointEntityBean.setGroup(i);
                id++;
                processesList.add(pointEntityBean);
            }
            processors.add(processesList);

            List<RelationBean> processTaskRelation = new ArrayList<>();
            for (int j = 0; j < processorNames.size(); j++) {
                RelationBean relationBean = new RelationBean();
                relationBean.setFrom(i);
                relationBean.setTo(processesList.get(j).getId());
                processTaskRelation.add(relationBean);
            }
            processorTaskRelations.add(processTaskRelation);
        }

        taskInfoBean.setProcessors(processors);
        taskInfoBean.setProcessTaskRelation(processorTaskRelations);

        return taskInfoBean;
    }
}
