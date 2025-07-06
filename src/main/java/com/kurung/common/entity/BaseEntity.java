package com.kurung.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;

@Getter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
public class BaseEntity {

    @CreatedDate
    @Column(name = "CREATE_DATE", updatable = false)
    protected Date createDate;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    protected Date modifyDate;
}
