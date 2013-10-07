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

    @RequiresUpdate
    void setSubject(String subject);

    JiveContainer getContainer();

}
