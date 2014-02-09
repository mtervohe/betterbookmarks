package com.jivesoftware.managedbookmarks.doa;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 10/1/13
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class BetterBookmarkBean implements Cloneable, Serializable {

    protected long id = Long.MIN_VALUE;
    protected int markedType;
    protected long markedId;
    protected long creatorId;
    protected String subject;
    protected Date creationDate;
    protected Date modificationDate;

    public BetterBookmarkBean() {
    }

    /**
     * Returns the unique system-wide identifier for this bookmark.
     *
     * @return the unique system-wide identifier for this bookmark.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique system-wide id for this bookmark.
     *
     * @param id the unique system-wide id for this bookmark.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the object type of the content being marked.
     *
     * @return the object type of the content being marked.
     */
    public int getMarkedType() {
        return markedType;
    }

    /**
     * Sets the object type of the content being marked.
     *
     * @param markedType the object type of the content being marked.
     */
    public void setMarkedType(int markedType) {
        this.markedType = markedType;
    }

    /**
     * Returns the unique, system-wide identifier for the object being marked by this bookmark.
     *
     * @return the unique, system-wide identifier for the object being marked by this bookmark.
     */
    public long getMarkedId() {
        return markedId;
    }

    /**
     * Sets the unique, system-wide identifier for the object being marked by this bookmark.
     *
     * @param markedId the unique, system-wide identifier for the object being marked by this bookmark.
     */
    public void setMarkedId(long markedId) {
        this.markedId = markedId;
    }

    /**
     * Returns the unique, system-wide identifier for the entity which created this bookmark.
     *
     * @return the unique, system-wide identifier for the entity which created this bookmark.
     */
    public long getCreatorId() {
        return creatorId;
    }

    /**
     * Sets the unique, system-wide identifier for the entity which created this bookmark.
     *
     * @param creatorId the unique, system-wide identifier for the entity which created this bookmark.
     */
    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * Returns the subject of this bookmark.
     *
     * @return the subject of this bookmark.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of this bookmark.
     *
     * @param subject the subject of this bookmark.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Returns the date on which this bookmark was created. <tt>null</tt> will be returned if no date has been set.
     *
     * @return the date on which this bookmark was created.
     */
    public Date getCreationDate() {
        return creationDate == null ? null : new Date(creationDate.getTime());
    }

    /**
     * Sets the date on which this bookmark was created.
     *
     * @param creationDate the date on which this bookmark was created. Specifying <tt>null</tt> will clear out any
     * previously configured value.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate == null ? null : new Date(creationDate.getTime());
    }

    /**
     * Returns the last date on which this bookmark was modified. <tt>null</tt> will be returned if no date has been
     * set.
     *
     * @return the last date on which this bookmark was modified.
     */
    public Date getModificationDate() {
        return modificationDate == null ? null : new Date(modificationDate.getTime());
    }

    /**
     * Sets the date on which this bookmark was last modified. Specifying <tt>null</tt> will clear out any value set
     * previously using this method.
     *
     * @param modificationDate the date on which this bookmark was last modified. Specifying <tt>null</tt> will clear
     * out any value set previously using this method.
     */
    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate == null ? null : new Date(modificationDate.getTime());
    }

    public Object clone() throws CloneNotSupportedException {
        BetterBookmarkBean bean = (BetterBookmarkBean) super.clone();
        bean.setCreationDate(creationDate);
        bean.setModificationDate(modificationDate);
        return bean;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BetterBookmarkBean that = (BetterBookmarkBean) o;

        if (creatorId != that.creatorId) {
            return false;
        }
        if (id != that.id) {
            return false;
        }
        if (markedId != that.markedId) {
            return false;
        }
        if (markedType != that.markedType) {
            return false;
        }
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) {
            return false;
        }
        if (modificationDate != null ? !modificationDate.equals(that.modificationDate)
                : that.modificationDate != null)
        {
            return false;
        }
        return !(subject != null ? !subject.equals(that.subject) : that.subject != null);
    }

    public int hashCode() {
        int result;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + markedType;
        result = 31 * result + (int) (markedId ^ (markedId >>> 32));
        result = 31 * result + (int) (creatorId ^ (creatorId >>> 32));
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        return result;
    }

    public String toString() {
        return String.format("FavoriteBean{id=%d, markedType=%d, markedId=%d, creatorId=%d, "
                + "subject='%s', creationDate=%s, modificationDate=%s}",
                id, markedType, markedId, creatorId, subject
                , creationDate, modificationDate);
    }
}
