/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ramses
 */
@Entity
@Table(name = "core_log_events")
public class LogEvent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "event_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date eventDate;
    
    @Column(name = "client_host", nullable = true)
    private String clientHost;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_code", nullable = false)
    private LogCategory category;

    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private LogLevel level;

    public LogEvent() {
    }

    public LogEvent(String message, Date eventDate) {
        this.message = message;
        this.eventDate = eventDate;
    }

    public LogEvent(String message, Date eventDate, String clientHost) {
        this.message = message;
        this.eventDate = eventDate;
        this.clientHost = clientHost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getClientHost() {
        return clientHost;
    }

    public void setClientHost(String clientHost) {
        this.clientHost = clientHost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LogCategory getCategory() {
        return category;
    }

    public void setCategory(LogCategory category) {
        this.category = category;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LogEvent other = (LogEvent) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "LogEvent{" + "id=" + id + ", message=" + message + ", eventDate=" + eventDate + ", user=" + user + ", category=" + category + ", level=" + level + '}';
    }
}
