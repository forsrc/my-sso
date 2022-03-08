package org.forsrc.sso.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
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
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "t_sso_user", indexes = {
        @Index(name = "index_user_username", columnList = "username") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "username" }) })
// @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler",
// "fieldHandler"})
// @SelectBeforeUpdate(true)
@DynamicUpdate(true)
@DynamicInsert(true)

public class User implements Serializable {

	private static final long serialVersionUID = -2974105023690270688L;

	@Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "username", unique = true, length = 200, nullable = false)
    private String username;

    @Column(name = "password", length = 200, insertable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "enabled", length = 1, columnDefinition = "INT DEFAULT 1")
    private Integer enabled;

    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date updatedAt;

    @Column(name = "version", insertable = false, updatable = true, columnDefinition = "INT DEFAULT 0")
    @Version
    private int version;

    @Column(name = "jwt_token", length = 1000)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String jwtToken;

    @OneToMany(mappedBy = "username", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    // @JoinColumn(name = "username", foreignKey =
    // @javax.persistence.ForeignKey(name = "none"))
    // @JoinColumn(name = "username")
    private Set<Authority> authorities = new HashSet<>();

    @PrePersist
    protected void onCreatedAt() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdatedAt() {
        this.updatedAt = new Date();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
    	this.username = username != null ? username.toLowerCase() : username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
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

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", enabled=" + enabled
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", version=" + version + ", jwtToken="
                + jwtToken + ", authorities=" + authorities + '}';
    }

}