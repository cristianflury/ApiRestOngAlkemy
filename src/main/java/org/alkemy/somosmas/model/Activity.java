package org.alkemy.somosmas.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@SQLDelete(sql = "UPDATE activity SET deleted = true, deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String image;

    @CreatedDate
    private LocalDateTime createDate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Organization organization;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    private LocalDateTime deletedAt;

    private boolean deleted = false;
}