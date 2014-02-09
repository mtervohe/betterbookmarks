package com.jivesoftware.managedbookmarks.action;

import com.jivesoftware.community.action.util.Decorate;
import com.jivesoftware.community.bookmarks.BookmarksBrowseAction;
import com.jivesoftware.community.bookmarks.rest.BookmarksViewBean;
import com.jivesoftware.community.web.struts.SetReferer;
//import com.jivesoftware.managedbookmarks.rest.BetterBookmarksViewBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Sets up a view to show a user's bookmarks in SBS.
 */
@Decorate(false)
@SetReferer(false)
public class ProfileBetterBookmarksBrowseAction extends BookmarksBrowseAction {
    static Logger log = LogManager.getLogger(ProfileBetterBookmarksBrowseAction.class);

    public void setProfileBookmarksBrowseViewBean(BookmarksViewBean profileBookmarksBrowseViewBean) {
        setBookmarksBrowseViewBean( profileBookmarksBrowseViewBean);
    }

    @Override
    public String getBrowseAction() {
        return "betterbookmarks";
    }

    @Override
    public String execute() {
        model = getBrowseActionSupport().getPopulatedBrowseViewBean();
        return SUCCESS;
    }

    public String doAdd(){
        System.out.println("ProfileBetterBookmarksBrowseAction doAdd add");
        return "SUCCESS";
    }


}
