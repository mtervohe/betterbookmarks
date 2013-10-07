package com.jivesoftware.betterbookmarks.model;

import com.jivesoftware.community.JiveContainer;
import com.jivesoftware.community.JiveContentObject;
import com.jivesoftware.community.JiveObject;
import com.jivesoftware.community.annotations.RequiresUpdate;
import com.jivesoftware.community.objecttype.MentionEnabled;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 9/26/13
 * Time: 12:34 AM
 * To change this template use File | Settings | File Templates.
 */
public interface BetterBookmark extends JiveContentObject, MentionEnabled {

    ObjectBetterBookmark getObjectBetterBookmark();

    JiveObject getCreator();

    @RequiresUpdate
    void setSubject(String subject);

    /**
     * Returns <tt>true</tt> if the subject returned by {@link #getSubject()} was defined by the user. A return value of
     * false indicates that the subject was inherited from the favorited object.
     *
     * @return <tt>true</tt> if the subject returned by {@link #getSubject()} was defined by the user.
     */
    boolean isUserDefinedSubject();

    @RequiresUpdate
    void setBody(String body);

    JiveContainer getContainer();

}
