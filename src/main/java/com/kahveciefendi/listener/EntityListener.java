package com.kahveciefendi.listener;


import com.kahveciefendi.entity.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Calendar;

public class EntityListener implements Serializable {

    @PrePersist
    public void prePersist(BaseEntity entity) {
        entity.setCreateTime(Calendar.getInstance());
    }


    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.setUpdateTime(Calendar.getInstance());
    }

}
