package com.cola.omlink.manager.mapper;


import com.cola.omlink.repository.dto.product.ProjectDto;
import com.cola.omlink.repository.entity.order.Order;
import com.cola.omlink.repository.entity.product.Project;
import com.cola.omlink.repository.entity.product.ProjectDetail;
import com.cola.omlink.repository.vo.Project.ProjectVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    void save(Project project);

    void saveDetail(ProjectDetail projectDetail);

    List<Project> findProjectPage();

    void update(Project project);

    void updateDetail(Long projectId, String content);

    void delete(Long projectId);
}
