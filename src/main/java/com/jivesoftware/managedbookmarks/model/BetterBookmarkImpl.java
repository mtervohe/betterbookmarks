package com.jivesoftware.managedbookmarks.model;

import com.jivesoftware.base.User;
import com.jivesoftware.community.JiveContainer;
import com.jivesoftware.community.UserContainer;
import com.jivesoftware.community.objecttype.JiveObjectType;
import com.jivesoftware.util.DateUtils;
import com.jivesoftware.util.StringUtils;
import org.w3c.dom.Document;

import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 9/26/13
 * Time: 12:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class BetterBookmarkImpl implements BetterBookmark {

    protected final JiveObjectType objectType;

    protected final long id;
    protected final ObjectBetterBookmark bookmarkedObject;
    protected final UserContainer container;
    protected final String subject;
    protected final Date creationDate;
    protected final Date modificationDate;

    //protected final FavoriteInfoProvider favoriteInfoProvider;

    public BetterBookmarkImpl(JiveObjectType objectType, long id, ObjectBetterBookmark bookmarkedObject,
                              User owner, UserContainer container,
                              String subject, String bodyStr, Date creationDate,
                              Date modificationDate)
    {
        this.objectType = objectType;
        this.id = id;
        this.bookmarkedObject = bookmarkedObject;
        this.container = container;
        if (bodyStr == null) {
            bodyStr = "";
        }
        this.subject = subject;
        this.creationDate = DateUtils.newInstance(creationDate);
        this.modificationDate = DateUtils.newInstance(modificationDate);
    }

    public Document getBody() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getPlainBody() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Date getCreationDate() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Date getModificationDate() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getUnfilteredSubject() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long getContainerID() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getContainerType() {
        return container.getObjectType();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public JiveObjectType getJiveObjectType() {
        return objectType;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isVisibleToPartner() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getSubject() {
        if (StringUtils.isBlank(subject)) {
            return bookmarkedObject.getSubject();
        }
        else {
            return "BetterBookmarkImpl_getSubject()";
        }
    }

    public String getPlainSubject() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long getID() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getObjectType() {
        return objectType.getID();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Status getStatus() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public User getUser() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long getUserID() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterable<User> getAuthors() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Collection<Long> getAuthorIDs() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ObjectBetterBookmark getObjectBetterBookmark() {
        return bookmarkedObject;
    }

    public void setSubject(String subject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public JiveContainer getContainer() {
        return container;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FavoriteImpl");
        sb.append("{objectType=").append(objectType);
        sb.append(", id=").append(id);
        sb.append(", bookmarkedObject=").append(bookmarkedObject);
        sb.append(", container=").append(container);
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", creationDate=").append(creationDate == null ? null : creationDate.getTime());
        sb.append(", modificationDate=").append(modificationDate == null ? null : modificationDate.getTime());
        sb.append('}');
        return sb.toString();
    }
}
