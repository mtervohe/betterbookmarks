package com.jivesoftware.betterbookmarks.rest;

import com.jivesoftware.community.bookmarks.rest.BookmarksViewBean;
import com.jivesoftware.community.browse.rest.impl.BrowseViewBean;

public class BetterBookmarksViewBean extends BrowseViewBean {

    private String communityName;
    private boolean advertizeBookmarklet = true;



    public BetterBookmarksViewBean(String browseViewID) {
        super(browseViewID);
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public boolean isAdvertizeBookmarklet() {
        return advertizeBookmarklet;
    }

    public void setAdvertizeBookmarklet(boolean advertizeBookmarklet) {
        this.advertizeBookmarklet = advertizeBookmarklet;
    }
}

