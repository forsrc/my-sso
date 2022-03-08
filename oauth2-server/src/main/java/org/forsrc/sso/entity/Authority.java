package org.forsrc.sso.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_sso_authority", indexes = {
        @Index(name = "index_authority_username", columnList = "username") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "username", "authority" }) })
@IdClass(AuthorityPk.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@DynamicUpdate
@DynamicInsert
public class Authority implements Serializable {

	private static final long serialVersionUID = 4129699434478110994L;

	@Id
    @Column(name = "username", unique = false, length = 200, nullable = false)
    private String username;

    @Id
    @Column(name = "authority", unique = false, length = 200, nullable = false)
    private String authority;

    @Column(name = "created_at", insertable = false, updatable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createdAt;

    @PrePersist
    protected void onCreated() {
        this.createdAt = new Date();
    }

    @Column(name = "updated_at", insertable = false, updatable = true, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date updatedAt;

    @PreUpdate
    protected void onUpdated() {
        this.updatedAt = new Date();
    }

    @Column(name = "version", insertable = false, updatable = true, nullable = false, columnDefinition = "INT DEFAULT 0")
    @Version
    private int version;

    public Authority() {
    }

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username != null ? username.toLowerCase() : username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Authority{" + "username='" + username + '\'' + ", authority='" + authority + '\'' + ", createdAt="
                + createdAt + ", updatedAt=" + updatedAt + ", version=" + version + '}';
    }
}
