package com.jivesoftware.managedbookmarks.impl;

import com.jivesoftware.managedbookmarks.ManagedBookmark;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 2/1/14
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagedBookmarkImpl implements ManagedBookmark {
    private static final String MANAGED_BOOKMARK_TYPE_NAME = "managedBookmark";

    public static final int MANAGED_BOOKMARK_TYPE_ID = Math.abs(31 * 17 + MANAGED_BOOKMARK_TYPE_NAME.hashCode());


    private long bookmarkID;
    private String bookmarkName;

    public ManagedBookmarkImpl() {
    }

    public long getBookmarkID() {
        return bookmarkID;
    }

    public void setBookmarkID(long bookmarkID) {
        this.bookmarkID = bookmarkID;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }
}
