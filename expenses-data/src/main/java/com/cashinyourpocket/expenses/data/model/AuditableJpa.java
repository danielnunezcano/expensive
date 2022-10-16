package com.cashinyourpocket.expenses.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableJpa implements Serializable {

  @CreatedDate
  @Column(name = "FECHA_ALTA", nullable = false)
  private LocalDateTime createdDate;

  @CreatedBy
  @Column(name = "USUARIO_ALTA", nullable = false)
  private String createdBy;

  @LastModifiedDate
  @Column(name = "FECHA_MODIFICACION")
  private LocalDateTime updatedDate;

  @LastModifiedBy
  @Column(name = "USUARIO_MODIFICACION")
  private String updatedBy;

  @Column(name = "FECHA_BAJA")
  private LocalDateTime deletedDate;

  @Column(name = "USUARIO_BAJA")
  private String deletedBy;
}
