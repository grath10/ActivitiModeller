package com.workflow.entity.activiti.mapper;

import com.workflow.utils.CollectionUtils;
import org.activiti.bpmn.model.FormProperty;
import org.activiti.bpmn.model.UserTask;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserTaskInfoMapper extends AbstractInfoMapper {
    protected void mapProperties(Object element) {
        UserTask userTask = (UserTask) element;
        createPropertyNode("Assignee", userTask.getAssignee());
        createPropertyNode("Candidate users", userTask.getCandidateUsers());
        createPropertyNode("Candidate groups", userTask.getCandidateGroups());
        createPropertyNode("Due date", userTask.getDueDate());
        createPropertyNode("Form key", userTask.getFormKey());
        createPropertyNode("Priority", userTask.getPriority());
        if (CollectionUtils.isNotEmpty(userTask.getFormProperties())) {
            List<String> formPropertyValues = new ArrayList<String>();
            for (FormProperty formProperty : userTask.getFormProperties()) {
                StringBuilder propertyBuilder = new StringBuilder();
                if (StringUtils.isNotEmpty(formProperty.getName())) {
                    propertyBuilder.append(formProperty.getName());
                } else {
                    propertyBuilder.append(formProperty.getId());
                }
                if (StringUtils.isNotEmpty(formProperty.getType())) {
                    propertyBuilder.append(" - ");
                    propertyBuilder.append(formProperty.getType());
                }
                if (formProperty.isRequired()) {
                    propertyBuilder.append(" (required)");
                } else {
                    propertyBuilder.append(" (not required)");
                }
                formPropertyValues.add(propertyBuilder.toString());
            }
            createPropertyNode("Form properties", formPropertyValues);
        }
        createListenerPropertyNodes("Task listeners", userTask.getTaskListeners());
        createListenerPropertyNodes("Execution listeners", userTask.getExecutionListeners());
    }
}
